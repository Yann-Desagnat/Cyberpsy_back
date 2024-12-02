package com.cyberpsy.entities;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Utilisateur")
public class Utilisateur {

    @Id
    @Column(name = "ID_Utilisateur")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUtilisateur;

    @Column(name = "Nom", nullable = false)
    private String nom;

    @Column(name = "Prenom", nullable = false)
    private String prenom;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Mot_De_Passe", nullable = false)
    private String motDePasse;

    @Column(name = "Date_Inscription")
    @Temporal(TemporalType.DATE)
    private Date dateInscription;

    @Column(name = "Role", nullable = false)
    private String role;

    @Column(name = "Niveau_Utilisateur", nullable = false)
    private Integer niveauUtilisateur;

    @Column(name = "Date_Activation")
    @Temporal(TemporalType.DATE)
    private Date dateActivation;

    // Getters and Setters
    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getNiveauUtilisateur() {
        return niveauUtilisateur;
    }

    public void setNiveauUtilisateur(Integer niveauUtilisateur) {
        this.niveauUtilisateur = niveauUtilisateur;
    }

    public Date getDateActivation() {
        return dateActivation;
    }

    public void setDateActivation(Date dateActivation) {
        this.dateActivation = dateActivation;
    }
}
