package com.wsconge.services;

import com.wsconge.DTO.DemandesCongeDTO;
import com.wsconge.entities.*;
import com.wsconge.repositories.IDemandeCongeRepository;
import com.wsconge.repositories.IPolitiqueCongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service

public class DemCongeService implements IDemCongeService{
    @Autowired
    private IDemandeCongeRepository demandesCongeRepository;
    @Autowired
    private IPolitiqueCongeRepository politiqueCongeRepository;
    @Autowired
    private UtilisateurService utilisateurRepository;
    @Autowired
    private SoldeCongeService soldeCongeService;
    @Override
    public void requestLeave(DemandesCongeDTO demande) {
        log.println("Fetching policy with ID: " + demande.getIdpolitique());
        PolitiqueConge politiqueConge= politiqueCongeRepository.findById(demande.getIdpolitique()).get();
        log.println("Fetching user with ID: " + demande.getIdutilisateur());
        Utilisateur utilisateur= utilisateurRepository.getUtilisateurById(demande.getIdutilisateur());
        log.println("Checking user's leave balance...");
        if (utilisateur == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        demande.toString();
        if (utilisateur.getSoldeConge() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User has no leave balance assigned");
        }
        long soldeConge = utilisateur.getSoldeConge().getMaxJours();
        log.println("User's leave balance: " + soldeConge);
        if (demande.getDatedebut() == null || demande.getDatefin() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start and end dates must not be null");
        }


        DemandesConge demandesConge= new DemandesConge(
                demande.getDatedebut(),
                demande.getDatefin(),
                demande.getStatut(),
                utilisateur, politiqueConge);
        Long days=demandesConge.calculateDays();
        if (days > soldeConge) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La période demandée excède votre solde de congé.");
        }
        log.println("Days requested: " + days);
        log.println("Saving leave request...");
        demandesCongeRepository.save(demandesConge);
        System.out.println("Demande ajoutée avec succès!");


    }

    @Override
    public String trackRequest(Long id) {
        DemandesConge demande = demandesCongeRepository.findById(id).get();
        if (demande!=null){
            return "Status of request with ID " + id + ": " + demande.getStatus();
        }else{
            return "Request with ID " + id + " not found.";
        }
    }

    @Override
    public void acceptRequest(Long id) {
        DemandesConge demande= demandesCongeRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Demande non trouvée"));
        System.out.println("🔍 Avant mise à jour : " + demande.getStatus());
        Utilisateur utilisateur = demande.getUtilisateur();
        SoldeConge soldeConge = utilisateur.getSoldeConge();
        if (soldeConge == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'utilisateur n'a pas de solde de congé assigné.");
        }// Calcul du nombre de jours demandés
        Long days = demande.calculateDays();
        Long soldeActuel = Long.valueOf(utilisateur.getSoldeConge().getMaxJours());
        if (days > soldeActuel) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le solde de congé est insuffisant.");
        }
        // Mise à jour du solde
        Long nouveauSolde = soldeActuel - days;
        utilisateur.getSoldeConge().setMaxJours(Math.toIntExact(nouveauSolde));
        demande.setStatus("Acceptée");
            demandesCongeRepository.save(demande);
            soldeCongeService.addSoldeConge(soldeConge);
        System.out.println("✅ Après mise à jour : " + demande.getStatus());


    }

    @Override
    public void declineRequest(Long id) {
        DemandesConge demande= demandesCongeRepository.findById(id).get();
        if(demande!=null){
            demande.setStatus("Refusée");
            demandesCongeRepository.save(demande);
        }}
// CRUD Methods
        @Override
        public List<DemandesCongeDTO> getAllDemandes() {
            List<DemandesConge> demandes =  demandesCongeRepository.findAll();
            return demandes.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        }

    private DemandesCongeDTO convertToDTO(DemandesConge demande) {
        return new DemandesCongeDTO(
                demande.getId(),
                demande.getDatedebut(),
                demande.getDatefin(),
                demande.getStatus(),
                demande.getPolitique().getId(),
                demande.getUtilisateur().getId());
    }

    @Override

        public DemandesConge getDemandeById(Long id) {
            return demandesCongeRepository.findById(id).get();
        }
        @Override

        public void deleteDemande(Long id) {
            demandesCongeRepository.deleteById(id);
        }
        @Override
        public List<DemandesCongeDTO> getUserRequests(Long userId) {
        Utilisateur u=utilisateurRepository.getUtilisateurById(userId);
        if (u != null) {
            // Fetch the user's leave requests from the repository
            // Convert the list of DemandesConge entities to DemandesCongeDTO
            return demandesCongeRepository.findByUtilisateur(u)
                    .stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());

        }
        return new ArrayList<>();
        }
}

