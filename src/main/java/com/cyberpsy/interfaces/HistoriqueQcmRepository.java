package com.cyberpsy.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyberpsy.entities.HistoriqueQuestionQcm;

public interface HistoriqueQcmRepository extends JpaRepository<HistoriqueQuestionQcm, Integer> {
    List<HistoriqueQuestionQcm> findByIdUser(int idUser);
}