package de.judgeman.messenger.controller;

import de.judgeman.messenger.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

/**
 * Created by Paul Richter on Mon 19/07/2021
 */
@Controller
public class MessageController {
    @MessageMapping("/messages")
    @SendTo("/messages/receive")
    public Message messageReceiving(Message message) throws Exception {
        return new Message(HtmlUtils.htmlEscape(message.getName()), HtmlUtils.htmlEscape(message.getText()));
    }
}
