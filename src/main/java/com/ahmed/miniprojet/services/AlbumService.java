package com.ahmed.miniprojet.services;

import com.ahmed.miniprojet.entities.Album;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AlbumService {
    Album saveAlbum(Album album);
    Album updateAlbum(Album album);
    void deleteAlbum(Album album);
    void deleteAlbumById(Long id);
    Album getAlbum(Long id);
    List<Album> getAllAlbums();
    Page<Album> getAllAlbumsByPage(int page, int size);
}
