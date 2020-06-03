package com.awarmisland.utils;

import com.awarmisland.bean.MethodArgsBean;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.objectweb.asm.ClassReader.EXPAND_FRAMES;

public class AnnotationUtils {


    public static boolean isRecordClickAnno(byte[] classBytes){
        final boolean[] isAnnotation = {false};
        try {
            ClassReader classReader = new ClassReader(classBytes);
            classReader.accept(new ClassVisitor(Opcodes.ASM5) {
                @Override
                public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                    return new MethodVisitor(Opcodes.ASM5) {
                        @Override
                        public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                            if("Lcom/awarmisland/aptannotation/RecordClick;".equals(desc)){
                                isAnnotation[0] = true;
                            }
                            return super.visitAnnotation(desc, visible);
                        }
                    };
                }
            },EXPAND_FRAMES);
        }catch (Exception e){
            return isAnnotation[0];
        }
        return isAnnotation[0];
    }


    public static List<MethodArgsBean> getParamsOfMethod(byte[] classBytes){
        List<MethodArgsBean> list = new ArrayList<>();
        try {
            ClassReader classReader = new ClassReader(classBytes);
            classReader.accept(new ClassVisitor(Opcodes.ASM5) {
                @Override
                public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                    return new MethodVisitor(Opcodes.ASM5) {
                        @Override
                        public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
                            MethodArgsBean bean = new MethodArgsBean();
                            bean.setIndex(index);
                            bean.setArgName(name);
                            list.add(bean);
                            super.visitLocalVariable(name, desc, signature, start, end, index);
                        }
                    };
                }
            },EXPAND_FRAMES);
        }catch (Exception e){
        }
        return list;
    }
}
