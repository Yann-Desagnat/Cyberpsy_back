package com.cyberpsy.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "qcm", schema = "dbo")
@Getter
@Setter
@Entity
public class Qcm {

    @Id
    @Column(name = "id_qcm")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idQcm;

    @Column(name = "question")
    private String question;

    @Column(name = "niveau")
    private int niveau;
}
