package at.mritter.dezsys09.config;

import at.mritter.dezsys09.rest.exception.BasicExceptionMapper;
import at.mritter.dezsys09.rest.exception.JsonMappingExceptionMapper;
import at.mritter.dezsys09.rest.UserEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * This class is used to register the RESTful endpoints and exception mappers in order to integrate Spring with Jersey.
 *
 * @author Mathias Ritter
 * @version 1.0
 */
@Configuration
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        // register restful endpoint
        this.register(UserEndpoint.class);
        // register exception mapper for json mapping exceptions
        this.register(JsonMappingExceptionMapper.class);
        // register simple exception mapper for any type of exception
        this.register(BasicExceptionMapper.class);
    }
}
