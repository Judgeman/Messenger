package de.judgeman.messenger.controller;

import de.judgeman.messenger.model.User;
import de.judgeman.messenger.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Paul Richter on Fri 20/08/2021
 */
@Controller
public class RegistrationController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/registration",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<HttpStatus> createNewUser(@RequestBody User newUser) {

        logger.info("Try to register new user: " + newUser.toString());

        if (userService.isUserAlreadyRegistered(newUser)) {
            // email address is already saved in the database
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        userService.saveNewUser(newUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
