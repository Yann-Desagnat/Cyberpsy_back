package com.cyberpsy.controller;

import com.cyberpsy.interfaces.UtilisateurRepository;
import com.cyberpsy.Utils.JwtUtil;
import com.cyberpsy.configuration.CustomUserDetailsService;
import com.cyberpsy.entities.Utilisateur;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberpsy.input.SignInInput;
import com.cyberpsy.input.SignUpInput;
import com.cyberpsy.reponses.ReponseUtilisateur;
import com.cyberpsy.service.UtilisateurService;

@RestController
@RequestMapping("/api/auth")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private PasswordEncoder passwordEncoder;

     @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    JwtUtil jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> createUtilisateur(@RequestBody SignUpInput signUpInput) {
        if(utilisateurRepository.existsByEmail(signUpInput.getEmail()) == true){
            return ResponseEntity.status(409).body("Email already exist");
   
    }
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateurService.createUserEntity(signUpInput));
        ReponseUtilisateur signUpReponse = utilisateurService.createReponseSignUp(savedUtilisateur);
        return ResponseEntity.status(201).body("Account created");
    }


    @PostMapping("/login")
    public String authenticateUser(@RequestBody SignInInput user) {
        /*UserDetails userd = customUserDetailsService.loadUserByUsername("aaa@aaa.com");
        System.out.println(userd.getUsername() + " " + user.getPassword());*/
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername());
    }

    @GetMapping("/getuser")
    public ResponseEntity<?> getUserDetails(Authentication authentication) {
        /*System.out.println(userd.getUsername() + " " + user.getPassword());*/
        Utilisateur user = utilisateurRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("email", user.getEmail());
        userDetails.put("prenom", user.getPrenom());
        userDetails.put("nom", user.getNom());
        userDetails.put("idUtilisateur", user.getIdUtilisateur());
        userDetails.put("derniereActivite", user.getDerniereActivite());
        userDetails.put("niveauUtilisateur", user.getNiveauUtilisateur());
        userDetails.put("role", user.getRole());


        return ResponseEntity.ok(userDetails);
    }

    @PutMapping("/{id}/updatepassword")
    public ResponseEntity<?> updatePassword(@PathVariable int id, @RequestBody String password){
        if (password == null || password.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password cannot be null or empty.");
        }

        return utilisateurRepository.findById(id).map(utilisateur -> {
            utilisateur.setMotDePasse(passwordEncoder.encode(password));
            utilisateurRepository.save(utilisateur);
            return ResponseEntity.ok("Password updated successfully.");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found."));
    }
}
    

