package com.cyberpsy.controller;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberpsy.interfaces.QcmRepository;
import com.cyberpsy.interfaces.ReponseQcmRepository;
import com.cyberpsy.entities.Qcm;
import com.cyberpsy.entities.ReponseQcm;


@RestController
@RequestMapping("/api")
public class QcmController {
@Autowired
    private QcmRepository qcmRepository;
    private ReponseQcmRepository reponseQcmRepository;

    // Get all QCM records
    @GetMapping("/qcm")
    public ResponseEntity<List<Qcm>> getAllQcm() {
        List<Qcm> qcms = qcmRepository.findAll();
        return ResponseEntity.ok(qcms);
    }

    // Get a single QCM by ID
    @GetMapping("qcm/{id}")
    public ResponseEntity<Qcm> getQcmById(@PathVariable int id) {
        Optional<Qcm> qcm = qcmRepository.findById(id);
        return qcm.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Add a new QCM
    @PostMapping("/qcm")
    public ResponseEntity<Qcm> createQcm(@RequestBody Qcm qcm) {
        Qcm savedQcm = qcmRepository.save(qcm);
        return ResponseEntity.ok(savedQcm);
    }

    // Update an existing QCM
    @PutMapping("/qcm/{id}")
    public ResponseEntity<Qcm> updateQcm(@PathVariable int id, @RequestBody Qcm updatedQcm) {
        Optional<Qcm> existingQcm = qcmRepository.findById(id);

        if (existingQcm.isPresent()) {
            Qcm qcm = existingQcm.get();
            qcm.setQuestion(updatedQcm.getQuestion());
            qcm.setNiveau(updatedQcm.getNiveau());
            Qcm savedQcm = qcmRepository.save(qcm);
            return ResponseEntity.ok(savedQcm);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a QCM by ID
    @DeleteMapping("/qcm/{id}")
    public ResponseEntity<Void> deleteQcm(@PathVariable int id) {
        if (qcmRepository.existsById(id)) {
            qcmRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("reponseqcm")
    public ResponseEntity<ReponseQcm> createResponseQcm(@RequestBody ReponseQcm reponseQcm) {
        ReponseQcm savedReponse = reponseQcmRepository.save(reponseQcm);
        return ResponseEntity.ok(savedReponse);
    }

    // Update an existing QCM
    @PutMapping("reponseqcm/{id}")
    public ResponseEntity<ReponseQcm> updateResponseQcm(@PathVariable int id, @RequestBody ReponseQcm updatedReponseQcm) {
        Optional<ReponseQcm> existingReponseQcm = reponseQcmRepository.findById(id);

        if (existingReponseQcm.isPresent()) {
            ReponseQcm reponseQcm = existingReponseQcm.get();
            reponseQcm.setResponse(updatedReponseQcm.getResponse());
            reponseQcm.setCorrect(updatedReponseQcm.isCorrect());
            ReponseQcm savedReponseQcm = reponseQcmRepository.save(reponseQcm);
            return ResponseEntity.ok(savedReponseQcm);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

 /*  @PostMapping("/historiqueqcm")
    public*/
}
