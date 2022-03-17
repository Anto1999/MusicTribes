package com.music.MusicTribes.controller;

import com.music.MusicTribes.entity.User;
import com.music.MusicTribes.jwt.JwtUtils;
import com.music.MusicTribes.repository.UserRepository;
import com.music.MusicTribes.request.LoginRequest;
import com.music.MusicTribes.request.SignupRequest;
import com.music.MusicTribes.response.JwtResponse;
import com.music.MusicTribes.service.UserDetailsImpl;
import com.music.MusicTribes.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/login")
public class MainController {
    private final UserService userService;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;


    @ModelAttribute("user")
    public LoginRequest  loginRequest(){
        return new LoginRequest();
    }

    @Autowired
    public MainController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String showLoginForm(){
        return "login";
    }


    @PostMapping
    public String login(@ModelAttribute("user") LoginRequest loginRequest, HttpServletResponse response){
        var user = userService.getUserFromLogin(loginRequest);
        System.out.println(user.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        System.out.println(jwt);

        Cookie c = new Cookie("Authorization", "Bearer"+jwt);
        response.addCookie(c);

        System.out.println(c.getName());

        if(user==null){
            return "login";
        }

        else {
            return "redirect:api/tribe/all";

        }
    }

}
