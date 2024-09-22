package com.emazon.UserMicroservice.domain.util;

import com.emazon.UserMicroservice.domain.model.Role;
import com.emazon.UserMicroservice.domain.model.User;

import java.time.LocalDate;

public class DefaultUsers {

    public static final Long ADMIN_ID = 1L;
    public static final String ADMIN_NAME = "Admin";
    public static final String ADMIN_LAST_NAME = "admin";
    public static final Integer ADMIN_ID_DOCUMENT = 11111111;
    public static final String ADMIN_PHONE = "3222222";
    public static final LocalDate ADMIN_DOB = LocalDate.of(1998, 12, 23);
    public static final String ADMIN_EMAIL = "admin@emazon.com";
    public static final String ADMIN_PASSWORD = "12345";
    public static final Role ADMIN_ROLE = DefaultRoles.ADMIN;

    public static final Long ASSISTANT_ID = 2L;
    public static final String ASSISTANT_NAME = "Warehouse ";
    public static final String ASSISTANT_LAST_NAME = "assistant";
    public static final Integer ASSISTANT_ID_DOCUMENT = 22222222;
    public static final String ASSISTANT_PHONE = "2333333";
    public static final LocalDate ASSISTANT_DOB = LocalDate.of(1990, 6, 15);
    public static final String ASSISTANT_EMAIL = "assistant@emazon.com";
    public static final String ASSISTANT_PASSWORD = "12345";
    public static final Role ASSISTANT_ROLE = DefaultRoles.ASSISTANT;

    public static final Long CLIENT_ID = 3L;
    public static final String CLIENT_NAME = "Rich ";
    public static final String CLIENT_LAST_NAME = "richy";
    public static final Integer CLIENT_ID_DOCUMENT = 33333333;
    public static final String CLIENT_PHONE = "232323232";
    public static final LocalDate CLIENT_DOB = LocalDate.of(1995, 5,23);
    public static final String CLIENT_EMAIL = "client@emazon.com";
    public static final String CLIENT_PASSWORD = "12345";
    public static final Role CLIENT_ROLE = DefaultRoles.CLIENT;


    public static final User ADMIN = new User(ADMIN_ID, ADMIN_NAME, ADMIN_LAST_NAME, ADMIN_ID_DOCUMENT, ADMIN_PHONE, ADMIN_DOB, ADMIN_EMAIL,
            ADMIN_PASSWORD, ADMIN_ROLE);
    public static final User ASSISTANT = new User(ASSISTANT_ID, ASSISTANT_NAME, ASSISTANT_LAST_NAME, ASSISTANT_ID_DOCUMENT, ASSISTANT_PHONE, ASSISTANT_DOB, ASSISTANT_EMAIL, ASSISTANT_PASSWORD,
            ASSISTANT_ROLE);
    public static final User CLIENT = new User(CLIENT_ID, CLIENT_NAME, CLIENT_LAST_NAME, CLIENT_ID_DOCUMENT, CLIENT_PHONE, CLIENT_DOB, CLIENT_EMAIL, CLIENT_PASSWORD, CLIENT_ROLE);
}
