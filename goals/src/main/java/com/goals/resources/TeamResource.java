package com.goals.resources;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import javax.annotation.security.PermitAll;

import com.goals.core.TeamDo;
import com.goals.core.UserDo;
import com.goals.db.TeamDao;
import com.goals.db.UserDao;
import io.dropwizard.auth.Auth;
import com.goals.core.User;
import java.util.List;
import java.util.Optional;

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
}
