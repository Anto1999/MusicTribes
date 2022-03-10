package com.music.MusicTribes.repository;

import com.music.MusicTribes.entity.Tribe;
import com.music.MusicTribes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TribeRepository extends JpaRepository< Tribe,Long> {
    Optional<Tribe> findById(Long tribeId);
}
