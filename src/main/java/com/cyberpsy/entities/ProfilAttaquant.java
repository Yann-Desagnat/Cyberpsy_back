package com.cyberpsy.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profil_attaqueant", schema = "dbo")
public class ProfilAttaquant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profil_attaqueant")
    private Integer idProfilAttaqueant;

    @Column(name = "nom")
    private String nom;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "tactique")
    private String tactique;

    @Column(name = "methode")
    private String methode;

}
