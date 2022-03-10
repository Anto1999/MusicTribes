package com.music.MusicTribes.controller;

import com.music.MusicTribes.entity.Playlist;
import com.music.MusicTribes.entity.Tribe;
import com.music.MusicTribes.request.PlaylistRequest;
import com.music.MusicTribes.request.SongRequest;
import com.music.MusicTribes.request.TribeRequest;
import com.music.MusicTribes.service.PlaylistService;
import com.music.MusicTribes.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/playlist")
public class PlaylistController {

    private final PlaylistService playlistService;

    @Autowired
    public  PlaylistController(PlaylistService playlistService){
        this.playlistService=playlistService;
    }
    @PostMapping("{tribeId}/create")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public ResponseEntity<Playlist> createTribe(@RequestBody PlaylistRequest playlistRequest, @PathVariable Long tribeId){
        playlistService.createPlaylist(playlistRequest,tribeId);
        return ResponseEntity.ok().build();

    }

    @PostMapping("{playlistId}/addsong")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public ResponseEntity<Playlist> addSongToPlaylist( @RequestBody SongRequest songRequest, @PathVariable Long playlistId){
        playlistService.addSongToPlaylist(playlistId, songRequest);
        return ResponseEntity.ok().build();

    }

}
