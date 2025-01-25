package com.cyberpsy.service;

import com.cyberpsy.entities.Utilisateur;
import com.cyberpsy.input.SignInInput;
import com.cyberpsy.input.SignUpInput;
import com.cyberpsy.reponses.ReponseUtilisateur;

public interface UtilisateurService {
    Utilisateur createUserEntity(SignUpInput signUpInput);

    ReponseUtilisateur createReponseSignUp(Utilisateur savedUtilisateur);

    Boolean verifyPasswordAndEmail(SignInInput signInInput);

}
