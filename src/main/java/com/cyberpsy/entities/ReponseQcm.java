package com.cyberpsy.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reponse_qcm", schema = "dbo")
@Getter @Setter
public class ReponseQcm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reponse")
    private int idResponseQcm;

    @ManyToOne
    @JoinColumn(name = "id_qcm", nullable = false)
    private Qcm qcm;

    @Column(name = "reponse", nullable = false)
    private String reponse;

    @Column(name = "est_correct", nullable = false)
    private boolean estCorrect;

    @PrePersist
    @PreUpdate
    private void validateReponse() {
        String normalized = this.reponse.trim().toLowerCase();
        if (!normalized.equals("vrai") && !normalized.equals("faux")) {
            throw new IllegalArgumentException("Réponse invalide");
        }
        // Normalisation en base
        this.reponse = normalized.substring(0, 1).toUpperCase() 
            + normalized.substring(1);
    }
}