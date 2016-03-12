package at.mritter.dezsys09.persistance;

import at.mritter.dezsys09.persistance.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}