package com.awarmisland.buryingPoint;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static com.awarmisland.config.Config.DOT_PATH;

public class LifecycleOnDestroyMethodVisitor extends MethodVisitor {

    public LifecycleOnDestroyMethodVisitor(MethodVisitor mv) {
        super(Opcodes.ASM4, mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        //方法执行前插
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, DOT_PATH, "getInstance", "()L"+DOT_PATH+";", false);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
        mv.visitLdcInsn("onDestroy");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, DOT_PATH, "recordLifecycle", "(Ljava/lang/String;Ljava/lang/String;)V", false);

    }



    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
    }
}
