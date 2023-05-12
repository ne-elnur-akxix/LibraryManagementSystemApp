package net.javaguides.sms.controller;

import net.javaguides.sms.entity.User;
import net.javaguides.sms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping({"/login"})
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping({"/register"})
    public String registerPage(Model model) {
        return "register";
    }

    @GetMapping({"/users"})
    public String usersPage(Model model) {
        model.addAttribute("users", this.userService.findAllUsers());
        log.debug("\n\n\n Registering user: " + this.userService.findAllUsers() + "\n\n\n");
        return "users";
    }

    @PostMapping({"/register"})
    public String postRegister(@ModelAttribute User user) {
        log.debug("Registering user: " + user.toString());
        this.userService.createUser(user);
        return "login";
    }
}