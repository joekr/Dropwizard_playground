package com.helloworld.core;

import javax.validation.constraints.NotEmpty;

public class Person {
    private int id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public String getFullName() {
      return String.format("%s %s", this.firstName, this.lastName);
    }

    public Person() {
      super();
    }

    public Person(int id, String firstName, String lastName) {
      super();
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
    }
  }
