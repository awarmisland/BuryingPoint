package com.awarmisland.buryingPoint.methodVisitor;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static com.awarmisland.config.Config.DOT_PATH;

public class LifecycleMethodVisitor extends MethodVisitor {

    private String lifecycleName;

    public LifecycleMethodVisitor(MethodVisitor mv){
        super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        //方法执行后插
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, DOT_PATH, "getInstance", "()L"+DOT_PATH+";", false);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
        mv.visitLdcInsn(lifecycleName);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, DOT_PATH, "recordLifecycle", "(Ljava/lang/String;Ljava/lang/String;)V", false);
    }

    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
    }


    public void setLifecycleName(String lifecycleName) {
        this.lifecycleName = lifecycleName;
    }
}
