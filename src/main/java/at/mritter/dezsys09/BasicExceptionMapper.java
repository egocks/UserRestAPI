package at.mritter.dezsys09;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BasicExceptionMapper implements ExceptionMapper<Exception> {

    public Response toResponse(Exception ex) {
        ex.printStackTrace();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                .entity(new ResponseMessage(ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()))
                .type(MediaType.APPLICATION_JSON).
                        build();
    }

}
