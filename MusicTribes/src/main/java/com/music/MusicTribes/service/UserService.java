package com.music.MusicTribes.service;

import com.music.MusicTribes.entity.User;
import com.music.MusicTribes.jwt.JwtUtils;
import com.music.MusicTribes.permission.ERole;
import com.music.MusicTribes.permission.Role;
import com.music.MusicTribes.repository.RoleRepository;
import com.music.MusicTribes.repository.UserRepository;
import com.music.MusicTribes.request.LoginRequest;
import com.music.MusicTribes.request.SignupRequest;
import com.music.MusicTribes.response.JwtResponse;
import com.music.MusicTribes.response.MessageResponse;
import lombok.extern.java.Log;
import org.apache.coyote.Response;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtUtils  jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;

    PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository,PasswordEncoder encoder){
        this.encoder=encoder;
        this.userRepository=userRepository;
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User saveUser(SignupRequest signupRequest){
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new MessageDescriptorFormatException("Username is taken");
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new MessageDescriptorFormatException("Email is taken");
        }

        // Create new user's account
        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.getRoles().add(userRole);
        return userRepository.save(user);
    }
    public User getUserFromLogin(LoginRequest loginRequest){
        Optional<User> getUser = userRepository.findByUsername(loginRequest.getUsername());
        User user = getUser.get();
        return user;
    }

    public User getUserFromUsername(UserDetails userDetails){
        Optional<User> getUser = userRepository.findByUsername(userDetails.getUsername());
        User user = getUser.get();
        return  user;
    }


    public UserDetails getUserDetails(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails;
    }






}
