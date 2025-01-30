package com.cyberpsy.service.Impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cyberpsy.Utils.JwtUtil;
import com.cyberpsy.configuration.CustomUserDetailsService;
import com.cyberpsy.entities.Utilisateur;
import com.cyberpsy.interfaces.UtilisateurRepository;

import jakarta.transaction.Transactional;

@Service("AuthService")
@Transactional
public class AuthServiceImpl {

    @Autowired
    private UtilisateurRepository userRepository;

    @Autowired
    private CustomUserDetailsService authService;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String authenticate(String email, String password) {
        Optional<Utilisateur> userOptional = userRepository.findByEmail(email);

        Utilisateur user = userOptional.orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (user != null && passwordEncoder.matches(password, user.getMotDePasse())) {
            return jwtUtil.generateToken(email);
        }
        throw new RuntimeException("Invalid credentials");
    }
}
