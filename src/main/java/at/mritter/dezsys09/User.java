package at.mritter.dezsys09;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.security.MessageDigest;
import java.util.Arrays;

@Entity
public class User {

    @Id
    private String email;

    private byte[] passwordHash;

    /**
     * Creates a new user with the given email and plain password. The email address must be unique.
     * This constructor is used by jackson to deserialize requests.
     *
     * @param email unique email of new user
     * @param password plain password of new user
     */
    @JsonCreator
    public User(@JsonProperty("email") String email, @JsonProperty("password") String password) {
        this(email, createHash(password));
    }

    /**
     * Creates a new user with the given email and hashed password. The email address must be unique.
     *
     * @param email unique email of new user
     * @param passwordHash hashed password of new user
     */
    public User(String email, byte[] passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    /**
     * Constructor needed by hibernate to create object via reflection
     */
    private User() {
    }

    public String getEmail() {
        return email;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
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


}
