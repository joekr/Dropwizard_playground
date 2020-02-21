package com.goals.core;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDo {

  private int id;
  private final String email;
  private final String passwordHash;
  private final String confirmationToken;
  private final Timestamp confirmedAt;
  private final Timestamp lastLoginAt;
  private final Timestamp createdAt;
  private final Timestamp modifiedAt;

  public UserDo(int id, String email, String passwordHash, String confirmationToken, Timestamp confirmedAt, Timestamp lastLoginAt, Timestamp createdAt, Timestamp modifiedAt) {
      this.id = id;
      this.email = email;
      this.passwordHash = passwordHash;
      this.confirmationToken = confirmationToken;
      this.confirmedAt = confirmedAt;
      this.lastLoginAt = lastLoginAt;
      this.createdAt = createdAt;
      this.modifiedAt = modifiedAt;
  }
}
