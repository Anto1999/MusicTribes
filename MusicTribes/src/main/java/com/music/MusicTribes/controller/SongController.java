package com.music.MusicTribes.controller;

import com.music.MusicTribes.entity.Song;
import com.music.MusicTribes.repository.SongRepository;
import com.music.MusicTribes.request.SongRequest;
import com.music.MusicTribes.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/song")
public class SongController {
    private final SongService songService;

    @Autowired
    SongRepository songRepository;

    @Autowired
    public SongController(SongService songService){
        this.songService=songService;
    }

    @PostMapping("/create")
    public ResponseEntity<Song> createRental(@RequestBody SongRequest songRequest){
        songService.addSong(songRequest);

        return ResponseEntity.ok().build();

    }

}
