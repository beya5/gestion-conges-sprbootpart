package com.wsconge.services;

import com.wsconge.entities.Role;
import com.wsconge.entities.SoldeConge;
import com.wsconge.entities.Utilisateur;
import com.wsconge.repositories.IUtilisateurRepository;
import com.wsconge.repositories.ISoldeCongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UtilisateurService implements IUtilisateurService {
    @Autowired
    private ISoldeCongeRepository soldeCongesRepository;
    @Autowired
    private IUtilisateurRepository utilisateurRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UtilisateurService(ISoldeCongeRepository soldeCongesRepository) {
        this.soldeCongesRepository = soldeCongesRepository;
    }

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();

    }

    @Override
    public void addUtilisateur(Utilisateur utilisateur) {

        SoldeConge soldeConges = new SoldeConge(utilisateur.getSoldeConge().getMaxJours(),utilisateur);
        utilisateur.setMdp(passwordEncoder.encode(utilisateur.getMdp()));
        utilisateur.setSoldeConge(soldeConges);
        utilisateurRepository.save(utilisateur);


    }

    @Override
    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id).get();
    }

    @Override
    public void deleteUser(Long id) {
        Utilisateur user= utilisateurRepository.findById(id).get();
        if (user!=null){
            utilisateurRepository.delete(user);
            System.out.println("User deleted");
        }
    }

    @Override
    public void updateUser(Long id, Utilisateur u) {
        Utilisateur user=utilisateurRepository.findById(id).get();
        user.setNom(u.getNom());
        user.setEmail(u.getEmail());
        user.setTel(u.getTel());
        user.setRole((Role) u.getRole());
        SoldeConge soldeConge=u.getSoldeConge();
        if (soldeConge != null) {
            soldeConge.setUtilisateur(user);
            soldeCongesRepository.save(soldeConge);
        }
        utilisateurRepository.save(user);
    }

    @Override
    public boolean Login(String nom, String mdp) {
        Utilisateur u=utilisateurRepository.getUtilisateurByNom(nom);
        return u != null ;

    }

    @Override
    public Utilisateur getUtilisateurByNom(String nom) {
        return utilisateurRepository.getUtilisateurByNom(nom);
    }
    public SoldeConge getSoldeByUserId(Long userId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return soldeCongesRepository.findBySoldeutilisateurid(utilisateur.getId());
    }

}
