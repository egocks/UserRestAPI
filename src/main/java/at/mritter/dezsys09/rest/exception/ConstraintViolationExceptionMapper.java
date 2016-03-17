package at.mritter.dezsys09.rest.exception;

import at.mritter.dezsys09.rest.response.ResponseCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger logger = LoggerFactory.getLogger(ConstraintViolationExceptionMapper.class);

    @Override
    public Response toResponse(ConstraintViolationException e) {
        StringBuilder sb = new StringBuilder();
        String comma = "";
        for (ConstraintViolation cv : e.getConstraintViolations()) {
            sb.append(comma);
            sb.append(cv.getMessage());
            comma = ", ";
        }
        return ResponseCreator.badRequest(sb.toString());
    }
}
