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
import com.cyberpsy.entities.Qcm;


@RestController
@RequestMapping("/api/qcm")
public class QcmController {
@Autowired
    private QcmRepository qcmRepository;

    // Get all QCM records
    @GetMapping
    public ResponseEntity<List<Qcm>> getAllQcm() {
        List<Qcm> qcms = qcmRepository.findAll();
        return ResponseEntity.ok(qcms);
    }

    // Get a single QCM by ID
    @GetMapping("/{id}")
    public ResponseEntity<Qcm> getQcmById(@PathVariable int id) {
        Optional<Qcm> qcm = qcmRepository.findById(id);
        return qcm.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Add a new QCM
    @PostMapping
    public ResponseEntity<Qcm> createQcm(@RequestBody Qcm qcm) {
        Qcm savedQcm = qcmRepository.save(qcm);
        return ResponseEntity.ok(savedQcm);
    }

    // Update an existing QCM
    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQcm(@PathVariable int id) {
        if (qcmRepository.existsById(id)) {
            qcmRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
