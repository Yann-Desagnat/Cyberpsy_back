package com.cyberpsy.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class QcmSubmissionDTO {
    
    @NotNull
    private Integer idUser;

    @NotNull
    private List<ReponseDTO> reponses;

    @Data
    public static class ReponseDTO {
        @NotNull
        private Integer idQcm;

        @NotBlank
        private String reponse;
    }

    // Getters/Setters
}