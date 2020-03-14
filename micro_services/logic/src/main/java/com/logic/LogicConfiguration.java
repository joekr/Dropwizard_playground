package com.logic;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class LogicConfiguration extends Configuration {

    @NotNull
    private String identHost;

    @JsonProperty("identHost")
    public String getIdentHost(){
        return this.identHost;
    }

    @JsonProperty("identHost")
    public void setIdentHost(String identHost) {
        this.identHost = identHost;
    }

}
