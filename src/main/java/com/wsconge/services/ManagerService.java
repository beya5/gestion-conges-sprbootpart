package com.wsconge.services;


import com.wsconge.entities.DemandesConge;
import com.wsconge.entities.PolitiqueConge;
import com.wsconge.entities.Role;
import com.wsconge.entities.Utilisateur;
import com.wsconge.repositories.IDemandeCongeRepository;
import com.wsconge.repositories.IPolitiqueCongeRepository;
import com.wsconge.repositories.IUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService implements IManagerService {

    @Autowired
    private IPolitiqueCongeRepository politiqueCongeRepository;
    @Autowired
    private IUtilisateurRepository utilisateurRepository;

    @Autowired
    private IDemandeCongeRepository demandeCongeRepository;

    @Override
    public void addDemandeConge(DemandesConge demandeConge) {
        PolitiqueConge politiqueConge = politiqueCongeRepository.findById(demandeConge.getPolitique().getId()).orElse(null);
        Utilisateur utilisateur = utilisateurRepository.findById(demandeConge.getUtilisateur().getId()).orElse(null);

        if (politiqueConge != null && utilisateur != null) {
            demandeConge.setPolitique(politiqueConge);
            demandeConge.setUtilisateur(utilisateur);
            demandeCongeRepository.save(demandeConge);
        }
    }

    @Override
    public Utilisateur findDemandeCongeById(long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }
    @Override
    public List<Utilisateur> getUtilisateurByRole(Role developpeur) {
        return (List<Utilisateur>) utilisateurRepository.findByRole(Role.DEVELOPER);
    }
}
