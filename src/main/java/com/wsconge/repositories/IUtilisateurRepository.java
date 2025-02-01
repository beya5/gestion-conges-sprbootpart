package com.wsconge.repositories;

import com.wsconge.entities.Role;
import com.wsconge.entities.Utilisateur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUtilisateurRepository extends JpaRepository<Utilisateur,Long> {

    Utilisateur getUtilisateurByNom(String nom);
    Utilisateur findByRole(Role r);
}
