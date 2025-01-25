package com.cyberpsy.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cyberpsy.Utils.JwtUtil;
import com.cyberpsy.entities.Utilisateur;
import com.cyberpsy.interfaces.UtilisateurRepository;

import jakarta.transaction.Transactional;

@Service("AuthService")
@Transactional
public class AuthServiceImpl {

    @Autowired
    private UtilisateurRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String authenticate(String email, String password) {
        Utilisateur user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getMotDePasse())) {
            return jwtUtil.generateToken(email);
        }
        throw new RuntimeException("Invalid credentials");
    }
}
