package com.cyberpsy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyberpsy.entities.Qcm;
import com.cyberpsy.interfaces.QcmRepository;

@Service
public class QcmService {

    @Autowired
    private QcmRepository qcmRepository;

    @Transactional(readOnly = true)  // Activation des transactions
    public List<Qcm> getQuestionsByNiveauWithAnswers(int niveau) {
        List<Qcm> questions = qcmRepository.findByNiveau(niveau);
      
        return qcmRepository.findByNiveauWithReponses(niveau);
    }
}