package com.ahmed.miniprojet.repos;

import com.ahmed.miniprojet.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
