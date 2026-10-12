package br.com.rm568156.cp.sistema_cursos.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {MultipleOfValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface MultipleOf {

    int value();

    String message() default "Carga horária deve ser múltipla de 5";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
