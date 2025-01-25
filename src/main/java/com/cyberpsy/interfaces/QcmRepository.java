package com.cyberpsy.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyberpsy.entities.Qcm;

@Repository
public interface QcmRepository extends JpaRepository<Qcm, Integer> {
}