package com.cyberpsy.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cyberpsy.Utils.JwtUtil;
import com.cyberpsy.entities.Utilisateur;
import com.cyberpsy.interfaces.UtilisateurRepository;
import com.cyberpsy.service.AuthService;

import lombok.Getter;

@RestController
@RequestMapping("/secure")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UtilisateurRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = authService.authenticate(request.getEmail(), request.getMotDePasse());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.getUsernameFromToken(token);
                Utilisateur user = userRepository.findByEmail(username);
                return ResponseEntity.ok(new UserResponse(user.getEmail(), user.getNom()));
            }
        }
        return ResponseEntity.status(401).build();
    }
}
@Getter
class LoginRequest {
    private String email;
    private String motDePasse;

    // Getters and Setters
}

class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    // Getters
}

class UserResponse {
    private String email;
    private String name;

    public UserResponse(String email, String name) {
        this.email = email;
        this.name = name;
    }

    // Getters
}