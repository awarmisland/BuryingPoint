package com.awarmisland.buryingPoint.classVisitor;

import com.awarmisland.buryingPoint.annotationVisitor.ClickRecordAnnVisitor;
import com.awarmisland.buryingPoint.methodVisitor.ClickRecordMethodVistor;
import com.awarmisland.utils.AnnotationUtils;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Arrays;

public class ClickRecordClassVisitor extends ClassVisitor {
    private String mClassName;
    private String[] mInterfaces;
    private byte[] classBytes;
    public ClickRecordClassVisitor(ClassVisitor cv,byte[] classBytes) {
        super(Opcodes.ASM5,cv);
        this.classBytes = classBytes;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
//        System.out.println("LifecycleClassVisitor:visit----->started"+name);
        this.mClassName = name;
        this.mInterfaces = interfaces;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//        System.out.println("LifecycleClassVisitor:visitMethod:" + name);
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        String nameDesc = name + desc;
        if ((mInterfaces != null && mInterfaces.length > 0)) {
            if (Arrays.asList(mInterfaces).contains("android/view/View$OnClickListener")
                    && "onClick(Landroid/view/View;)V".equals(nameDesc)) {
                System.out.println("visitMethod deal width "+nameDesc);
                return new ClickRecordMethodVistor(mv, access, name, desc);
            }
        }
        return new ClickRecordAnnVisitor(mv,access,name,desc,classBytes);
    }



    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return super.visitAnnotation(desc, visible);
    }

    @Override
    public void visitEnd() {
//        System.out.println("LifecycleClassVisitor : visit -----> end");
        super.visitEnd();
    }
}
