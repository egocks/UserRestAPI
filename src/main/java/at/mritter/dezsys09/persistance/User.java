package at.mritter.dezsys09.persistance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;

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
    @NotBlank(message="{NotBlank.user.email}")
    @Email(message="{Email.user.email}")
    private String email;

    @NotNull(message="{NotNull.user.passwordHash}")
    private byte[] passwordHash;

    @Transient
    @Size(min=5, message="{Size.user.passwordRaw}")
    @JsonProperty("password")
    private String passwordRaw;

    /**
     * Creates a new user with the given email and plain password. The email address must be unique.
     * This constructor is used by jackson to deserialize requests.
     *
     * @param email    unique email of new user
     * @param password plain password of new user
     */
    @JsonCreator
    public User(@JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.email = email;
        this.passwordRaw = password;
        this.passwordHash = this.createHash();
    }

    /**
     * Constructor needed by hibernate to create object via reflection
     */
    private User() {
    }

    /**
     * Hashes a given password by using SHA-256
     *
     * @return hashed password as byte array
     */
    private byte[] createHash() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(this.passwordRaw.getBytes("UTF-8"));
            return md.digest();
        } catch (Exception e) {
            return null;
        }
    }

    public String getEmail() {
        return email;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }


}
