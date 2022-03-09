package com.music.MusicTribes.service;

import com.music.MusicTribes.entity.Tribe;
import com.music.MusicTribes.entity.User;
import com.music.MusicTribes.repository.TribeRepository;
import com.music.MusicTribes.repository.UserRepository;
import com.music.MusicTribes.request.TribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TribeService {
    private final TribeRepository tribeRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    public TribeService(TribeRepository tribeRepository){
        this.tribeRepository=tribeRepository;
    }

    public Tribe createTribe(TribeRequest tribeRequest){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> getUser = userRepository.findByUsername(userDetails.getUsername());
        User user = getUser.get();
        Tribe tribe = new Tribe();
        tribe.setChieftain(user);
        tribe.setName(tribeRequest.getName());
        tribe.setDescription(tribeRequest.getDescription());
        tribe.setGenre(tribeRequest.getGenre());
        return tribeRepository.save(tribe);
    }
}
