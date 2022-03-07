package com.music.MusicTribes.repository;

import com.music.MusicTribes.permission.ERole;
import com.music.MusicTribes.permission.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
