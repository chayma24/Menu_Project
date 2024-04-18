package com.esprit.demo.repository;

import com.esprit.demo.entity.Menu;
import com.esprit.demo.entity.typeComposant;
import com.esprit.demo.entity.typeMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByTypeMenuAndComposants_PrixGreaterThan(typeMenu typeMenu, Float montant);

    @Query("SELECT m.libelleMenu FROM Menu m JOIN m.composants c WHERE m.typeMenu = :typeMenu GROUP BY m.libelleMenu ORDER BY SUM(c.prix)")
    List<String> selectNomMenuOrderedByPrixTotal(@Param("typeMenu") typeMenu typeMenu);


    @Query("SELECT m FROM Menu m JOIN m.composants c JOIN c.detailComposant dc WHERE c.detailComposant.idDetailComposant = dc.idDetailComposant AND dc.typeComposant =: typeComposant")
    List<Menu> selectListMenuByTypeComposant(@Param("typeComposant") typeComposant typeComposant);

    Optional<Menu> findByLibelleMenu(String libelleMenu);
}