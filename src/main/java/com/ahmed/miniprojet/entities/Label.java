package com.ahmed.miniprojet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LabelId;
    private String LabelName;
    private String LabelDescription;
    private String LabelCountry;

    public Label(String labelName, String labelDescription, String labelCountry) {
        LabelName = labelName;
        LabelDescription = labelDescription;
        LabelCountry = labelCountry;
    }

    public Label() {
    }
}
