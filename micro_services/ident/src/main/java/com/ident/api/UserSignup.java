package com.ident.api;

import lombok.Getter;

@Getter
public class UserSignup {

    private String username;
    private String password;
    private String teamName;

    public UserSignup() {
        this.username = null;
        this.password = null;
    }

    public UserSignup(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
