package com.music.MusicTribes.service;

import com.music.MusicTribes.entity.Tribe;
import com.music.MusicTribes.entity.User;
import com.music.MusicTribes.repository.TribeRepository;
import com.music.MusicTribes.repository.UserRepository;
import com.music.MusicTribes.request.TribeRequest;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
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
        tribe.getMembers().add(user);
        return tribeRepository.save(tribe);
    }

    public Tribe joinToTribe(Long tribeId){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> getUser = userRepository.findByUsername(userDetails.getUsername());
        User user = getUser.get();
        Optional<Tribe> getTribe = tribeRepository.findById(tribeId);
        Tribe tribe = getTribe.get();
        tribe.getMembers().add(user);

        return tribeRepository.save(tribe);

    }
    public List<Tribe> getTribes(){
        return tribeRepository.findAll();
    }
}
