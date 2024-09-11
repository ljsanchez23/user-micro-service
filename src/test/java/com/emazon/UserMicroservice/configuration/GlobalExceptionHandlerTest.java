package com.emazon.UserMicroservice.configuration;

import com.emazon.UserMicroservice.configuration.util.TestController;
import com.emazon.UserMicroservice.domain.util.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(TestController.class)
class GlobalExceptionHandlerTest {
    private final MockMvc mockMvc;

    public GlobalExceptionHandlerTest(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("Should return BAD REQUEST when the document is invalid")
    void shouldReturnBadRequestWhenInvalidDocumentException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test/invalid-document")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.IDENTIFICATION_MUST_BE_NUMERIC));
    }

    @Test
    @DisplayName("Should return BAD REQUEST when the email is invalid")
    void shouldReturnBadRequestWhenInvalidEmailException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test/invalid-email").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.INVALID_EMAIL_FORMAT));
    }

    @Test
    @DisplayName("Should return BAD REQUEST when the phone is invalid")
    void shouldReturnBadRequestWhenInvalidPhoneException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test/invalid-phone").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.INVALID_PHONE_FORMAT));
    }

    @Test
    @DisplayName("Should return BAD REQUEST when a mandatory field is not given")
    void shouldReturnBadRequestWhenMandatoryFieldException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test/mandatory-field").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.EMAIL_MANDATORY));
    }

    @Test
    @DisplayName("Should return BAD REQUEST when the user does not have the minimum age")
    void shouldReturnBadRequestWhenUnderageUserException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test/underage-user")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.USER_MUST_HAVE_EIGHTEEN));
    }

    @Test
    @DisplayName("Should return BAD REQUEST when the user already exists")
    void shouldReturnBadRequestWhenUserAlreadyExistsException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test/already-exists")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.ALREADY_EXISTS));
    }

}
