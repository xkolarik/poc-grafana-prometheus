package br.gov.bnb.employeeservice.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    private final static String EXCEPTION_MESSAGE = "The Employee with id '%d' was not found";

    public EmployeeNotFoundException(long id) {
        super(String.format(EXCEPTION_MESSAGE, id));
    }
}
