package com.music.MusicTribes.controller;

import com.music.MusicTribes.entity.Song;
import com.music.MusicTribes.entity.Tribe;
import com.music.MusicTribes.repository.SongRepository;
import com.music.MusicTribes.repository.TribeRepository;
import com.music.MusicTribes.request.SongRequest;
import com.music.MusicTribes.request.TribeRequest;
import com.music.MusicTribes.service.SongService;
import com.music.MusicTribes.service.TribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/tribe")
public class TribeController {
    private final TribeService tribeService;

    @Autowired
    TribeRepository tribeRepository;

    @Autowired
    public TribeController(TribeService tribeService){
        this.tribeService=tribeService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public ResponseEntity<Tribe> createTribe(@RequestBody TribeRequest tribeRequest){
        tribeService.createTribe(tribeRequest);

        return ResponseEntity.ok().build();

    }

}
