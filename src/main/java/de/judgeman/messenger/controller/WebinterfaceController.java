package de.judgeman.messenger.controller;

import de.judgeman.messenger.service.MessageService;
import de.judgeman.messenger.service.SettingEntryService;
import de.judgeman.messenger.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Paul Richter on Mon 19/07/2021
 */
@Controller
public class WebinterfaceController {

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    private SettingEntryService settingEntryService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private WebSocketService webSocketService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("messageCounter", settingEntryService.getMessageCounter());
        model.addAttribute("applicationVersion", settingEntryService.getApplicatonVersion());
        model.addAttribute("lastMessages", messageService.getNewestMessages());
        model.addAttribute("activeConnections", webSocketService.getActiveConnections());

        return "home";
    }

    @GetMapping("/messenger")
    public String onlineClient(Model model) {
        return "onlineClient";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }
}
