package com.emazon.UserMicroservice.configuration.util;

import com.emazon.UserMicroservice.domain.exception.*;
import com.emazon.UserMicroservice.domain.util.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/invalid-document")
    public void triggerInvalidDocumentException() throws InvalidDocumentException {
        throw new InvalidDocumentException(Constants.IDENTIFICATION_MUST_BE_NUMERIC);
    }

    @GetMapping("/invalid-email")
    public void triggerInvalidEmailException() throws InvalidEmailException {
        throw new InvalidDocumentException(Constants.INVALID_EMAIL_FORMAT);
    }

    @GetMapping("/invalid-phone")
    public void triggerInvalidPhoneException() throws InvalidPhoneException {
        throw new InvalidPhoneException(Constants.INVALID_PHONE_FORMAT);
    }

    @GetMapping("/mandatory-field")
    public void triggerMandatoryFieldException() throws MandatoryFieldException {
        throw new MandatoryFieldException(Constants.EMAIL_MANDATORY);
    }

    @GetMapping("/underage-user")
    public void triggerUnderageUserException() throws UnderageUserException {
        throw new UnderageUserException(Constants.USER_MUST_HAVE_EIGHTEEN);
    }

    @GetMapping("already-exists")
    public void triggerUserAlreadyExistsException() throws UserAlreadyExistsException {
        throw new UserAlreadyExistsException(Constants.ALREADY_EXISTS);
    }
}
