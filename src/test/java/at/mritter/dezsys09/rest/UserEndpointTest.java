package at.mritter.dezsys09.rest;


import at.mritter.dezsys09.config.JerseyConfiguration;
import at.mritter.dezsys09.persistance.UserRepository;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.core.Application;

import static org.junit.Assert.*;

public class UserEndpointTest extends JerseyTest {

    @Override
    protected Application configure() {
        ApplicationContext context = new AnnotationConfigApplicationContext(UserEndpoint.class, UserRepository.class);
        return new JerseyConfiguration().property("contextConfig", context);
    }

    @Test
    public void test() {
        String response = target("/register").request().get(String.class);
    }

}