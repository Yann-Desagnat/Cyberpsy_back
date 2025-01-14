package com.cyberpsy.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberpsy.entities.Utilisateur;
import com.cyberpsy.input.SignUpInput;
import com.cyberpsy.interfaces.UtilisateurRepository;
import com.cyberpsy.reponses.SignUpReponse;
import com.cyberpsy.service.UtilisateurService;

import jakarta.transaction.Transactional;

@Service("UtilisateurService")
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur createUserEntity(SignUpInput signUpInput) {
        Utilisateur newUser = Utilisateur.builder()
            .nom(signUpInput.getNom())
            .prenom(signUpInput.getPrenom())
            .email(signUpInput.getEmail())
            .motDePasse(signUpInput.getMotDePasse())
            .derniereActivite(signUpInput.getDerniereActivite())
            .niveauUtilisateur(signUpInput.getNiveauUtilisateur())
            .role(signUpInput.getRole())
            .build();

        return newUser;
    }

    @Override
    public SignUpReponse createReponseSignUp(Utilisateur savedUtilisateur) {
        int idUser = utilisateurRepository.findIdByEmail(savedUtilisateur.getEmail());
        SignUpReponse signUpReponse = SignUpReponse.builder()
            .id(idUser)
            .nom(savedUtilisateur.getNom())
            .prenom(savedUtilisateur.getPrenom())
            .email(savedUtilisateur.getEmail())
            .motDePasse(savedUtilisateur.getMotDePasse())
            .derniereActivite(savedUtilisateur.getDerniereActivite())
            .niveauUtilisateur(savedUtilisateur.getNiveauUtilisateur())
            .role(savedUtilisateur.getRole())
            .build();
        
        return signUpReponse;
    }

    
}
