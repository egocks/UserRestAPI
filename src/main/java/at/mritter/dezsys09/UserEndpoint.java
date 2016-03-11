package at.mritter.dezsys09;

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

    @POST
    @Path("/register")
    public Response register() {
        return Response.status(Response.Status.OK.getStatusCode()).entity("Registrierung").build();
    }


    @POST
    @Path("/login")
    public Response login() {
        return Response.status(Response.Status.OK.getStatusCode()).entity("Login").build();
    }

}
