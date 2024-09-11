package com.emazon.UserMicroservice.domain.util;

import com.emazon.UserMicroservice.domain.exception.*;
import com.emazon.UserMicroservice.domain.model.User;

import java.time.LocalDate;
import java.time.Period;

public class Validator {


    public static void validateUser(User user) {
        validateMandatoryFields(user);
        validateEmail(user.getEmail());
        validatePhone(user.getPhone());
        validateIdDocument(user.getIdDocument());
        validateAge(user.getDateOfBirth());
    }

    private static void validateMandatoryFields(User user) {
        checkMandatory(user.getName(), Constants.NAME_MANDATORY);
        checkMandatory(user.getLastName(), Constants.LAST_NAME_MANDATORY);
        checkMandatory(user.getIdDocument(), Constants.ID_DOCUMENT_MANDATORY);
        checkMandatory(user.getPhone(), Constants.PHONE_MANDATORY);
        checkMandatory(user.getDateOfBirth(), Constants.DOB_MANDATORY);
        checkMandatory(user.getEmail(), Constants.EMAIL_MANDATORY);
        checkMandatory(user.getPassword(), Constants.PASSWORD_MANDATORY);
    }

    private static void checkMandatory(Object field, String errorMessage) {
        if (field == null || (field instanceof String str && str.trim().isEmpty())) {
            throw new MandatoryFieldException(errorMessage);
        }
    }

    private static void validateEmail(String email) {
        if (!Constants.EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException(Constants.INVALID_EMAIL_FORMAT);
        }
    }

    private static void validatePhone(String phone) {
        if (phone.length() > Constants.PHONE_MAX_LENGTH) {
            throw new InvalidPhoneException((Constants.PHONE_UNDER_THIRTEEN_CHARACTERS));
        }
        if (!Constants.PHONE_PATTERN.matcher(phone).matches()) {
            throw new InvalidPhoneException(Constants.INVALID_PHONE_FORMAT);
        }
    }

    private static void validateIdDocument(Integer idDocument) {
        if (idDocument < Constants.NUMERIC_VALIDATION) {
            throw new InvalidDocumentException(Constants.IDENTIFICATION_MUST_BE_NUMERIC);
        }
    }

    private static void validateAge(LocalDate dateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(dateOfBirth, currentDate).getYears();

        if (age < Constants.MINIMUM_AGE) {
            throw new UnderageUserException(Constants.USER_MUST_HAVE_EIGHTEEN);
        }
    }
}