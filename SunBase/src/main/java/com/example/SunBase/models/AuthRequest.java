package com.example.SunBase.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String login_id;
    private String password;

    public AuthRequest(String login_id, String password) {
        this.login_id = login_id;
        this.password = password;
    }


}
