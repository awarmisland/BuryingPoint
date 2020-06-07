package com.awarmisland.buryingPoint.annotationVisitor;

import com.awarmisland.bean.MethodArgsBean;
import com.awarmisland.utils.AnnotationUtils;
import com.awarmisland.utils.ListUtils;
import com.awarmisland.utils.StringUtils;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClickRecordAnnVisitor extends AdviceAdapter{
    private boolean isAnnotation;
    private String methodName;
    private List<String> annoKeys;
    private byte[] classBytes;
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
    public ClickRecordAnnVisitor(MethodVisitor mv, int access, String name, String desc,byte[] classBytes) {
        super(ASM5,mv,access,name,desc);
        this.methodName = name;
        this.classBytes = classBytes;
        annoKeys = new ArrayList<>();
    }

    @Override
    protected void onMethodExit(int opcode) {
        if (isAnnotation&&!ListUtils.isEmptyList(annoKeys)) {
            for(String param : annoKeys){
                setRecordCode(param);
            }
        }
        super.onMethodExit(opcode);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }

    /**
     * record
     * @param param
     */
    private void setRecordCode(String param){
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/awarmisland/android/buryingpoint/buryingPoint/DotComponent", "getInstance", "()Lcom/awarmisland/android/buryingpoint/buryingPoint/DotComponent;", false);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
        mv.visitLdcInsn(methodName);
        mv.visitVarInsn(Opcodes.ALOAD, getIndexOfParam(param));
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/awarmisland/android/buryingpoint/buryingPoint/DotComponent", "recordMethods", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", false);
    }

    /**
     * 获取变量 index
     * @return
     */
    private int getIndexOfParam(String param){
        List<MethodArgsBean> list = AnnotationUtils.getParamsOfMethod(classBytes);
        if(!ListUtils.isEmptyList(list)){
            for(MethodArgsBean bean : list){
                if(!StringUtils.isEmpty(param)&&param.equals(bean.getArgName())){
                    return bean.getIndex();
                }
            }
        }
        return 0;
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
        public AnnotationVisitor visitAnnotation(String name, String desc) {
            return super.visitAnnotation(name, desc);
        }

        @Override
        public AnnotationVisitor visitArray(String name) {
            AnnotationVisitor visitor =  new AnnotationVisitor(ASM5, av){
                @Override
                public void visit(String name, Object value) {
                    if(value!=null){
                        annoKeys.add((String) value);
                    }
                }
            };
            visitor.visitArray(name);
            return visitor;
        }

        @Override
        public void visit(String name, Object value) {
            super.visit(name, value);
        }
    }
}
