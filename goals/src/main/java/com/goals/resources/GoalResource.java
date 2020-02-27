package com.goals.resources;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import javax.annotation.security.PermitAll;

import com.goals.api.Goal;
import com.goals.core.GoalDo;
import com.goals.db.GoalDao;
import io.dropwizard.auth.Auth;
import com.goals.core.User;
;import java.sql.Timestamp;
import java.util.List;
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

  @PATCH
  @PermitAll
  @Path("/{id}")
  public Response createGoal(@Auth User user, @PathParam("id") int id, @Valid Goal goal) {
    final Optional<GoalDo> goalDoOp = this.goalDao.findById(id, user.getId());
    if (!goalDoOp.isPresent()) {
      return Response.status(Status.NOT_FOUND).build();
    }

    final GoalDo goalDo = goalDoOp.get();

//    Feels dirty. I need to find a better way to handle feel merging. Maybe reflection?
    final String name = goal.getName() == null ? goalDo.getName() : goal.getName();
    final String goalBody = goal.getGoal() == null ? goalDo.getGoal() : goal.getGoal();
    Timestamp createdAt = null;
    if (goal.getCompleted() == null) {
      createdAt = goalDo.getCompletedAt();
    } else {
      if (goal.getCompleted()){
        createdAt = new Timestamp(System.currentTimeMillis());
      }
    }

    final Optional<GoalDo> goalDoUpdate = this.goalDao.update(id, goalDo.getUserId(), name, goalBody, createdAt);
    if (goalDoUpdate.isPresent()) {
      return Response.status(Status.OK).entity(goalDoUpdate.get()).build();
    }else{
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GET
  @PermitAll
  public Response listGoals(@Auth User user) {
    final List<GoalDo> goalDo = this.goalDao.findByUserId(user.getId());
    return Response.status(Status.OK).entity(goalDo).build();
  }
}
