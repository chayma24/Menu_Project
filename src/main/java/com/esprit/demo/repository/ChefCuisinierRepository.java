package com.esprit.demo.repository;

import com.esprit.demo.entity.ChefCuisinier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefCuisinierRepository extends JpaRepository<ChefCuisinier,Long> {
}
