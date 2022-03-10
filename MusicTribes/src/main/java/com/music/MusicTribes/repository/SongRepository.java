package com.music.MusicTribes.repository;

import com.music.MusicTribes.entity.Playlist;
import com.music.MusicTribes.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface SongRepository extends JpaRepository<Song,Long> {

}
