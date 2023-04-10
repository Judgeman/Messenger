package de.judgeman.messenger.repository;

import de.judgeman.messenger.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Paul Richter on Sat 24/07/2021
 */
public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findAll();
    List<Message> findTop10ByOrderByIdDesc();

}
