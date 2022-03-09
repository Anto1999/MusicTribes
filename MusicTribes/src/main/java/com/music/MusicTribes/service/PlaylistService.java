package com.music.MusicTribes.service;

import com.music.MusicTribes.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository){
        this.playlistRepository=playlistRepository;
    }
}
