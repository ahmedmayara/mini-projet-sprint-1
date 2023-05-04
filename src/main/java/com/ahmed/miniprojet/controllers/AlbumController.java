package com.ahmed.miniprojet.controllers;

import com.ahmed.miniprojet.entities.Album;
import com.ahmed.miniprojet.entities.Label;
import com.ahmed.miniprojet.services.AlbumService;
import com.ahmed.miniprojet.services.LabelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AlbumController {
    @Autowired
    AlbumService albumService;
    @Autowired
    LabelService labelService;

    @RequestMapping("showCreate")
    public String showCreate(ModelMap modelMap) {
        List<Label> labels = labelService.getAllLabels();
        Album album = new Album();
        Label label = new Label();
        label = labels.get(0);
        album.setLabel(label);
        modelMap.addAttribute("album", album);
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("label", label);
        modelMap.addAttribute("labels", labels);
        modelMap.addAttribute("page",0);
        return "formAlbum";
    }

    @RequestMapping("/saveAlbum")
    public String saveAlbum(@Valid Album album,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
    @ModelAttribute("page") int pageFromPrevious,
    @RequestParam(name = "size", defaultValue = "2") int size) {
        int page;
        if (bindingResult.hasErrors()) return "formAlbum";
        if (album.getAlbumId() == null) {
            page = albumService.getAllAlbums().size() / size;
        } else {
            page = pageFromPrevious;
        }
        albumService.saveAlbum(album);
        redirectAttributes.addAttribute("page", page);
        return "redirect:/albumsList";
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
    public String showUpdate(@RequestParam("albumId") Long albumId, ModelMap modelMap, @RequestParam(name = "page") int page) {
        List<Label> labels = labelService.getAllLabels();
        Album album = albumService.getAlbum(albumId);
        modelMap.addAttribute("album", album);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("labels", labels);
        modelMap.addAttribute("mode", "update");
        return "formAlbum";
    }

    @RequestMapping("/updateAlbum")
    public String updateAlbum(
            @ModelAttribute("album") @Valid Album album,
            BindingResult bindingResult,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            RedirectAttributes redirectAttributes
    ) {
        if (!bindingResult.hasErrors()) {
            albumService.updateAlbum(album);
            redirectAttributes.addAttribute("page", page);
        }
        return "redirect:/albumsList";
    }
}
