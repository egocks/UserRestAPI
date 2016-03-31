package at.mritter.dezsys09.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository is used to access the User objects in the database.
 *
 * @author Mathias Ritter
 * @version 1.0
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {

}