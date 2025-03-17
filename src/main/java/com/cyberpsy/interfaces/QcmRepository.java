package com.cyberpsy.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cyberpsy.entities.Qcm;

public interface QcmRepository extends JpaRepository<Qcm, Integer> {

    // Méthode de base
    List<Qcm> findByNiveau(int niveau);

    // Méthode avec JOIN FETCH
    @Query("SELECT DISTINCT q FROM Qcm q LEFT JOIN FETCH q.reponses WHERE q.niveau = :niveau")
    List<Qcm> findByNiveauWithReponses(@Param("niveau") int niveau);

    
}