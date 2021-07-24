package de.judgeman.messenger.repository;

import de.judgeman.messenger.model.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Paul Richter on Sat 24/07/2021
 */
public interface MessageRepository extends CrudRepository<Message, Integer> {
}
