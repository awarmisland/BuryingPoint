package com.awarmisland.aptprocessor;

import com.awarmisland.aptannotation.RecordClick;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class AptProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> supportTypes = new LinkedHashSet<>();
        supportTypes.add(RecordClick.class.getCanonicalName());
        return supportTypes;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
//        System.out.println("--------------- AptProcessor process start --------------- ");
//
//        TypeSpec autoClass = TypeSpec.classBuilder("AutoClass").build();
//        JavaFile javaFile = JavaFile.builder("com.awarmisland.asmproject", autoClass).build();
//
//        try {
//            javaFile.writeTo(processingEnv.getFiler());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        if (!roundEnvironment.processingOver()) {
            Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(RecordClick.class);
            for (Element each : elements) {
                if (each.getKind() == ElementKind.METHOD) {

                }
            }
        } else{

        }
        return false;
    }
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
