package com.cyberpsy.configuration;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cyberpsy.entities.Utilisateur;
import com.cyberpsy.interfaces.UtilisateurRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Utilisateur> userOptional = utilisateurRepository.findByEmail(email);

        Utilisateur user = userOptional.orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getMotDePasse(),
                Collections.emptyList()
        );
    }
}
