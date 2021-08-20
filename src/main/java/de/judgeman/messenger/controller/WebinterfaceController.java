package de.judgeman.messenger.controller;

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

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
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
