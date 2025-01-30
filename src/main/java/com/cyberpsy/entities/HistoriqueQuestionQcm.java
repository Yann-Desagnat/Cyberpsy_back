package com.cyberpsy.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class HistoriqueQuestionQcm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historique")
    private Integer id;

    @Column(name = "id_qcm")
    private int idQcm;

    @Column(name = "id_utilisateur")
    private int idUser;

    @Column(name = "date_reponse")
    private Date dateReponse;

    @Column(name = "est_correct")
    private String correct;
}
