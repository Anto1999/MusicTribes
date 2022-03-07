package com.music.MusicTribes.service;

import com.music.MusicTribes.entity.User;
import com.music.MusicTribes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
