package com.ahmed.miniprojet.repos;

import com.ahmed.miniprojet.entities.Album;
import com.ahmed.miniprojet.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query("SELECT a FROM Album a WHERE a.AlbumName LIKE %:AlbumName AND a.AlbumPrice > :AlbumPrice")
    List<Album> findByNamePrice(@Param("AlbumName") String AlbumName, @Param("AlbumPrice") Double AlbumPrice);

    @Query("SELECT a from Album  a where a.label = ?1")
    List<Album> findByLabel(Label label);

    @Query("select a from Album a order by a.AlbumName ASC, a.AlbumPrice DESC ")
    List<Album> trierAlbumsNomsPrix();
}
