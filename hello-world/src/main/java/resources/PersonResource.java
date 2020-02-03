package com.helloworld.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.helloworld.api.Person;
import com.helloworld.db.PersonDao;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
  private final PersonDao personDao;

  public PersonResource(PersonDao personDao){
    this.personDao = personDao;
  }

  // TODO: create person
  @POST
  @Timed
  public Response createPerson(Person person) {
    personDao.create(person.getFirstName(), person.getLastName());
    return Response.status(201).build();
  }

  @GET
  @Path("/{id}")
  @Timed
  public Person getPerson(@PathParam("id") long id){
    final com.helloworld.core.Person person = personDao.getPerson(id);
    return new Person(person.getId(), person.getFirstName(), person.getLastName());
  }

  // TODO: update person
  // TODO: delete person

}
