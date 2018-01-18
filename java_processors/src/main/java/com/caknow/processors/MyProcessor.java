package com.caknow.processors;

import com.caknow.annotations.RxResourceManager;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {

    private Filer filer;
    private static final List<Class<? extends Annotation>> SupportAnnotations = Arrays.asList(
            RxResourceManager.class
    );

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }

    /**
     * 这里必须指定，这个注解处理器是注册给哪个注解的。注意，它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称
     *
     * @return 注解器所支持的注解类型集合，如果没有这样的类型，则返回一个空集合
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        for (Class<? extends Annotation> annotation : getSupportedAnnotations()) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.addAll(SupportAnnotations);
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // roundEnv.getElementsAnnotatedWith()返回使用给定注解类型的元素
        for (Element element : roundEnvironment.getElementsAnnotatedWith(RxResourceManager.class)) {
            System.out.println("------------------------------");
            // 判断元素的类型为Class
            if (element.getKind() == ElementKind.CLASS) {
                // 显示转换元素类型
                TypeElement typeElement = (TypeElement) element;
                // 输出元素名称
                System.out.println(typeElement.getSimpleName());
                System.out.println(typeElement.getQualifiedName());
                // 输出注解属性值
                System.out.println(typeElement.getAnnotation(RxResourceManager.class).value());

                typeElement.getEnclosedElements();
                // 创建main方法
                MethodSpec main = MethodSpec.methodBuilder("onLazyLoad")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .returns(void.class)
                        .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                        .build();
                // 创建HelloWorld类
                String className = typeElement.getSimpleName().toString() + "_Fuck";
                TypeSpec lazyLoadClass = TypeSpec.classBuilder(className)
                        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                        .addMethod(main)
                        .build();

                try {
                    String packageNameAll = typeElement.getQualifiedName().toString();
                    System.out.println(packageNameAll);

                    String packageName = packageNameAll.substring(0, packageNameAll.lastIndexOf("."));
                    System.out.println(packageName);

                    // 生成 com.example.HelloWorld.java
                    JavaFile javaFile = JavaFile.builder(packageName, lazyLoadClass)
                            .addFileComment(" This codes are generated automatically. Do not modify!")
                            .build();
                    //　生成文件
                    javaFile.writeTo(filer);
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                try {
//                    // 生成 com.example.HelloWorld.java
//                    JavaFile javaFile = JavaFile.builder("com.example", typeElement)
//                            .addFileComment(" This codes are generated automatically. Do not modify!")
//                            .build();
//                    //　生成文件
//                    javaFile.writeTo(filer);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
            System.out.println("------------------------------");
        }
        return false;
    }
}
