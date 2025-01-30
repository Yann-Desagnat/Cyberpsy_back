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
@Table(name = "scenario_simulation", schema = "dbo")
public class ScenarioSimulation {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_scenario")
        private Integer idScenario;
    
        @Column(name = "titre")
        private String titre;
    
        @Column(name = "description")
        private String description;
    
        @Column(name = "contexte")
        private String contexte;
    
        @Column(name = "objectif")
        private String objectif;
    
        @Column(name = "type_acteur")
        private String typeActeur;
    
        @Column(name = "difficulté")
        private String difficulte;
    
        
    
}
