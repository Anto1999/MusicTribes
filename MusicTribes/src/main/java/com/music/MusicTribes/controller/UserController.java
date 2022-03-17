package com.music.MusicTribes.controller;

import com.music.MusicTribes.entity.User;
import com.music.MusicTribes.jwt.JwtUtils;
import com.music.MusicTribes.repository.UserRepository;
import com.music.MusicTribes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "musictribes/user")
public class UserController {
    private final UserService userService;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserRepository userRepository;

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

    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    @GetMapping("/me")
    public String userView(Model model){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user",userService.getUserFromUsername(userDetails));
        return "userprofile";
    }




}
