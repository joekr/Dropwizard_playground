package com.goals;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import javax.validation.Valid;
import io.dropwizard.db.DataSourceFactory;

public class GoalsConfiguration extends Configuration {
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @NotNull
    private String jwtSecret;

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory(){
        return this.database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }

    @JsonProperty("jwtSecret")
    public String getJWTSecret(){
        return this.jwtSecret;
    }

    @JsonProperty("jwtSecret")
    public void setJWTSecret(String jwtSecret){
        this.jwtSecret = jwtSecret;
    }
}
