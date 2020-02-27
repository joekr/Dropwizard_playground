package com.goals.resources;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import javax.annotation.security.PermitAll;

import com.goals.api.Goal;
import com.goals.core.GoalDo;
import com.goals.core.TeamDo;
import com.goals.db.GoalDao;
import com.goals.db.TeamDao;
import com.goals.db.UserDao;
import io.dropwizard.auth.Auth;
import com.goals.core.User;;import java.util.List;
import java.util.Optional;

// Dummy class for now to test auth
@Path("/goals")
@Produces(MediaType.APPLICATION_JSON)
public class GoalResource {
  private final GoalDao goalDao;

  public GoalResource(GoalDao goalDao){
    this.goalDao = goalDao;
  }

  @POST
  @PermitAll
  public Response createGoal(@Auth User user, @Valid Goal goal) {
    final Optional<GoalDo> goalDo = this.goalDao.create(goal, user.getId());
    if (goalDo.isPresent()) {
      return Response.status(Status.OK).entity(goalDo).build();
    } else {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GET
  @PermitAll
  public Response listGoals(@Auth User user) {
    final List<GoalDo> goalDo = this.goalDao.findById(user.getId());
    return Response.status(Status.OK).entity(goalDo).build();
  }
}
