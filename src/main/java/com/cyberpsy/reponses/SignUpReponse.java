package com.cyberpsy.reponses;
import java.lang.String;
import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)

public class SignUpReponse {
    private int id;
    private String prenom;
    private String nom;
    private String email;
    private String motDePasse; 
    private Date derniereActivite;
    private int niveauUtilisateur;
    private String role;
}
