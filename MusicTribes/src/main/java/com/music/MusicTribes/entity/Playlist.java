package com.music.MusicTribes.entity;

import javax.persistence.*;
import java.util.List;
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
    private List<Song> songs;


    @ManyToOne
    @JoinColumn(name ="tribeId")
    private Tribe tribeId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;



    public Playlist() {

    }

    public Long getId() {
        return id;
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

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Tribe getTribeId() {
        return tribeId;
    }

    public void setTribeId(Tribe tribeId) {
        this.tribeId = tribeId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
