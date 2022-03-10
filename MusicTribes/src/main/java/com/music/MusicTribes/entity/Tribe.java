package com.music.MusicTribes.entity;

import com.music.MusicTribes.permission.Role;

import javax.persistence.*;
import java.util.ArrayList;
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





    @ManyToOne
    @JoinColumn(name = "chieftain")
    private User chieftain;

    @ManyToMany
    @JoinTable(     name="users_in_tribes",
            joinColumns = @JoinColumn(name="tribe_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> members;

    @ManyToMany
    @JoinTable(     name="playlists_in_tribe",
            joinColumns = @JoinColumn(name="tribe_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    private List<Playlist> playlists;

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

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public User getChieftain() {
        return chieftain;
    }

    public void setChieftain(User chieftain) {
        this.chieftain = chieftain;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
