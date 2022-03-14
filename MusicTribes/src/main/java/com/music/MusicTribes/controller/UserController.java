package com.music.MusicTribes.controller;

import com.music.MusicTribes.entity.User;
import com.music.MusicTribes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping(path = "musictribes/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();

    }
    @GetMapping("/all")
    public String usersView(Model model){
        model.addAttribute("listOfUsers",userService.getUsers());
        return "users";
    }




}
