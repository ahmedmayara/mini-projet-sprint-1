package com.ahmed.miniprojet.repos;

import com.ahmed.miniprojet.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
