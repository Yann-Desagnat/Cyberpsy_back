package com.cyberpsy.reponses;

import java.time.LocalDateTime;

import com.cyberpsy.entities.Qcm;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class HistoriqueQuestionQcmReponse {
    private Qcm qcm;
    private LocalDateTime dateReponse;
    private String correct; 
}
