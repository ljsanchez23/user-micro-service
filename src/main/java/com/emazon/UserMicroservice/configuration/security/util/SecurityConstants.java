package com.emazon.UserMicroservice.configuration.security.util;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class SecurityConstants {

    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_SECRET_KEY = System.getenv("SECRET_KEY");
    public static final String JWT_USER_NOT_FOUND = "User not found with the email: ";
    public static final String JWT_ID = "emazon";
    public static final String JWT_AUTHORITIES = "authorities";
    public static final Long JWT_TIME_EXPIRATION =  43200000L;
    public static final String JWT_MALFORMED_TOKEN = "JWT token is malformed.";
    public static final String JWT_EXPIRED_TOKEN = "JWT token has expired.";
    public static final String JWT_NOT_SUPPORTED = "JWT token is not supported.";
    public static final String JWT_EMPTY_CLAIM = "JWT claim is empty.";
    public static final String DATA_INITIALIZER_PATH = "/dataInitializer/**";
    public static final String LOGIN_URL = "/login";
    public static final String ADMIN_ROLE = "ADMIN";
    public static final String CREATE_USER_URL = "/user";
    public static final String THE_USER = "The user ";
    public static final String DOES_NOT_EXISTS = " does not exists";
    public static final String USER_ID = "userId";
    public static final String APPLICATION_JSON = "application/json";
    public static final String WRITER = "{\"error\": \"Unauthorized\", \"message\": \"Invalid or missing token.\"}";




    public static SecretKey getSignedKey(String secretKey){
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
