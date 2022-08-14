package com.todeb.credit_application_project.exceptions;

public class IdentityNumberIsAlreadyExistException extends RuntimeException{

    public IdentityNumberIsAlreadyExistException(String message) {
        super(message);
    }
}
