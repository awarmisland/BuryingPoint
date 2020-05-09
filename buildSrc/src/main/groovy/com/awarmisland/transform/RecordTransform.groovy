package com.awarmisland.transform

import com.awarmisland.buryingPoint.ClickRecordClassVisitor
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

import static org.objectweb.asm.ClassReader.EXPAND_FRAMES

class RecordTransform extends BaseTransform {

    RecordTransform(Project p) {
        super(p)
    }

    @Override
    String getName() {
        return "RecordTransform"
    }

    @Override
    boolean isModifyClass(String className) {
        if (className.contains("MainActivity")) {
            return true
        }
        return false
    }

    @Override
    byte[] modifyClass(String className,byte[] classBytes) {
        println '----------- deal with "class" file <' + className + '> -----------'
        ClassReader classReader = new ClassReader(classBytes)
        ClassWriter classWriter = new ClassWriter(classReader,ClassWriter.COMPUTE_MAXS)
        ClassVisitor cv = new ClickRecordClassVisitor(classWriter)
        classReader.accept(cv,EXPAND_FRAMES)
        return classWriter.toByteArray()
    }


}