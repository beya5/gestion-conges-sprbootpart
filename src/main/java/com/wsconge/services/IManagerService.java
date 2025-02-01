package com.wsconge.services;

import com.wsconge.entities.DemandesConge;
import com.wsconge.entities.Role;
import com.wsconge.entities.Utilisateur;

import java.util.List;

public interface IManagerService {
    public void addDemandeConge(DemandesConge demandeConge);
    public Utilisateur findDemandeCongeById(long id);
    public List<Utilisateur> getUtilisateurByRole(Role d);

}
