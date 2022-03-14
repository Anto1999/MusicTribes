package com.music.MusicTribes.service;

import com.music.MusicTribes.entity.User;
import com.music.MusicTribes.permission.ERole;
import com.music.MusicTribes.permission.Role;
import com.music.MusicTribes.repository.RoleRepository;
import com.music.MusicTribes.repository.UserRepository;
import com.music.MusicTribes.request.SignupRequest;
import com.music.MusicTribes.response.MessageResponse;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


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

    public User userProfile(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> getUser = userRepository.findByUsername(userDetails.getUsername());
        User user = getUser.get();
        return user;
    }

}
