package org.example;

import org.assertj.core.api.SoftAssertions;
import org.example.annotations.ChildAnnotationA;
import org.example.annotations.ChildAnnotationB;
import org.example.annotations.StandaloneAnnotationA;
import org.example.annotations.StandaloneAnnotationB;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

class ExampleModelTest {

  @Test
  void generatedMethodsHaveExpectedAnnotations() throws Exception {
    SoftAssertions softly = new SoftAssertions();

    checkMethodHasAnnotations(
        softly,
        ImmutableExampleModel.class.getDeclaredMethod("attributeWithChildAnnotationA"),
        ChildAnnotationA.class
    );

    checkMethodHasAnnotations(
        softly,
        ImmutableExampleModel.class.getDeclaredMethod("attributeWithChildAnnotationB"),
        ChildAnnotationB.class
    );

    // this one fails because only one of ChildAnnotationA or ChildAnnotationB is present
    checkMethodHasAnnotations(
        softly,
        ImmutableExampleModel.class.getDeclaredMethod("attributeWithChildAnnotationAAndB"),
        ChildAnnotationA.class,
        ChildAnnotationB.class
    );

    checkMethodHasAnnotations(
        softly,
        ImmutableExampleModel.class.getDeclaredMethod("attributeWithStandaloneAnnotationA"),
        StandaloneAnnotationA.class
    );

    checkMethodHasAnnotations(
        softly,
        ImmutableExampleModel.class.getDeclaredMethod("attributeWithStandaloneAnnotationB"),
        StandaloneAnnotationB.class
    );

    checkMethodHasAnnotations(
        softly,
        ImmutableExampleModel.class.getDeclaredMethod("attributeWithStandaloneAnnotationAAndB"),
        StandaloneAnnotationA.class,
        StandaloneAnnotationB.class
    );

    checkMethodHasAnnotations(
        softly,
        ImmutableExampleModel.class.getDeclaredMethod("attributeWithOtherAnnotation")
    );

    // this one fails because only one of ChildAnnotationA or ChildAnnotationB is present
    checkMethodHasAnnotations(
        softly,
        ImmutableExampleModel.class.getDeclaredMethod("attributeWithAllAnnotations"),
        ChildAnnotationA.class,
        ChildAnnotationB.class,
        StandaloneAnnotationA.class,
        StandaloneAnnotationB.class
    );

    softly.assertAll();
  }

  @SafeVarargs
  private void checkMethodHasAnnotations(
      SoftAssertions softly,
      Method method,
      Class<? extends Annotation>... annotations
  ) {
    List<Class> declaredAnnotations = Arrays
        .stream(method.getDeclaredAnnotations())
        .map(a -> (Class) a.annotationType())
        .toList();

    softly.assertThat(declaredAnnotations)
        .as("annotations of %s#%s",
            method.getDeclaringClass().getName(),
            method.getName())
        .containsExactlyInAnyOrder(annotations);
  }
}
