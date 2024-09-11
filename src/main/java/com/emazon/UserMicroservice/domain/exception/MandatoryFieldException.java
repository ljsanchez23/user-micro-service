package com.emazon.UserMicroservice.domain.exception;

public class MandatoryFieldException extends RuntimeException {
    public MandatoryFieldException(String message){
        super(message);
    }
}
