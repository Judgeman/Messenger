package de.judgeman.messenger.service;

import de.judgeman.messenger.model.User;
import de.judgeman.messenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Paul Richter on Fri 20/08/2021
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isUserAlreadyRegistered(User user)
    {
        User savedUser = userRepository.findById(user.getEmailAddress()).orElse(null);

        return savedUser != null;
    }

    public void saveNewUser(User user) {
        userRepository.save(user);
    }
}
