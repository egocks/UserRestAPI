package at.mritter.dezsys09.persistance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.security.MessageDigest;

/**
 * This class represents a user entity identified by it's email address.
 * The password is saved as hash in a byte array.
 *
 * @author Mathias Ritter
 * @version 1.0
 */
@Entity
public class User {

    @Id
    @Email(message="{Email.user.email}")
    private String email;

    private byte[] passwordHash;

    /**
     * Creates a new user with the given email and plain password. The email address must be unique.
     * This constructor is used by jackson to deserialize requests.
     *
     * @param email    unique email of new user
     * @param password plain password of new user
     */
    @JsonCreator
    public User(@JsonProperty("email") String email, @JsonProperty("password") String password) {
        this(email, createHash(password));
    }

    /**
     * Creates a new user with the given email and hashed password. The email address must be unique.
     *
     * @param email        unique email of new user
     * @param passwordHash hashed password of new user
     */
    public User(String email, byte[] passwordHash) {
        if (email == null || passwordHash == null)
            throw new IllegalArgumentException("The email address and password must not be null");
        this.email = email;
        this.passwordHash = passwordHash;
    }

    /**
     * Constructor needed by hibernate to create object via reflection
     */
    private User() {
    }

    /**
     * Hashes a given password by using SHA-256
     *
     * @param password plain password
     * @return hashed password as byte array
     */
    private static byte[] createHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));
            return md.digest();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmail() {
        return email;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }


}
