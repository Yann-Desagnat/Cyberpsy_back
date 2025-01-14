package com.cyberpsy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberpsy.entities.Utilisateur;
import com.cyberpsy.input.SignUpInput;
import com.cyberpsy.interfaces.UtilisateurRepository;
import com.cyberpsy.reponses.SignUpReponse;
import com.cyberpsy.service.UtilisateurService;

@RestController
@RequestMapping("/api/auth")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/register")
    public SignUpReponse createUtilisateur(@RequestBody SignUpInput signUpInput) {
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateurService.createUserEntity(signUpInput));
        SignUpReponse signUpReponse = utilisateurService.createReponseSignUp(savedUtilisateur);
        return signUpReponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Integer id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        if (utilisateur.isPresent()) {
            return ResponseEntity.ok(utilisateur.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
