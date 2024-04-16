package br.gov.bnb.childservice.exceptions;

public class ChildNotFoundException extends RuntimeException {

    private final static String EXCEPTION_MESSAGE = "The employee with id '%d' has no children";

    public ChildNotFoundException(long id) {
        super(String.format(EXCEPTION_MESSAGE, id));
    }
}
