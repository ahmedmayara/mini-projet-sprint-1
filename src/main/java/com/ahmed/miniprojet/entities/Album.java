package com.ahmed.miniprojet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AlbumId;
    private String AlbumName;
    private String AlbumArtist;
    private String AlbumDescription;
    private Double AlbumPrice;
    private Date AlbumReleaseDate;
    @ManyToOne
    @JoinColumn(name = "LabelId")
    private Label label;

    public Album(String albumName, String albumArtist, String albumDescription, Double albumPrice, Date albumReleaseDate, Label label) {
        AlbumName = albumName;
        AlbumArtist = albumArtist;
        AlbumDescription = albumDescription;
        AlbumPrice = albumPrice;
        AlbumReleaseDate = albumReleaseDate;
        this.label = label;
    }

    public Album() {
    }
}
