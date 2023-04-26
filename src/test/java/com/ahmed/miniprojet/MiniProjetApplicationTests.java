package com.ahmed.miniprojet;

import com.ahmed.miniprojet.entities.Album;
import com.ahmed.miniprojet.entities.Label;
import com.ahmed.miniprojet.repos.AlbumRepository;
import com.ahmed.miniprojet.repos.LabelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

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

    @Test
    void testfindByNamePrice() {
        List<Album> albums = albumRepository.findByNamePrice("Album 1", 10.0);
        for (Album a : albums) {
            System.out.println(a);
        }
    }

    @Test
    void testfindByLabel() {
        Label label = new Label();
        label.setLabelId(1L);
        List<Album> albums = albumRepository.findByLabel(label);
        for (Album a : albums) {
            System.out.println(a);
        }
    }

    @Test
    void testtrierAlbumsNomsPrix() {
        List<Album> albums = albumRepository.trierAlbumsNomsPrix();
        for (Album a : albums) {
            System.out.println(a);
        }
    }
}
