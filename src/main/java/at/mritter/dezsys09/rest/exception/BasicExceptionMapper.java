package at.mritter.dezsys09.rest.exception;

import at.mritter.dezsys09.rest.response.ResponseCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Simple Exception Mapper that is used to handle Exceptions in the RESTful endpoints.
 *
 * @author Mathias Ritter
 * @version 1.0
 */
@Provider
public class BasicExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger logger = LoggerFactory.getLogger(BasicExceptionMapper.class);

    /**
     * Creates an error response containing the message of the Exception.
     *
     * @param e The exception that was threw
     * @return Response containing message of the Exception
     */
    public Response toResponse(Exception e) {
        logger.error(e.getMessage());
        return ResponseCreator.internalServerError(e.getMessage());
    }

}
