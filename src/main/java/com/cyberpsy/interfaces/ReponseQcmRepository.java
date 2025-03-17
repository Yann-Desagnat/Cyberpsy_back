package com.cyberpsy.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyberpsy.entities.ReponseQcm;

public interface ReponseQcmRepository extends JpaRepository<ReponseQcm, Integer> {
    Optional<ReponseQcm> findByQcmIdQcmAndReponse(int idQcm, String reponse);
    Optional<ReponseQcm> findByQcmIdQcmAndEstCorrect(int idQcm, boolean estCorrect);
}


