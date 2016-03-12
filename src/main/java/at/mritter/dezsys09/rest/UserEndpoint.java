package at.mritter.dezsys09.rest;

import at.mritter.dezsys09.persistance.User;
import at.mritter.dezsys09.persistance.UserRepository;
import at.mritter.dezsys09.rest.response.ResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class UserEndpoint {

    @Autowired
    private UserRepository repo;

    @POST
    @Path("/register")
    public Response register(@Valid  User user) {
        if (user == null)
            return ResponseCreator.badRequest("Payload is empty");
        if (repo.exists(user.getEmail()))
            return ResponseCreator.badRequest("User with given email address already exists");
        repo.save(user);
        return ResponseCreator.created("Successfully created new user");
    }

    @POST
    @Path("/login")
    public Response login(@Valid User user) {
        if (user == null)
            return ResponseCreator.badRequest("Payload is empty");
        User requestUser = repo.findOne(user.getEmail());
        if (Arrays.equals(requestUser.getPasswordHash(), user.getPasswordHash()))
            return ResponseCreator.ok("Successfully logged in");
        return ResponseCreator.forbidden("Wrong email or password");
    }

}
