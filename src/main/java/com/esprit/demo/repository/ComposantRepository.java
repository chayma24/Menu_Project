package com.esprit.demo.repository;

import com.esprit.demo.entity.Composant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComposantRepository extends JpaRepository<Composant, Long> {
}
