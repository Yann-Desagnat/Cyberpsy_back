package com.cyberpsy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cyberpsy.entities.ProfilAttaquant;
import com.cyberpsy.entities.ResultatSimulation;
import com.cyberpsy.entities.ScenarioSimulation;
import com.cyberpsy.interfaces.ProfilAttaquantRepository;
import com.cyberpsy.interfaces.ResultatSimulationRepository;
import com.cyberpsy.interfaces.ScenarioSimulationRepository;

public class SimulationController {
    
        @Autowired
        private ProfilAttaquantRepository profilAttaquantRepository;
    
        @Autowired
        private ResultatSimulationRepository resultatSimulationRepository;
    
        @Autowired
        private ScenarioSimulationRepository scenarioSimulationRepository;
    
        // ---------------- ProfilAttaquant Endpoints ----------------
    
        @GetMapping("/profilAttaquants")
        public List<ProfilAttaquant> getAllProfilAttaquants() {
            return profilAttaquantRepository.findAll();
        }
    
        @GetMapping("/profilAttaquants/{id}")
        public ResponseEntity<ProfilAttaquant> getProfilAttaquantById(@PathVariable Integer id) {
            Optional<ProfilAttaquant> profil = profilAttaquantRepository.findById(id);
            return profil.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }
    
        @PostMapping("/profilAttaquants")
        public ProfilAttaquant createProfilAttaquant(@RequestBody ProfilAttaquant profilAttaquant) {
            return profilAttaquantRepository.save(profilAttaquant);
        }
    
        @PutMapping("/profilAttaquants/{id}")
        public ResponseEntity<ProfilAttaquant> updateProfilAttaquant(@PathVariable Integer id, @RequestBody ProfilAttaquant profilAttaquantDetails) {
            return profilAttaquantRepository.findById(id).map(profil -> {
                profil.setNom(profilAttaquantDetails.getNom());
                profil.setType(profilAttaquantDetails.getType());
                profil.setDescription(profilAttaquantDetails.getDescription());
                profil.setTactique(profilAttaquantDetails.getTactique());
                profil.setMethode(profilAttaquantDetails.getMethode());
                profilAttaquantRepository.save(profil);
                return ResponseEntity.ok(profil);
            }).orElse(ResponseEntity.notFound().build());
        }
    
        @DeleteMapping("/profilAttaquants/{id}")
        public ResponseEntity<Void> deleteProfilAttaquant(@PathVariable Integer id) {
            if (profilAttaquantRepository.existsById(id)) {
                profilAttaquantRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }
    
        // ---------------- ResultatSimulation Endpoints ----------------
    
        @GetMapping("/resultatSimulations")
        public List<ResultatSimulation> getAllResultatSimulations() {
            return resultatSimulationRepository.findAll();
        }
    
        @GetMapping("/resultatSimulations/{id}")
        public ResponseEntity<ResultatSimulation> getResultatSimulationById(@PathVariable Integer id) {
            Optional<ResultatSimulation> resultat = resultatSimulationRepository.findById(id);
            return resultat.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }
    
        @PostMapping("/resultatSimulations")
        public ResultatSimulation createResultatSimulation(@RequestBody ResultatSimulation resultatSimulation) {
            return resultatSimulationRepository.save(resultatSimulation);
        }
    
        @PutMapping("/resultatSimulations/{id}")
        public ResponseEntity<ResultatSimulation> updateResultatSimulation(@PathVariable Integer id, @RequestBody ResultatSimulation resultatSimulationDetails) {
            return resultatSimulationRepository.findById(id).map(resultat -> {
                resultat.setIdScenario(resultatSimulationDetails.getIdScenario());
                resultat.setIdUtilisateur(resultatSimulationDetails.getIdUtilisateur());
                resultat.setDate(resultatSimulationDetails.getDate());
                resultat.setConclusion(resultatSimulationDetails.getConclusion());
                resultatSimulationRepository.save(resultat);
                return ResponseEntity.ok(resultat);
            }).orElse(ResponseEntity.notFound().build());
        }
    
        @DeleteMapping("/resultatSimulations/{id}")
        public ResponseEntity<Void> deleteResultatSimulation(@PathVariable Integer id) {
            if (resultatSimulationRepository.existsById(id)) {
                resultatSimulationRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }
    
        // ---------------- ScenarioSimulation Endpoints ----------------
    
        @GetMapping("/scenarioSimulations")
        public List<ScenarioSimulation> getAllScenarioSimulations() {
            return scenarioSimulationRepository.findAll();
        }
    
        @GetMapping("/scenarioSimulations/{id}")
        public ResponseEntity<ScenarioSimulation> getScenarioSimulationById(@PathVariable Integer id) {
            Optional<ScenarioSimulation> scenario = scenarioSimulationRepository.findById(id);
            return scenario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }
    
        @PostMapping("/scenarioSimulations")
        public ScenarioSimulation createScenarioSimulation(@RequestBody ScenarioSimulation scenarioSimulation) {
            return scenarioSimulationRepository.save(scenarioSimulation);
        }
    
        @PutMapping("/scenarioSimulations/{id}")
        public ResponseEntity<ScenarioSimulation> updateScenarioSimulation(@PathVariable Integer id, @RequestBody ScenarioSimulation scenarioSimulationDetails) {
            return scenarioSimulationRepository.findById(id).map(scenario -> {
                scenario.setTitre(scenarioSimulationDetails.getTitre());
                scenario.setDescription(scenarioSimulationDetails.getDescription());
                scenario.setContexte(scenarioSimulationDetails.getContexte());
                scenario.setObjectif(scenarioSimulationDetails.getObjectif());
                scenario.setTypeActeur(scenarioSimulationDetails.getTypeActeur());
                scenario.setDifficulte(scenarioSimulationDetails.getDifficulte());
                scenarioSimulationRepository.save(scenario);
                return ResponseEntity.ok(scenario);
            }).orElse(ResponseEntity.notFound().build());
        }
    
        @DeleteMapping("/scenarioSimulations/{id}")
        public ResponseEntity<Void> deleteScenarioSimulation(@PathVariable Integer id) {
            if (scenarioSimulationRepository.existsById(id)) {
                scenarioSimulationRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }
    }
    

