package com.music.MusicTribes.entity;

import com.music.MusicTribes.permission.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Tribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String genre;
    private String description;

    @OneToMany
    @JoinColumn(name="playlist_id")
    private Set<Playlist> playlists ;



    @OneToOne
    @JoinColumn(name = "chieftain")
    private User chieftain;

    @OneToMany
    @JoinColumn(name = "members")
    private Set<User> members;

    public Tribe() {

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }

    public User getChieftain() {
        return chieftain;
    }

    public void setChieftain(User chieftain) {
        this.chieftain = chieftain;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }
}
