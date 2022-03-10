package com.music.MusicTribes.service;

import com.music.MusicTribes.entity.Song;
import com.music.MusicTribes.repository.SongRepository;
import com.music.MusicTribes.request.SongRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository){
        this.songRepository=songRepository;
    }

    public List<Song> getAllSongs(){
        return songRepository.findAll();
    }

    public Song addSong(SongRequest songRequest){
        Song song = new Song();
        song.setName(songRequest.getName());
        song.setArtist(songRequest.getArtist());
        song.setUrl(songRequest.getUrl());
        return songRepository.save(song);
    }


}
