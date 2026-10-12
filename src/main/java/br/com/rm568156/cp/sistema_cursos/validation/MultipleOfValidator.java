package br.com.rm568156.cp.sistema_cursos.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MultipleOfValidator implements ConstraintValidator<MultipleOf, Integer> {

    private int multiplicador;

    @Override
    public void initialize(MultipleOf annotation) {
        this.multiplicador = annotation.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return value % multiplicador == 0;
    }
}
