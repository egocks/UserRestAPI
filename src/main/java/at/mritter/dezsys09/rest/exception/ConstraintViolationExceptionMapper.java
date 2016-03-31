package at.mritter.dezsys09.rest.exception;

import at.mritter.dezsys09.rest.response.ResponseUtil;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * This exception mapper is used to map ConstraintViolationException to a proper JSON response.
 * ConstraintViolationException may occur when validating an object using bean validation.
 *
 * @author Mathias Ritter
 * @version 1.0
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    /**
     * @see ExceptionMapper#toResponse(Throwable)
     */
    @Override
    public Response toResponse(ConstraintViolationException e) {
        StringBuilder sb = new StringBuilder();
        String comma = "";
        for (ConstraintViolation cv : e.getConstraintViolations()) {
            sb.append(comma);
            sb.append(cv.getMessage());
            comma = ", ";
        }
        return ResponseUtil.badRequest(sb.toString());
    }
}
