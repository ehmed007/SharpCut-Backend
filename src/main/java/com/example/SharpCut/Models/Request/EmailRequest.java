package com.example.SharpCut.Models.Request;

public class EmailRequest {
    String email;

    public EmailRequest() {
        super();
    }

    public EmailRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
