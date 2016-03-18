package at.mritter.dezsys09.rest;

import at.mritter.dezsys09.persistance.User;
import at.mritter.dezsys09.rest.response.ResponseMessage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import at.mritter.dezsys09.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class UserEndpointTest {

    private RestTemplate restTemplate;

    public UserEndpointTest() {
        this.restTemplate = new RestTemplate();
    }

    @BeforeClass
    public static void startSpring() {
        String[] args = {"--spring.profiles.active=test"};
        Application.main(args);
    }

    @Test
    public void testRegisterSuccess() {
        User user = new User("bla@test.com", "12345");
        ResponseEntity response = restTemplate.postForEntity("http://127.0.0.1:8080/register", user, ResponseMessage.class);
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatusCode().value());
    }

    @Test(expected=HttpClientErrorException.class)
    public void testRegisterTwice() {
        User user1 = new User("bla2@test.com", "12345");
        restTemplate.postForEntity("http://127.0.0.1:8080/register", user1, ResponseMessage.class);
        User user2 = new User("bla2@test.com", "12345");
        restTemplate.postForEntity("http://127.0.0.1:8080/register", user2, ResponseMessage.class);
    }

    @Test(expected=HttpClientErrorException.class)
    public void testRegisterNoPassword() {
        User user4 = new User("bla4@test.com", null);
        restTemplate.postForEntity("http://127.0.0.1:8080/register", user4, ResponseMessage.class);
    }

    @Test(expected=HttpClientErrorException.class)
    public void testRegisterNoEmail() {
        User user = new User(null, "12345");
        restTemplate.postForEntity("http://127.0.0.1:8080/register", user, ResponseMessage.class);
    }

    @Test(expected=HttpClientErrorException.class)
    public void testRegisterNoEmailAndNoPassword() {
        User user = new User(null, null);
        restTemplate.postForEntity("http://127.0.0.1:8080/register", user, ResponseMessage.class);
    }

    @Test(expected=HttpClientErrorException.class)
    public void testRegisterInvalidEmail() {
        User user = new User("bla5", "12345");
        restTemplate.postForEntity("http://127.0.0.1:8080/register", user, ResponseMessage.class);
    }

    @Test(expected=HttpClientErrorException.class)
    public void testRegisterInvalidEmail2() {
        User user = new User("", "12345");
        restTemplate.postForEntity("http://127.0.0.1:8080/register", user, ResponseMessage.class);
    }

    @Test(expected=HttpClientErrorException.class)
    public void testRegisterInvalidPassword() {
        User user = new User("bla6@test.com", "1234");
        restTemplate.postForEntity("http://127.0.0.1:8080/register", user, ResponseMessage.class);
    }

    @Test(expected=HttpClientErrorException.class)
    public void testRegisterInvalidPassword2() {
        User user = new User("bla6@test.com", "");
        restTemplate.postForEntity("http://127.0.0.1:8080/register", user, ResponseMessage.class);
    }

    @Test
    public void testLoginSuccessful() {
        User user = new User("bla3@test.com", "12345");
        ResponseEntity<ResponseMessage> responseRegister = restTemplate.postForEntity("http://127.0.0.1:8080/register", user, ResponseMessage.class);
        assertEquals(Response.Status.CREATED.getStatusCode(), responseRegister.getStatusCode().value());
        ResponseEntity<ResponseMessage> responseLogin = restTemplate.postForEntity("http://127.0.0.1:8080/login", user, ResponseMessage.class);
        assertEquals(Response.Status.OK.getStatusCode(), responseLogin.getStatusCode().value());
    }

    @Test(expected=HttpClientErrorException.class)
    public void testLoginInvalidEmail1() {
        User user = new User("example.com", "12345");
        restTemplate.postForEntity("http://127.0.0.1:8080/login", user, ResponseMessage.class);
    }

    @Test(expected=HttpClientErrorException.class)
    public void testLoginInvalidEmail2() {
        User user = new User("", "12345");
        restTemplate.postForEntity("http://127.0.0.1:8080/login", user, ResponseMessage.class);
    }

    @Test(expected=HttpClientErrorException.class)
    public void testLoginInvalidPassword1() {
        User user = new User("bla@test.com", "1234");
        restTemplate.postForEntity("http://127.0.0.1:8080/login", user, ResponseMessage.class);
    }

    @Test(expected=HttpClientErrorException.class)
    public void testLoginInvalidPassword2() {
        User user = new User("bla@test.com", "");
        restTemplate.postForEntity("http://127.0.0.1:8080/login", user, ResponseMessage.class);
    }

    @Test(expected=HttpClientErrorException.class)
    public void testLoginNoPassword() {
        User user = new User("bla@test.com", null);
        restTemplate.postForEntity("http://127.0.0.1:8080/login", user, ResponseMessage.class);
    }

    @Test(expected=HttpClientErrorException.class)
    public void testLoginNoEmail() {
        User user = new User(null, "123456");
        restTemplate.postForEntity("http://127.0.0.1:8080/login", user, ResponseMessage.class);
    }

    @Test(expected=HttpClientErrorException.class)
    public void testLoginNoEmailAndNoPassword() {
        User user = new User(null, null);
        restTemplate.postForEntity("http://127.0.0.1:8080/login", user, ResponseMessage.class);
    }
}