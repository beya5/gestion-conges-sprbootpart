package com.wsconge.services;

import com.wsconge.DTO.DemandesCongeDTO;
import com.wsconge.entities.DemandesConge;

import java.util.List;

public interface IDemCongeService {
    String trackRequest(Long id);
    void acceptRequest(Long id);
    void declineRequest(Long id);

    // CRUD Methods
    List<DemandesCongeDTO> getAllDemandes();

    DemandesConge getDemandeById(Long id);
    void requestLeave(DemandesCongeDTO demande);

    void deleteDemande(Long id);

    List<DemandesCongeDTO> getUserRequests(Long userId);

//    void updateDemande(Long id, DemandesConge updatedDemande);
}