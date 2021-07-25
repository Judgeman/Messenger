package de.judgeman.messenger.controller;

import de.judgeman.messenger.model.Message;
import de.judgeman.messenger.service.MessageService;
import de.judgeman.messenger.service.SettingEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * Created by Paul Richter on Mon 19/07/2021
 */
@Controller
public class MessageController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SettingEntryService settingEntryService;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/messages")
    @SendTo("/messages/receive")
    public Message messageReceiving(Message message) throws Exception {

        Message escapedMessage = new Message(HtmlUtils.htmlEscape(message.getName()), HtmlUtils.htmlEscape(message.getText()));

        int newCounterValue = settingEntryService.incrementMessageCounter();
        logger.info("Nachrichten insgesamt versendet: " + newCounterValue);

        messageService.saveNewMessage(escapedMessage);

        return escapedMessage;
    }

    @RequestMapping(
            value = "/messages/all",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public @ResponseBody List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }
}
