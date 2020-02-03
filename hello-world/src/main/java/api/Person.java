package com.helloworld.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person{
  final private long id;
  final private String firstName;
  final private String lastName;

  public Person(){
    this.id = 0;
    this.firstName = null;
    this.lastName = null;
  }

  public Person(long id, String firstName, String lastName){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @JsonProperty
  public long getId(){
    return this.id;
  }

  @JsonProperty
  public String getFirstName(){
    return this.firstName;
  }

  @JsonProperty
  public String getLastName(){
    return this.lastName;
  }
}
