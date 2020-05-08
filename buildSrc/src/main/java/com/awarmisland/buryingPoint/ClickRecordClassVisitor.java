package com.awarmisland.buryingPoint;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

public class ClickRecordClassVisitor extends ClassVisitor {
    private String mClassName;
    private String[] mInterfaces;

    public ClickRecordClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM6,cv);
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
            boolean flag = false;
            for(String str:mInterfaces){
                if("android/view/View$OnClickListener".equals(str)){
                    flag=true;
                    break;
                }
            }
            if ((flag && "onClick(Landroid/view/View;)V".equals(nameDesc))) {
                System.out.println("visitMethod deal width "+nameDesc);
                return new ClickRecordMethodVistor(Opcodes.ASM6,mv, access, name, desc);
            }
        }
        return mv;
    }


    @Override
    public void visitEnd() {
//        System.out.println("LifecycleClassVisitor : visit -----> end");
        super.visitEnd();
    }
}
