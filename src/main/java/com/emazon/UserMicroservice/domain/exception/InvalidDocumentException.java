package com.emazon.UserMicroservice.domain.exception;

public class InvalidDocumentException extends RuntimeException{
    public InvalidDocumentException(String message){
        super(message);
    }
}
