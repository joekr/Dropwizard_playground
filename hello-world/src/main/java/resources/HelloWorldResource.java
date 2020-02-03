package com.helloworld.resource;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.concurrent.atomic.AtomicLong;

import com.helloworld.core.Person;
import com.helloworld.api.Saying;
import com.helloworld.db.PersonDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

import com.codahale.metrics.annotation.Timed;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource{
  public final String template;
  public final String defaultName;
  public final AtomicLong counter;
  private final PersonDao personDao;

  public HelloWorldResource(String template, String defaultString, PersonDao personDao){
    this.template = template;
    this.defaultName = defaultString;
    this.counter = new AtomicLong();
    this.personDao = personDao;
  }

  @GET
  @Timed
  public Saying sayHello(@QueryParam("name") Optional<String> name){
    final String value = String.format(template, name.orElse(defaultName));
    return new Saying(counter.incrementAndGet(), value);
  }

  // TODO: create person

  @GET
  @Path("/{id}")
  @Timed
  public Saying getPerson(@PathParam("id") long id){
    final Person person = personDao.getPerson(id);
    final String value = String.format(template, person.getFirstName(), person.getLastName());
    return new Saying(counter.incrementAndGet(), value);
  }

  // TODO: update person
  // TODO: delete person

}
