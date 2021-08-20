package de.judgeman.messenger.repository;

import de.judgeman.messenger.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Paul Richter on Fri 20/08/2021
 */
public interface UserRepository extends CrudRepository<User, String> {

}
