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

import com.goals.api.UserInvite;
import com.goals.api.UserSignup;
import com.goals.core.GoalDo;
import com.goals.core.TeamDo;
import com.goals.core.UserDo;
import com.goals.db.TeamDao;
import com.goals.db.UserDao;
import io.dropwizard.auth.Auth;
import com.goals.core.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
public class TeamResource {
    private final UserDao userDao;
    private final TeamDao teamDao;

    public TeamResource(UserDao userDao, TeamDao teamDao){
        this.userDao = userDao;
        this.teamDao = teamDao;
    }

    @GET
    @PermitAll
    public Response listTeams(@Auth User user) {
        final Optional<TeamDo> teamDo = this.teamDao.findById(user.getTeamId());
        if (teamDo.isPresent()) {
            return Response.status(Status.OK).entity(teamDo.get()).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @GET
    @PermitAll
    @Path("/users")
    public Response listTeamUsers(@Auth User user) {
        final List<UserDo> userDo = this.teamDao.findAllUsersByTeamId(user.getTeamId());
        return Response.status(Status.OK).entity(userDo).build();
    }

    @GET
    @PermitAll
    @Path("/goals")
    public Response listTeamGoals(@Auth User user) {
        final List<GoalDo> goalDo = this.teamDao.findAllGoalsByTeamId(user.getTeamId());
        return Response.status(Status.OK).entity(goalDo).build();
    }

    @POST
    @PermitAll
    @Path("/invite")
    public Response inviteUserToTeam(@Auth User user, @Valid UserInvite newUser) {
//        TODO: gen new password and send email (for production)
        final Optional<TeamDo> teamDo = this.teamDao.findById(user.getTeamId());

        if (!teamDo.isPresent()) {
            return Response.status(Status.NOT_FOUND).build();
        }
//        TODO: move this and UserResource into a share class
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt(12));
        final String token = UUID.randomUUID().toString();
        final Optional<UserDo> userDo = this.userDao.create(newUser.getUsername().toLowerCase(), hashed, teamDo.get().getId(), token);

        if (userDo.isPresent()) {
            return Response.status(Status.CREATED).entity(userDo).build();
        } else {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
