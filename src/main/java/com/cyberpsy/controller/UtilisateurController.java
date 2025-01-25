package com.cyberpsy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cyberpsy.entities.Utilisateur;
import com.cyberpsy.input.SignInInput;
import com.cyberpsy.input.SignUpInput;
import com.cyberpsy.interfaces.UtilisateurRepository;
import com.cyberpsy.reponses.ReponseUtilisateur;
import com.cyberpsy.service.UtilisateurService;

@RestController
@RequestMapping("/api/auth")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/register")
    public ResponseEntity<?> createUtilisateur(@RequestBody SignUpInput signUpInput) {
        if(utilisateurRepository.existsByEmail(signUpInput.getEmail()) == true){
            return ResponseEntity.status(409).body("Email already exist");
   
    }
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateurService.createUserEntity(signUpInput));
        ReponseUtilisateur signUpReponse = utilisateurService.createReponseSignUp(savedUtilisateur);
        return ResponseEntity.status(201).body("Account created");
    }


    @PostMapping("/login")
    public ResponseEntity<?> signInUtilisateur(@RequestBody SignInInput signInInput) {
        Boolean isValid = utilisateurService.verifyPasswordAndEmail(signInInput);
        if (isValid) {
            return ResponseEntity.ok("Valid credentials");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}
