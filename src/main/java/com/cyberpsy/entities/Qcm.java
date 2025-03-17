package com.cyberpsy.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "qcm", schema = "dbo")
public class Qcm {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idQcm;
    private String question;
    private int niveau;
    private String reponse;
    private boolean estCorrect;
    private String source;

    @OneToMany(mappedBy = "qcm", cascade = CascadeType.ALL)
    private List<ReponseQcm> reponses;

    public Qcm() {
    }

    // Constructor only for ID - useful for setting relationships without full entity fetch
    public Qcm(int idQcm) {
        this.idQcm = idQcm;
    }

    // Getters and setters
    public int getIdQcm() {
        return idQcm;
    }

    public void setIdQcm(int idQcm) {
        this.idQcm = idQcm;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public boolean isEstCorrect() {
        return estCorrect;
    }

    public void setEstCorrect(boolean estCorrect) {
        this.estCorrect = estCorrect;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<ReponseQcm> getReponses() {
        return reponses;
    }

    public void setReponses(List<ReponseQcm> reponses) {
        this.reponses = reponses;
    }
}
