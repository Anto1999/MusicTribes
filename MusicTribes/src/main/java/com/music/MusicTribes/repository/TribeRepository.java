package com.music.MusicTribes.repository;

import com.music.MusicTribes.entity.Tribe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TribeRepository extends JpaRepository< Tribe,Long> {
}
