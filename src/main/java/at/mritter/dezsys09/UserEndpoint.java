package at.mritter.dezsys09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class UserEndpoint {

    @Autowired
    private UserRepository repo;

    @POST
    @Path("/register")
    public Response register(User user) {
        if (user == null)
            return Response
                    .status(Response.Status.BAD_REQUEST.getStatusCode())
                    .entity(new ResponseMessage("Email address or password missing"))
                    .build();
        if (repo.exists(user.getEmail()))
            return Response
                    .status(Response.Status.BAD_REQUEST.getStatusCode())
                    .entity(new ResponseMessage("User with given email address already exists"))
                    .build();
        repo.save(user);
        return Response
                .status(Response.Status.OK.getStatusCode())
                .build();
    }

    @POST
    @Path("/login")
    public Response login(User user) {
        User dbUser = repo.findOne(user.getEmail());
        if (dbUser.equals(user))
            return Response
                    .status(Response.Status.OK.getStatusCode())
                    .entity(new ResponseMessage("Successfully logged in"))
                    .build();
        return Response
                .status(Response.Status.UNAUTHORIZED.getStatusCode())
                .entity(new ResponseMessage("Wrong email or password"))
                .build();
    }

}
