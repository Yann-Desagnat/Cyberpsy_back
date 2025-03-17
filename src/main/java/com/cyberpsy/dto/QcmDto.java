package com.cyberpsy.dto;  

import java.util.List;

import lombok.Data;

@Data
public class QcmDto {
    private String question;
    private int niveau;
    private String source;  // Ajouté pour correspondre à l'entité Qcm
    private List<String> reponsesPossibles;
}