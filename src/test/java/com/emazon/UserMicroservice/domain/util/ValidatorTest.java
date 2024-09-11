package com.emazon.UserMicroservice.domain.util;

import com.emazon.UserMicroservice.domain.exception.*;
import com.emazon.UserMicroservice.domain.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorTest {

    @Test
    @DisplayName("Should throw MandatoryFieldException when name is null")
    void shouldThrowMandatoryFieldExceptionWhenNameIsNull() {
        User user = new User(1L, null, "LastName", 123456789, "1234567890", LocalDate.of(2000, 1, 1), "test@example.com", "password", null);

        assertThrows(MandatoryFieldException.class, () -> Validator.validateUser(user));
    }

    @Test
    @DisplayName("Should throw MandatoryFieldException when last name is empty")
    void shouldThrowMandatoryFieldExceptionWhenLastNameIsEmpty() {
        User user = new User(1L, "FirstName", " ", 123456789, "1234567890", LocalDate.of(2000, 1, 1), "test@example.com", "password", null);

        assertThrows(MandatoryFieldException.class, () -> Validator.validateUser(user));
    }

    @Test
    @DisplayName("Should throw InvalidEmailException when email format is invalid")
    void shouldThrowInvalidEmailExceptionWhenEmailFormatIsInvalid() {
        User user = new User(1L, "FirstName", "LastName", 123456789, "1234567890", LocalDate.of(2000, 1, 1), "invalid-email", "password", null);

        assertThrows(InvalidEmailException.class, () -> Validator.validateUser(user));
    }

    @Test
    @DisplayName("Should throw InvalidPhoneException when phone number is too long")
    void shouldThrowInvalidPhoneExceptionWhenPhoneNumberIsTooLong() {
        User user = new User(1L, "FirstName", "LastName", 123456789, "12345678901234567890", LocalDate.of(2000, 1, 1), "test@example.com", "password", null);

        assertThrows(InvalidPhoneException.class, () -> Validator.validateUser(user));
    }

    @Test
    @DisplayName("Should throw InvalidPhoneException when phone number format is invalid")
    void shouldThrowInvalidPhoneExceptionWhenPhoneNumberFormatIsInvalid() {
        User user = new User(1L, "FirstName", "LastName", 123456789, "abc123", LocalDate.of(2000, 1, 1), "test@example.com", "password", null);

        assertThrows(InvalidPhoneException.class, () -> Validator.validateUser(user));
    }

    @Test
    @DisplayName("Should throw InvalidDocumentException when ID document is less than the minimum")
    void shouldThrowInvalidDocumentExceptionWhenIdDocumentIsInvalid() {
        User user = new User(1L, "FirstName", "LastName", -1, "1234567890", LocalDate.of(2000, 1, 1), "test@example.com", "password", null);

        assertThrows(InvalidDocumentException.class, () -> Validator.validateUser(user));
    }

    @Test
    @DisplayName("Should throw UnderageUserException when user is underage")
    void shouldThrowUnderageUserExceptionWhenUserIsUnderage() {
        User user = new User(1L, "FirstName", "LastName", 123456789, "1234567890", LocalDate.of(2010, 1, 1), "test@example.com", "password", null);

        assertThrows(UnderageUserException.class, () -> Validator.validateUser(user));
    }

    @Test
    @DisplayName("Should not throw any exceptions when user data is valid")
    void shouldNotThrowExceptionWhenUserDataIsValid() {
        User user = new User(1L, "FirstName", "LastName", 123456789, "1234567890", LocalDate.of(2000, 1, 1), "test@example.com", "password", null);

        Validator.validateUser(user);
    }
}
