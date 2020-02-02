package com.helloworld.resource;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.concurrent.atomic.AtomicLong;

import com.helloworld.api.Saying;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

import com.codahale.metrics.annotation.Timed;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource{
  public final String template;
  public final String defaultName;
  public final AtomicLong counter;

  public HelloWorldResource(String template, String defaultString){
    this.template = template;
    this.defaultName = defaultString;
    this.counter = new AtomicLong();
  }

  @GET
  @Timed
  public Saying sayHello(@QueryParam("name") Optional<String> name){
    final String value = String.format(template, name.orElse(defaultName));
    return new Saying(counter.incrementAndGet(), value);
  }

}
