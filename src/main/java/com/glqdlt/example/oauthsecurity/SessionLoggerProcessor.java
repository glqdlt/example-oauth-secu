package com.glqdlt.example.oauthsecurity;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

//https://www.baeldung.com/java-annotation-processing-builder
//http://pluu.github.io/blog/android/2015/12/24/annotation-processing-api/
@SupportedAnnotationTypes("SessionLogger")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class SessionLoggerProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(SessionLogger.class);

        for(Element e : elements){

            System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

        }

        return true;
    }
}
