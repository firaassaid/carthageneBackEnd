package com.tta.carthagene.entities;

public class EmailRequest {

    private String email;

    // Default constructor
    public EmailRequest() {
    }

    // Parameterized constructor
    public EmailRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailRequest{" +
                "email='" + email + '\'' +
                '}';
    }
}
