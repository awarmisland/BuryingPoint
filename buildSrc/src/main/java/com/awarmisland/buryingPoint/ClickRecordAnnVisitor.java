package com.awarmisland.buryingPoint;


import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

import static com.awarmisland.config.Config.DOT_PATH;
import static org.objectweb.asm.Opcodes.ASM5;

public class ClickRecordAnnVisitor extends MethodVisitor{
    private boolean isAnnotation;
    private String methodName;
    /**
     * Creates a new {@link AdviceAdapter}.
     *
     * @param api    the ASM API version implemented by this visitor. Must be one
     *               of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @param mv     the method visitor to which this adapter delegates calls.
     * @param access the method's access flags (see {@link Opcodes}).
     * @param name   the method's name.
     * @param desc   the method's descriptor (see {@link Type Type}).
     */
    protected ClickRecordAnnVisitor(MethodVisitor mv, int access, String name, String desc) {
        super(ASM5,mv);
        this.methodName = name;
    }

    @Override
    public void visitParameter(String name, int access) {
        super.visitParameter(name, access);
    }

    @Override
    public void visitCode() {
        if (isAnnotation) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, DOT_PATH, "getInstance", "()L"+DOT_PATH+";", false);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
            mv.visitLdcInsn(methodName);
            mv.visitLdcInsn("111");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, DOT_PATH, "recordMethods", "(Ljava/lang/String;Ljava/lang/String;)V", false);
            mv.visitParameter("urlStr",Opcodes.ACC_MANDATED);
        }
        super.visitCode();
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        super.visitVarInsn(opcode, var);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        if("Lcom/awarmisland/aptannotation/RecordClick;".equals(desc)){
            isAnnotation=true;
        }
        return new MAnnotationVisitor(super.visitAnnotation(desc, visible));
    }

    /**
     * 访问注解
     */
    class MAnnotationVisitor extends AnnotationVisitor {

        public MAnnotationVisitor(AnnotationVisitor av) {
            super(ASM5, av);
        }

        @Override
        public void visit(String name, Object value) {
            super.visit(name, value);
        }
    }
}
