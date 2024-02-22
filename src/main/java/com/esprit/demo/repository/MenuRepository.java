package com.esprit.demo.repository;

import com.esprit.demo.entity.Menu;
import com.esprit.demo.entity.TypeMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByTypeMenuAndComposant_PrixGreaterThan(TypeMenu typeMenu, Float montant);
    }
