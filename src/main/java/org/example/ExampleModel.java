package org.example;

import org.example.annotations.ChildAnnotationA;
import org.example.annotations.ChildAnnotationB;
import org.example.annotations.ParentAnnotation;
import org.example.annotations.StandaloneAnnotationA;
import org.example.annotations.StandaloneAnnotationB;
import org.example.annotations.UnincludedAnnotation;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(
        passAnnotations = {
                ParentAnnotation.class,
                StandaloneAnnotationA.class,
                StandaloneAnnotationB.class
        }
)
public interface ExampleModel {
    @ChildAnnotationA
    String attributeWithChildAnnotationA();

    @ChildAnnotationB
    String attributeWithChildAnnotationB();

    @ChildAnnotationA
    @ChildAnnotationB
    String attributeWithChildAnnotationAAndB();

    @StandaloneAnnotationA
    String attributeWithStandaloneAnnotationA();

    @StandaloneAnnotationB
    String attributeWithStandaloneAnnotationB();

    @StandaloneAnnotationA
    @StandaloneAnnotationB
    String attributeWithStandaloneAnnotationAAndB();

    @UnincludedAnnotation
    String attributeWithOtherAnnotation();

    @ChildAnnotationA
    @ChildAnnotationB
    @StandaloneAnnotationA
    @StandaloneAnnotationB
    @UnincludedAnnotation
    String attributeWithAllAnnotations();
}
