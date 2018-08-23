package at.mritter.dezsys09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is used to initialize the spring boot application via the main method.
 *
 * @author Mathias Ritter
 * @version 1.0
 */
@SpringBootApplication
public class Application {

    /**
     * Runs a new spring boot application, no args required.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("Test change");
    }
}

