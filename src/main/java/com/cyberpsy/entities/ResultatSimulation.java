package com.cyberpsy.entities;

import java.sql.Date;

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
@Table(name = "resultat_simulation", schema = "dbo")
public class ResultatSimulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultat")
    private Integer idResultat;

    @Column(name = "id_scenario")
    private Integer idScenario;

    @Column(name = "id_utilisateur")
    private Integer idUtilisateur;

    @Column(name = "date")
    private Date date;

    @Column(name = "conclusion")
    private String conclusion;

  
}
