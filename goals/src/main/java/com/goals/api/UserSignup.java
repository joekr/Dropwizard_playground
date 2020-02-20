package com.goals.api;

import lombok.Getter;

public class UserSignup {

  @Getter
  private String username;

  @Getter
  private String password;

  public UserSignup() {
    this.username = null;
    this.password = null;
  }

  public UserSignup(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
