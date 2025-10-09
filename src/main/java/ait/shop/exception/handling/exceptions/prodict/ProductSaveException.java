package ait.shop.exception.handling.exceptions.prodict;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class ProductSaveException extends RuntimeException {

    private final Set<ConstraintViolation<?>> constraintViolations;

    public ProductSaveException(Set<ConstraintViolation<?>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }

    public Set<ConstraintViolation<?>> getConstraintViolations() {
        return constraintViolations;
    }
}
