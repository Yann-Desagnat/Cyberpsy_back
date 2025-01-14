package com.cyberpsy.input;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class SignUpInput {
    private String prenom;
    private String nom;
    private String email;
    private String motDePasse; 
    private Date derniereActivite;
    private int niveauUtilisateur;
    private String role;

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }
}
