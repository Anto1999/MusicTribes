package com.music.MusicTribes.service;

import com.music.MusicTribes.entity.Song;
import com.music.MusicTribes.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SongService {
    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository){
        this.songRepository=songRepository;
    }

    public List<Song> getAllVhs(){
        return songRepository.findAll();
    }

}
