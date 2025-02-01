package com.wsconge.repositories;

import com.wsconge.entities.DemandesConge;
import com.wsconge.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface IDemandeCongeRepository extends JpaRepository<DemandesConge,Long> {
     List<DemandesConge> findByUtilisateur(Utilisateur u);
}
