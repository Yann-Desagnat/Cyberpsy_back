package com.cyberpsy.entities;
 

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "utilisateur", schema = "dbo")
@Getter
@Setter
@Builder(toBuilder = true)

public class Utilisateur {

    @Id
    @Column(name = "id_utilisateur")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUtilisateur;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "mot_de_passe")
    private String motDePasse;

    @Column(name = "date_inscription")
    @Temporal(TemporalType.DATE)
    private Date dateInscription;

    @Column(name = "role")
    private String role;

    @Column(name = "niveau_utilisateur")
    private Integer niveauUtilisateur;

    @Column(name = "date_activation")
    @Temporal(TemporalType.DATE)
    private Date derniereActivite;

}
