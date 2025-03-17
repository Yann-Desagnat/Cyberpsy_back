package com.cyberpsy.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "historique_questions_qcm", schema = "dbo")
@Getter
@Setter // Lombok génère les setters automatiquement
public class HistoriqueQuestionQcm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historique")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_qcm", nullable = false)
    private Qcm qcm; // Supprimez le champ "idQcm" redondant

    @Column(name = "id_utilisateur", nullable = false)
    private Integer idUser; // Utilisez Integer au lieu de int

    @Column(name = "date_reponse", nullable = false)
    private LocalDateTime dateReponse; // Utilisez LocalDateTime

    @Column(name = "est_correct", nullable = false)
    private String correct; 
}
