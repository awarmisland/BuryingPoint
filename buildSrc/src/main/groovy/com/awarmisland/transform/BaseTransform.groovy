package com.awarmisland.transform

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.compress.utils.IOUtils
import org.gradle.api.Project

import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

abstract class BaseTransform extends Transform implements TransformInterface{

    private Project mProject;

    BaseTransform(Project p) {
        this.mProject = p;
    }

    @Override
    String getName() {
        return "BaseTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }


    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        def transformName = getName();
        println '--------------- '+transformName+' visit start --------------- '
        def startTime = System.currentTimeMillis()
        Collection<TransformInput> inputs = transformInvocation.inputs
        TransformOutputProvider outputProvider = transformInvocation.outputProvider
        //删除之前的输出
        if (outputProvider != null)
            outputProvider.deleteAll()
        //遍历inputs
        inputs.each { TransformInput input ->
            //遍历directoryInputs
            input.directoryInputs.each { DirectoryInput directoryInput ->
                //处理directoryInputs
                handleDirectoryInput(directoryInput, outputProvider)
            }

            //遍历jarInputs
            input.jarInputs.each { JarInput jarInput ->
                //处理jarInputs
                handleJarInputs(jarInput, outputProvider)
            }
        }
        def cost = (System.currentTimeMillis() - startTime) / 1000
        println '--------------- '+transformName+' visit end --------------- '
        println transformName+" cost ： $cost s"
    }

    /**
     * 遍历sdk 中的class
     * 处理Jar中的class文件
     */
     void handleJarInputs(JarInput jarInput, TransformOutputProvider outputProvider) {
        if (jarInput.file.getAbsolutePath().endsWith(".jar")) {
            //重名名输出文件,因为可能同名,会覆盖
            def jarName = jarInput.name
            def md5Name = DigestUtils.md5Hex(jarInput.file.getAbsolutePath())
            if (jarName.endsWith(".jar")) {
                jarName = jarName.substring(0, jarName.length() - 4)
            }

            JarFile jarFile = new JarFile(jarInput.file)
            Enumeration enumeration = jarFile.entries()
            File tmpFile = new File(jarInput.file.getParent() + File.separator + "classes_temp.jar")
            //避免上次的缓存被重复插入
            if (tmpFile.exists()) {
                tmpFile.delete()
            }
            JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(tmpFile))
            //用于保存
            while (enumeration.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) enumeration.nextElement()
                String entryName = jarEntry.getName()
                ZipEntry zipEntry = new ZipEntry(entryName)
                InputStream inputStream = jarFile.getInputStream(jarEntry)
//                println("className: "+entryName)
                jarOutputStream.putNextEntry(zipEntry)
                //处理 插桩class
                if(isModifyClass(entryName)&&entryName.endsWith(".class")){
                    byte[] code = modifyClass(entryName, IOUtils.toByteArray(inputStream))
                    if(code){
                        jarOutputStream.write(code)
                    }else{
                        jarOutputStream.write(IOUtils.toByteArray(inputStream))
                    }
                }else{
                    jarOutputStream.write(IOUtils.toByteArray(inputStream))
                }
                jarOutputStream.closeEntry()
            }
            //结束
            jarOutputStream.close()
            jarFile.close()

            def dest = outputProvider.getContentLocation(jarName + md5Name,
                    jarInput.contentTypes, jarInput.scopes, Format.JAR)
            FileUtils.copyFile(tmpFile, dest)
            tmpFile.delete()
        }
    }


    /**
     *  遍历自己的class
     * @param directoryInput
     * @param outputProvider
     */
     void handleDirectoryInput(DirectoryInput directoryInput, TransformOutputProvider outputProvider) {
         //是否是目录
         if (directoryInput.file.isDirectory()) {
             //列出目录所有文件（包含子文件夹，子文件夹内文件）
             //处理完输入文件之后，要把输出给下一个任务
             def dest = outputProvider.getContentLocation(directoryInput.name,
                     directoryInput.contentTypes, directoryInput.scopes,
                     Format.DIRECTORY)
             File dir = directoryInput.file
             FileUtils.copyDirectory(directoryInput.file, dest)

             directoryInput.file.eachFileRecurse { File file ->
                 if(isModifyClass(file.name)&& file.name.endsWith(".class")){
                     byte[] code = modifyClass(file.name,file.bytes)
                     if(code){
                         String key = file.absolutePath.replace(dir.absolutePath,"")
                         File target = new File(dest.absolutePath + key)
                         if (target.exists()) {
                             target.delete()
                         }
                         FileOutputStream fos = new FileOutputStream(target.absolutePath)
//                         println("path: " + file.parentFile.absolutePath + File.separator + name)
                         fos.write(code)
                         fos.close()
                     }
                 }
             }
         }

    }

}