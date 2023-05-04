package com.ahmed.miniprojet.controllers;

import com.ahmed.miniprojet.entities.Album;
import com.ahmed.miniprojet.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api")
@CrossOrigin("*")
public class AlbumRestController {
    @Autowired
    AlbumService albumService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{AlbumId}")
    public Album getAlbumById(@PathVariable("AlbumId") Long id) {
        return albumService.getAlbum(id);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public Album saveAlbum(@RequestBody Album album) {
        return albumService.saveAlbum(album);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Album updateAlbum(@RequestBody Album album) {
        return albumService.updateAlbum(album);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{AlbumId}")
    public void deleteAlbum(@PathVariable("AlbumId") Long id) {
        albumService.deleteAlbumById(id);
    }
}
