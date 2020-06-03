package com.awarmisland.buryingPoint.annotationVisitor;


import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.commons.Method;

import static com.awarmisland.config.Config.DOT_PATH;
import static org.objectweb.asm.Opcodes.ASM5;

public class ClickRecordAnnVisitor extends AdviceAdapter{
    private boolean isAnnotation;
    private String annotationKeyValue;
    private int annotationKeyIndex;
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
    public ClickRecordAnnVisitor(MethodVisitor mv, int access, String name, String desc) {
        super(ASM5,mv,access,name,desc);
        Type[] typs = Type.getArgumentTypes(desc);
        this.methodName = name;
    }


    @Override
    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
        if(isAnnotation&&annotationKeyValue!=null){
            if(annotationKeyValue.equals(name)){
                annotationKeyIndex = index;
            }
        }
        super.visitLocalVariable(name, desc, signature, start, end, index);
    }


    @Override
    public void visitCode() {
        if (isAnnotation) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/awarmisland/android/buryingpoint/buryingPoint/DotComponent", "getInstance", "()Lcom/awarmisland/android/buryingpoint/buryingPoint/DotComponent;", false);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
            mv.visitLdcInsn(methodName);
            mv.visitVarInsn(Opcodes.ALOAD, annotationKeyIndex);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/awarmisland/android/buryingpoint/buryingPoint/DotComponent", "recordMethods", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", false);
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
            annotationKeyValue = (String) value;
            super.visit(name, value);
        }
    }
}
