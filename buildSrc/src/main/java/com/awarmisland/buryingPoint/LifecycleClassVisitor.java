package com.awarmisland.buryingPoint;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LifecycleClassVisitor extends ClassVisitor {
    private String mClassName;

    public LifecycleClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5,cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
//        System.out.println("LifecycleClassVisitor:visit----->started"+name);
        this.mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) { ;
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        LifecycleMethodVisitor method  = new LifecycleMethodVisitor(mv);
        //匹配FragmentActivity
        if ("onResume".equals(name)
                ||"onStop".equals(name)) {
            //处理onCreate
            method.setLifecycleName(name);
            return method;
        }
        return mv;
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
