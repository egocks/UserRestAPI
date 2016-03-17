package at.mritter.dezsys09.rest.response;

import javax.ws.rs.core.Response;

public class ResponseCreator {

    private static Response build(int code, String message) {
        return Response.status(code).entity(new ResponseMessage(message, code)).build();
    }

    public static Response ok(String message) {
        return build(Response.Status.OK.getStatusCode(), message);
    }

    public static Response created(String message) {
        return build(Response.Status.CREATED.getStatusCode(), message);
    }

    public static Response badRequest(String message) {
        return build(Response.Status.BAD_REQUEST.getStatusCode(), message);
    }

    public static Response forbidden(String message) {
        return build(Response.Status.FORBIDDEN.getStatusCode(), message);
    }

    public static Response internalServerError(String message) {
        return build(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), message);
    }

}
