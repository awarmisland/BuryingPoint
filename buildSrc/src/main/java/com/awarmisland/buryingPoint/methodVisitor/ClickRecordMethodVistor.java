package com.awarmisland.buryingPoint.methodVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

import static com.awarmisland.config.Config.DOT_PATH;

public class ClickRecordMethodVistor  extends AdviceAdapter {


    /**
     * Creates a new {@link AdviceAdapter}.
     *
     * @param api    the ASM API version implemented by this visitor. Must be one
     *               of {@link Opcodes#ASM4}, {@link Opcodes#ASM5} or {@link Opcodes#ASM6}.
     * @param mv     the method visitor to which this adapter delegates calls.
     * @param access the method's access flags (see {@link Opcodes}).
     * @param name   the method's name.
     * @param desc   the method's descriptor (see {@link Type Type}).
     */
    public ClickRecordMethodVistor(MethodVisitor mv, int access, String name, String desc) {
        super(ASM5, mv, access, name, desc);
    }

    @Override
    protected void onMethodEnter() {
        mv.visitMethodInsn(INVOKESTATIC, DOT_PATH, "getInstance", "()L"+DOT_PATH+";", false);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKEVIRTUAL, DOT_PATH, "recordViewClick", "(Landroid/view/View;)V", false);
        super.onMethodEnter();
    }
}
