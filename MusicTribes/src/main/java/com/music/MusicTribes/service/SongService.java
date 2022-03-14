package com.music.MusicTribes.service;

import com.music.MusicTribes.entity.Playlist;
import com.music.MusicTribes.entity.Song;
import com.music.MusicTribes.entity.Tribe;
import com.music.MusicTribes.entity.User;
import com.music.MusicTribes.repository.PlaylistRepository;
import com.music.MusicTribes.repository.SongRepository;
import com.music.MusicTribes.repository.TribeRepository;
import com.music.MusicTribes.repository.UserRepository;
import com.music.MusicTribes.request.SongRequest;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private final SongRepository songRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TribeRepository tribeRepository;

    @Autowired
    PlaylistRepository playlistRepository;

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

    public Song likeSong(Long songId){
        Optional<Song> getSong = songRepository.findById(songId);
        Song song = getSong.get();

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> getUser = userRepository.findByUsername(userDetails.getUsername());
        User user = getUser.get();

        Optional<Playlist> getPlaylist = playlistRepository.findBySongs(song);
        Playlist playlist = getPlaylist.get();
        System.out.println(playlist.getName());

        Optional<Tribe> getTribe = tribeRepository.findByPlaylists(playlist);
        Tribe tribe = getTribe.get();

        if(tribe.getMembers().contains(user)){
            song.getLikes().add(user);
            return songRepository.save(song);
        }
        else   {
            throw new MessageDescriptorFormatException("You need to be owner of this playlist");
        }

    }

    public int numberOfLikes(Long songId){
        Optional<Song> getSong = songRepository.findById(songId);
        Song song = getSong.get();
        return song.getLikes().size();
    }


}
