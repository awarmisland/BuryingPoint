package com.awarmisland.buryingPoint;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

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
    protected ClickRecordMethodVistor(int api, MethodVisitor mv, int access, String name, String desc) {
        super(api, mv, access, name, desc);
    }

    @Override
    protected void onMethodEnter() {
//        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/awarmisland/asmproject/buryingPoint/DotComponent", "getInstance", "()Lcom/awarmisland/asmproject/buryingPoint/DotComponent;", false);
//        mv.visitVarInsn(Opcodes.ALOAD, 0);
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
//        mv.visitLdcInsn("onCreate");
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/awarmisland/asmproject/buryingPoint/DotComponent", "recordLifecycle", "(Ljava/lang/String;Ljava/lang/String;)V", false);
//        mv.visitVarInsn(ALOAD, 1);

        mv.visitMethodInsn(INVOKESTATIC, "com/awarmisland/asmproject/buryingPoint/DotComponent", "getInstance", "()Lcom/awarmisland/asmproject/buryingPoint/DotComponent;", false);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/awarmisland/asmproject/buryingPoint/DotComponent", "recordViewClick", "(Landroid/view/View;)V", false);
        super.onMethodEnter();
    }
}
