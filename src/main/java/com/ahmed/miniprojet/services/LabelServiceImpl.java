package com.ahmed.miniprojet.services;

import com.ahmed.miniprojet.entities.Label;
import com.ahmed.miniprojet.repos.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService{
    @Autowired
    private LabelRepository labelRepository;

    @Override
    public Label saveLabel(Label label) {
        return labelRepository.save(label);
    }

    @Override
    public Label updateLabel(Label label) {
        return labelRepository.save(label);
    }

    @Override
    public void deleteLabel(Label label) {
        labelRepository.delete(label);
    }

    @Override
    public void deleteLabelById(Long id) {
        labelRepository.deleteById(id);
    }

    @Override
    public Label getLabel(Long id) {
        return labelRepository.findById(id).get();
    }

    @Override
    public List<Label> getAllLabels() {
        return labelRepository.findAll();
    }
}
