package br.gov.bnb.employeeservice.exceptions;

public class ChildNotFoundException extends RuntimeException {

    private final static String EXCEPTION_MESSAGE = "There is no children for the informed employee";

    public ChildNotFoundException() {
        super(EXCEPTION_MESSAGE);
    }
}
