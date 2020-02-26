package com.goals.api;

import lombok.Getter;

public class UserSignup {

  @Getter
  private String username;

  @Getter
  private String password;

  @Getter
  private String teamName;

  public UserSignup() {
    this.username = null;
    this.password = null;
    this.teamName = null;
  }

  public UserSignup(String username, String password, String teamName) {
    this.username = username;
    this.password = password;
    this.teamName = teamName;
  }
}
