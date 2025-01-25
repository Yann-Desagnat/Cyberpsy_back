package com.cyberpsy.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cyberpsy.entities.Utilisateur;


@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    @Query("SELECT u.id FROM Utilisateur u WHERE u.email = :email")
    int findIdByEmail(@Param("email") String email);

    @Query("SELECT COUNT(u) > 0 FROM Utilisateur u WHERE u.email = :email")
    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT COUNT(u) > 0 FROM Utilisateur u WHERE u.email = :email and u.mot_de_passe = :password")
    boolean verifyUser(@Param("email") String email, @Param("password") String password);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Utilisateur findByEmail(@Param("email") String email);
}
