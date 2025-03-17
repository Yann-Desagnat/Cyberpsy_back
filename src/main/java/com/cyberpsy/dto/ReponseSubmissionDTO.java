package com.cyberpsy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReponseSubmissionDTO {
    @NotNull 
    private Integer idQcm; // Utiliser Integer au lieu de int

    @NotBlank 
    private String reponse;

    @NotNull 
    private Integer idUser; // Utiliser Integer au lieu de int

    // Getters/Setters
    public Integer getIdQcm() { return idQcm; }
    public void setIdQcm(Integer idQcm) { this.idQcm = idQcm; }
    
    public String getReponse() { return reponse; }
    public void setReponse(String reponse) { this.reponse = reponse; }
    
    public Integer getIdUser() { return idUser; }
    public void setIdUser(Integer idUser) { this.idUser = idUser; }
}