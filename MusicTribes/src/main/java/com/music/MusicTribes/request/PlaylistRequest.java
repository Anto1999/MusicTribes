package com.music.MusicTribes.request;

import com.music.MusicTribes.entity.Song;
import com.music.MusicTribes.entity.Tribe;
import com.music.MusicTribes.entity.User;

import java.util.List;

public class PlaylistRequest {
    private String name;
    private List<Song> songList;
    private Tribe tribeId;
    private User userId;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
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
