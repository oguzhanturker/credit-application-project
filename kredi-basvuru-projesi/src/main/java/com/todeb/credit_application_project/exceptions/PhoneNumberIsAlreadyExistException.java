package com.todeb.credit_application_project.exceptions;

public class PhoneNumberIsAlreadyExistException extends RuntimeException{
    public PhoneNumberIsAlreadyExistException(String message) {
        super(message);
    }
}
