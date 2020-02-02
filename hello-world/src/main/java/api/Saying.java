package com.helloworld.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Saying{
  private long id;
  private String content;

  public Saying(){

  }

  public Saying(long id, String content){
    this.id = id;
    this.content = content;
  }

  @JsonProperty
  public long getId(){
    return this.id;
  }

  @JsonProperty
  public String getContent(){
    return this.content;
  }
}
