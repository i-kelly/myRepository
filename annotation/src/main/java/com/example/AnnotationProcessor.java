package com.example;

import com.google.auto.service.AutoService;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

@AutoService(Processor.class)//自动生成 javax.annotation.processing.IProcessor 文件
@SupportedSourceVersion(SourceVersion.RELEASE_7)//java版本支持
@SupportedAnnotationTypes({//标注注解处理器支持的注解类型
                           "com.example.Router"
                          })
public class AnnotationProcessor
        extends AbstractProcessor {
    public Filer    mFileUtils;//文件相关的辅助类
    public Elements mElementUtils; //元素相关的辅助类
    public Messager mMessager;//日志相关的辅助类
   /* Element
  - VariableElement //一般代表成员变量
  - ExecutableElement //一般代表类中的方法
  - TypeElement //一般代表代表类
  - PackageElement //一般代表Package*/

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv); mFileUtils = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils(); mMessager = processingEnv.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new LinkedHashSet<>();
        annotationTypes.add(Router.class.getCanonicalName()); return annotationTypes;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        new RouterProcessor().process(roundEnv, this);
//        new ApiProcessor().process(roundEnv, this);
        return true;
    }
}
