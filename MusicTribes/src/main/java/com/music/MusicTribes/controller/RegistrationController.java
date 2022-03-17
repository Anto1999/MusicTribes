package com.music.MusicTribes.controller;

import com.music.MusicTribes.request.SignupRequest;
import com.music.MusicTribes.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private UserService userService;
    public RegistrationController(UserService userService){
        this.userService=userService;
    }

    @ModelAttribute("user")
    public SignupRequest signupRequest(){
        return new SignupRequest();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") SignupRequest signupRequest){
        var user = userService.saveUser(signupRequest);
        if(user==null){
            return "registration";
        }
        return "redirect:/login";
    }
}
