package de.judgeman.messenger.service;

import de.judgeman.messenger.model.Message;
import de.judgeman.messenger.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Paul Richter on Sat 24/07/2021
 */
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void saveNewMessage(Message message) {
        messageRepository.save(message);
    }
}
