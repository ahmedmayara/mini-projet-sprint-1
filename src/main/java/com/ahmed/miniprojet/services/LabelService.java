package com.ahmed.miniprojet.services;

import com.ahmed.miniprojet.entities.Label;

import java.util.List;

public interface LabelService {
    Label saveLabel(Label label);
    Label updateLabel(Label label);
    void deleteLabel(Label label);
    void deleteLabelById(Long id);
    Label getLabel(Long id);
    List<Label> getAllLabels();
}
