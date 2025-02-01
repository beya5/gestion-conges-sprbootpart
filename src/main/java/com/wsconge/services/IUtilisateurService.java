package com.wsconge.services;

import com.wsconge.entities.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface IUtilisateurService {
    List<Utilisateur>getAllUtilisateurs();
    void addUtilisateur(Utilisateur utilisateur);
    Utilisateur getUtilisateurById(Long id);
    void deleteUser(Long id);
    void updateUser(Long id,Utilisateur u);

    boolean Login(String nom, String mdp);
    Utilisateur getUtilisateurByNom(String nom);
}
