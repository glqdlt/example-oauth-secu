package com.glqdlt.example.oauthsecurity;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.HashSet;
import java.util.Set;

// 얘를 왜부로 빼야함. jar로 만들어서
// 이유는 maven 이 사전에 컴파일단계에서 이 jar를 호출해주어야 해서
//https://www.baeldung.com/java-annotation-processing-builder
//http://pluu.github.io/blog/android/2015/12/24/annotation-processing-api/
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class SessionLoggerProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(SessionLogger.class);

        for (Element e : elements) {

            System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

        }

        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> s = new HashSet<>();
        s.add(SessionLogger.class.getName());
        return s;

    }


}
