package com.emazon.UserMicroservice.adapters.driving.dto.response;

public class AuthenticationResponse {
    private String token;
    public AuthenticationResponse(String token){
        this.token = token;
    }
    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return token;
    }
}
