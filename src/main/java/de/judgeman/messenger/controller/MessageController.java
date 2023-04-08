package de.judgeman.messenger.controller;

import de.judgeman.messenger.model.Message;
import de.judgeman.messenger.service.MessageService;
import de.judgeman.messenger.service.SettingEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private SettingEntryService settingEntryService;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/messages")
    public void messageReceiving(Message message) throws Exception {

        messageService.saveNewMessage(message);

        simpMessagingTemplate.convertAndSend("/messages/receive/" + message.getReceiver(), message);
        if (message.getReceiver() != null && !message.getReceiver().equals(message.getSender())) {
            simpMessagingTemplate.convertAndSend("/messages/receive/" + message.getSender(), message);
        }

        int newCounterValue = settingEntryService.incrementMessageCounter();
        logger.info("Nachrichten insgesamt versendet: " + newCounterValue);
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
