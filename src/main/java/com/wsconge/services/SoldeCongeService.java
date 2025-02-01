package com.wsconge.services;

import com.wsconge.entities.*;
import com.wsconge.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoldeCongeService implements ISoldeCongeService {
    @Autowired
    private ISoldeCongeRepository soldeCongeRepository;
    private final IUtilisateurRepository iUtilisateurRepository;

    public SoldeCongeService( IUtilisateurRepository iUtilisateurRepository) {
        this.iUtilisateurRepository = iUtilisateurRepository;
    }

    @Override
    public int trackLeaveBalance(Long userId) {
        Utilisateur utilisateur= iUtilisateurRepository.findById(userId).get();
        SoldeConge soldeConge = soldeCongeRepository.findBySoldeutilisateurid(utilisateur);
        if (soldeConge != null) {
            return soldeConge.getMaxJours();
        }
        else {
            return 0;
        }
    }
    @Override
    public void addSoldeConge(SoldeConge soldeConge) {
        soldeCongeRepository.save(soldeConge);
    }
    @Override
    public void deleteSoldeConge(Long id) {
        soldeCongeRepository.deleteById(id);
    }
    @Override
    public SoldeConge getSoldeCongeById(Long id) {
        return soldeCongeRepository.findById(id).get();
    }
}