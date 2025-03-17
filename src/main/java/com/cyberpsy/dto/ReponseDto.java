package com.cyberpsy.dto;

import lombok.Data;

@Data
public class ReponseDto {
    private int idQcm;
    private String reponse;  // Doit être "Vrai" ou "Faux"
    private boolean estCorrect;
}