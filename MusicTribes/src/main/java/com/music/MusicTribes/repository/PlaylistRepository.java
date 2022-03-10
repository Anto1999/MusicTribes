package com.music.MusicTribes.repository;

import com.music.MusicTribes.entity.Playlist;
import com.music.MusicTribes.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
    Optional<Playlist> findBySongs(Song song);

}
