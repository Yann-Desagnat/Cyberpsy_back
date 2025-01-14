package com.cyberpsy.service;

import com.cyberpsy.entities.Utilisateur;
import com.cyberpsy.input.SignUpInput;
import com.cyberpsy.reponses.SignUpReponse;

public interface UtilisateurService {
    Utilisateur createUserEntity(SignUpInput signUpInput);

    SignUpReponse createReponseSignUp(Utilisateur savedUtilisateur);

}
