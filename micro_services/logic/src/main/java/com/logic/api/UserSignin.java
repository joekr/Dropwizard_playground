package com.logic.api;

import lombok.Getter;

@Getter
public class UserSignin {
    private String username;
    private String password;

    public UserSignin() {
        this.username = null;
        this.password = null;
    }

    public UserSignin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
