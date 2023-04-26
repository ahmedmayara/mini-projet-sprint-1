package com.ahmed.miniprojet.controllers;

import com.ahmed.miniprojet.entities.Album;
import com.ahmed.miniprojet.services.AlbumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @RequestMapping("showCreate")
    public String showCreate(ModelMap modelMap) {
        modelMap.addAttribute("album", new Album());
        return "createAlbum";
    }

    @RequestMapping("/saveAlbum")
    public String saveAlbum(@Valid Album album, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            albumService.saveAlbum(album);
        }
        return "createAlbum";
    }

    @RequestMapping("/albumsList")
    public String albumsList(
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size
    ) {
        Page<Album> albums = albumService.getAllAlbumsByPage(page, size);
        modelMap.addAttribute("albums", albums);
        modelMap.addAttribute("pages", new int[albums.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "listAlbums";
    }

    @RequestMapping("/deleteAlbum")
    public String deleteAlbum(
            @RequestParam("albumId") Long albumId,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size
    ) {
        albumService.deleteAlbumById(albumId);
        Page<Album> albums = albumService.getAllAlbumsByPage(page, size);
        modelMap.addAttribute("albums", albums);
        modelMap.addAttribute("pages", new int[albums.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listAlbums";
    }

    @RequestMapping("/showUpdate")
    public String showUpdate(
            @RequestParam("albumId") Long albumId,
            ModelMap modelMap
    ) {
        Album album = albumService.getAlbum(albumId);
        modelMap.addAttribute("album", album);
        return "updateAlbum";
    }

    @RequestMapping("/updateAlbum")
    public String updateAlbum(
            @ModelAttribute("album") Album album,
            @RequestParam("creationDate") String date,
            ModelMap modelMap
    ) throws ParseException {
        // Convert the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date creationDate = dateFormat.parse(String.valueOf(date));
        album.setAlbumReleaseDate(creationDate);
        albumService.updateAlbum(album);
        Page<Album> albums = albumService.getAllAlbumsByPage(0, 2);
        modelMap.addAttribute("albums", albums);
        modelMap.addAttribute("pages", new int[albums.getTotalPages()]);
        modelMap.addAttribute("currentPage", 0);
        return "listAlbums";
    }
}
