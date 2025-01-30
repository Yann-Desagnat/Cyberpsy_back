package com.cyberpsy.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "reponse_qcm", schema = "dbo")
@Getter
@Setter
@Entity
public class ReponseQcm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_reponse")
    private int idResponseQcm;

    @ManyToOne
    @JoinColumn(name = "id_qcm")
    private Qcm qcm;

    @Column(name = "reponse")
    private String Response;

    @Column(name = "est_correct")
    private boolean correct;
    
}
