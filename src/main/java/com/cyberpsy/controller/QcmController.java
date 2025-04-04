package com.cyberpsy.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cyberpsy.dto.ReponseSubmissionDTO;
import com.cyberpsy.entities.HistoriqueQuestionQcm;
import com.cyberpsy.entities.Qcm;
import com.cyberpsy.entities.ReponseQcm;
import com.cyberpsy.interfaces.HistoriqueQcmRepository;
import com.cyberpsy.interfaces.QcmRepository;
import com.cyberpsy.interfaces.ReponseQcmRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/qcm")
@Validated
public class QcmController {

    @Autowired
    private QcmRepository qcmRepository;

    @Autowired
    private ReponseQcmRepository reponseQcmRepository;

    @Autowired
    private HistoriqueQcmRepository historiqueQcmRepository;

    /**
     * ✅ Récupérer les questions du QCM par niveau avec la bonne réponse
     */


   /*  @GetMapping("/niveau/{niveau}")
    public ResponseEntity<List<Map<String, Object>>> getByLevel(
        @PathVariable int niveau,
        
        @RequestHeader("Authorization") String token) */
        @GetMapping("/niveau/{niveau}")
public ResponseEntity<List<Map<String, Object>>> getByLevel(@PathVariable  int niveau) {

        List<Qcm> questions = qcmRepository.findByNiveau(niveau);
        List<Map<String, Object>> response = questions.stream().map(q -> {
            Map<String, Object> questionData = new HashMap<>();
            questionData.put("idQcm", q.getIdQcm());
            questionData.put("question", q.getQuestion());
            questionData.put("options", List.of("Vrai", "Faux"));

            // ✅ Récupérer la vraie réponse correcte et non la source
            Optional<ReponseQcm> correctAnswer = reponseQcmRepository.findByQcmIdQcmAndEstCorrect(q.getIdQcm(), true);
            questionData.put("correctAnswer", correctAnswer.map(ReponseQcm::getReponse).orElse("Bonne réponse non disponible"));

            questionData.put("source", q.getSource()); // Source gardée pour référence
            questionData.put("niveau", q.getNiveau());

            return questionData;
        }).toList();

        return ResponseEntity.ok(response);
    }

    /**
     * ✅ Soumettre la réponse d'un utilisateur et vérifier si elle est correcte
     */
    @PostMapping("/submit")
    @Transactional
    public ResponseEntity<?> submitResponse(@RequestBody @Valid ReponseSubmissionDTO submission) {
        try {
            // 🔹 Vérifier si la question existe
            Qcm qcm = qcmRepository.findById(submission.getIdQcm())
                      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "QCM non trouvé avec l'ID : " + submission.getIdQcm()));

            // 🔹 Récupérer la vraie bonne réponse
            Optional<ReponseQcm> correctResponse = reponseQcmRepository.findByQcmIdQcmAndEstCorrect(submission.getIdQcm(), true);
            
            if (!correctResponse.isPresent()) {
                return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "Aucune réponse correcte trouvée pour ce QCM"
                ));
            }

        
            boolean isCorrect = submission.getReponse().trim().equalsIgnoreCase(correctResponse.get().getReponse().trim());

            // 🔹 Enregistrer la réponse de l'utilisateur dans l'historique
            HistoriqueQuestionQcm historique = new HistoriqueQuestionQcm();
            historique.setQcm(qcm);
            historique.setIdUser(submission.getIdUser());
            historique.setDateReponse(LocalDateTime.now());
            historique.setCorrect(isCorrect ? "true" : "false");
            historiqueQcmRepository.save(historique);

            Map<String, Object> response = new HashMap<>();
response.put("status", "success");
response.put("isCorrect", isCorrect);
response.put("userResponse", submission.getReponse());
response.put("correctAnswer", correctResponse.get().getReponse());
response.put("source", qcm.getSource());

return ResponseEntity.ok(response);


        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "status", "error",
                "message", e.getMessage()
            ));
        }
    }
}
