package com.ahmed.miniprojet.services;

import com.ahmed.miniprojet.entities.Album;
import com.ahmed.miniprojet.repos.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Override
    public Album saveAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Album updateAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public void deleteAlbum(Album album) {
        albumRepository.delete(album);
    }

    @Override
    public void deleteAlbumById(Long id) {
        albumRepository.deleteById(id);
    }

    @Override
    public Album getAlbum(Long id) {
        return albumRepository.findById(id).get();
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @Override
    public Page<Album> getAllAlbumsByPage(int page, int size) {
        return albumRepository.findAll(PageRequest.of(page, size));
    }
}
