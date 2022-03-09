package com.music.MusicTribes.request;

import com.music.MusicTribes.entity.User;

import java.util.List;

public class TribeRequest {
    private String name;
    private String genre;
    private String description;
    private User chieftain;
    private List<User> members;

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
