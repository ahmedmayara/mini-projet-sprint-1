package com.ahmed.miniprojet;

import com.ahmed.miniprojet.entities.Album;
import com.ahmed.miniprojet.entities.Label;
import com.ahmed.miniprojet.repos.AlbumRepository;
import com.ahmed.miniprojet.repos.LabelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class MiniProjetApplicationTests {
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private AlbumRepository albumRepository;

    @Test
    void testCreateLabel() {
        Label label = new Label("Label 1", "Description 1", "U.S.A");
        labelRepository.save(label);
    }

    @Test
    void testFindLabel() {
        Label label = labelRepository.findById(1L).get();
        System.out.println(label);
    }

    @Test
    void testUpdateLabel() {
        Label label = labelRepository.findById(1L).get();
        label.setLabelName("Label 1 Updated");
        labelRepository.save(label);
    }

    @Test
    void testDeleteLabel() {
        labelRepository.deleteById(1L);
    }

    @Test
    void testCreateAlbum() {
        Album album = new Album("Album 1", "Artist 1", "Description 1", 10.0, new Date(), labelRepository.findById(2L).get());
        albumRepository.save(album);
    }

    @Test
    void testFindAlbum() {
        Album album = albumRepository.findById(1L).get();
        System.out.println(album);
    }

    @Test
    void testUpdateAlbum() {
        Album album = albumRepository.findById(1L).get();
        album.setAlbumName("Album 1 Updated");
        albumRepository.save(album);
    }
}
