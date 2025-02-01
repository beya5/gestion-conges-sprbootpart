package com.wsconge.repositories;

import com.wsconge.entities.SoldeConge;
import com.wsconge.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISoldeCongeRepository extends JpaRepository<SoldeConge,Long> {
    SoldeConge getSoldeCongeById(Long id);
    SoldeConge findBySoldeutilisateurid(Long userId);
    SoldeConge findBySoldeutilisateurid(Utilisateur utilisateur);
}
