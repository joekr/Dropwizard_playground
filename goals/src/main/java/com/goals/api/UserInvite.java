package com.goals.api;

import lombok.Getter;

public class UserInvite {

    @Getter
    private String username;

    @Getter
    private String password;

    public UserInvite() {
        this.username = null;
        this.password = null;
    }

    public UserInvite(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
