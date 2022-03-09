package com.music.MusicTribes.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "song_in_playlist",
            joinColumns = @JoinColumn(name="playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private Set<Song> songs;


    @OneToMany
    @JoinColumn(name="playlist_id")
    private Set<Tribe> playlists ;

    public Playlist() {

    }

    public Long getId() {
        return id;
    }

    public Set<Tribe> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Tribe> playlists) {
        this.playlists = playlists;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}
