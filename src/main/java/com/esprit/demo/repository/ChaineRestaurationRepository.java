package com.esprit.demo.repository;

import com.esprit.demo.entity.ChaineRestauration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChaineRestaurationRepository extends JpaRepository<ChaineRestauration,Long> {
    ChaineRestauration findByLibelle(String libelleChaine);
}
