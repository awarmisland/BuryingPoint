package com.awarmisland.buryingPoint;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static com.awarmisland.config.Config.DOT_PATH;

public class LifecycleOnCreateMethodVisitor extends MethodVisitor {
    public LifecycleOnCreateMethodVisitor(MethodVisitor mv) {
        super(Opcodes.ASM4, mv);
    }

    @Override
    public void visitCode() {
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, DOT_PATH, "getInstance", "()L"+DOT_PATH+";", false);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
        mv.visitLdcInsn("onCreate");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, DOT_PATH, "recordLifecycle", "(Ljava/lang/String;Ljava/lang/String;)V", false);

        super.visitCode();
        //方法执行后插
    }

    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
    }
}
