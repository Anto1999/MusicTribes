package com.music.MusicTribes.service;

import com.music.MusicTribes.entity.Playlist;
import com.music.MusicTribes.entity.Song;
import com.music.MusicTribes.entity.Tribe;
import com.music.MusicTribes.entity.User;
import com.music.MusicTribes.repository.PlaylistRepository;
import com.music.MusicTribes.repository.SongRepository;
import com.music.MusicTribes.repository.TribeRepository;
import com.music.MusicTribes.repository.UserRepository;
import com.music.MusicTribes.request.PlaylistRequest;
import com.music.MusicTribes.request.SongRequest;
import com.music.MusicTribes.request.TribeRequest;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    @Autowired
    SongRepository songRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TribeRepository tribeRepository;
    @Autowired
    SongService songService;
    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository, SongService songService){
        this.playlistRepository=playlistRepository;
        this.songService=songService;
    }

    public Playlist createPlaylist(PlaylistRequest playlistRequest,Long tribeId){
        Optional<Tribe> getTribe = tribeRepository.findById(tribeId);
        Tribe tribe = getTribe.get();


        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> getUser = userRepository.findByUsername(userDetails.getUsername());
        User user = getUser.get();
        if(tribe.getMembers().contains(user)){
            Playlist playlist = new Playlist();
            playlist.setUserId(user);
            playlist.setName(playlistRequest.getName());
            tribe.getPlaylists().add(playlist);
            playlistRepository.save(playlist);
            tribeRepository.save(tribe);
            return playlistRepository.save(playlist);

        }
        else{
            throw new MessageDescriptorFormatException("You need to be member in this tribe");
        }

    }

    public Playlist addSongToPlaylist(Long playlistId,SongRequest songRequest){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> getUser = userRepository.findByUsername(userDetails.getUsername());
        User user = getUser.get();
        Optional<Playlist> getPlaylist = playlistRepository.findById(playlistId);
        Playlist playlist = getPlaylist.get();


        if(playlist.getUserId()==user){
            Song song = new Song();
            song.setName(songRequest.getName());
            song.setArtist(songRequest.getArtist());
            song.setUrl(songRequest.getUrl());
            playlist.getSongs().add(song);
            songRepository.save(song);
            return  playlistRepository.save(playlist);
        }
        else{
            throw new MessageDescriptorFormatException("You need to be owner of this playlist");
        }
    }



}
