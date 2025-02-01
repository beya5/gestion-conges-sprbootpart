package com.wsconge.services;

import com.wsconge.DTO.ListeDepartementsDTO;
import com.wsconge.entities.*;
import com.wsconge.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UtilisDepService implements IUtilisDepService{
    @Autowired
    private UtilisDepRepository utilisDepRepository;
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private IUtilisateurRepository utilisateurRepository;
    @Override
    public UtilisDep addUnion(Long departementId, Long utilisateurId, LocalDateTime dateEntree) {
        // Fetch associated entities
        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new EntityNotFoundException("Departement not found with ID: " + departementId));
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur not found with ID: " + utilisateurId));

        // Set up the UtilisDep entity
        UtilisDepId id = new UtilisDepId(departementId, utilisateurId);
        UtilisDep utilisDep = new UtilisDep();
        utilisDep.setId(id);
        utilisDep.setIddepartement(departement);
        utilisDep.setIdutilisateur(utilisateur);
        utilisDep.setDateEntree(dateEntree);

        return utilisDepRepository.save(utilisDep);
    }
    @Override
    public List<Map<String, Object>> getDepartmentsWithUserAssignment(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        List<Departement> allDepartments = departementRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Departement dept : allDepartments) {
            Map<String, Object> departmentInfo = new HashMap<>();
            departmentInfo.put("id", dept.getId());
            departmentInfo.put("name", dept.getNom());
            Utilisateur utilisateur = utilisateurRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Utilisateur not found"));

            boolean isAssigned = utilisDepRepository.existsByIds(dept.getId(), utilisateur.getId());
            departmentInfo.put("isAssigned", isAssigned);
            result.add(departmentInfo);
        }
        return result;
    }

    @Override
    public UtilisDep getUnion(Long departementId, Long utilisateurId) {
        return null;
    }

    @Override
    public UtilisDep updateUnion(Long departementId, Long utilisateurId, LocalDateTime dateEntree) {
        return null;
    }

    @Override
    public void deleteUnion(Long departementId, Long utilisateurId) {

    }

//    public UtilisDep getUnion(Long departementId, Long utilisateurId) {
//        UtilisDepId id = new UtilisDepId(departementId, utilisateurId);
//        return utilisDepRepository.findById();
//    }
//    @Override

//    public UtilisDep updateUnion(Long departementId, Long utilisateurId, LocalDateTime dateEntree) {
//        UtilisDepId id = new UtilisDepId(departementId, utilisateurId);
//        // Fetch the existing relationship
//        UtilisDep utilisDep = utilisDepRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("UtilisDep not found with provided IDs"));
//        Departement departement = departementRepository.findById(departementId)
//                .orElseThrow(() -> new EntityNotFoundException("Departement not found"));
//        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
//                .orElseThrow(() -> new EntityNotFoundException("Utilisateur not found"));
//
//        // Set the full entities
//        utilisDep.setIddepartement(departement);
//        utilisDep.setIdutilisateur(utilisateur);
//        utilisDep.setDateEntree(dateEntree);
//        return utilisDepRepository.save(utilisDep);
//    }
//    @Override
//    public void deleteUnion(Long id) {
//    utilisDepRepository.deleteById(id);
//    }
//    public void deleteUnion(Long departementId, Long utilisateurId) {
//        UtilisDepId id = new UtilisDepId(departementId, utilisateurId);
//        utilisDepRepository.deleteById(id);
//    }

    @Override
    public void deleteByUtilisateurId(Long userId) {

    }

    //@Override
//public void deleteByUtilisateurId(Long userId) {
//    // Delete all UtilisDep entries for the user
//    // Example using JPA query
//    utilisDepRepository.deleteAllByUtilisateurId(userId);
//}
    @Override
    public void updateUserDepartments(Long userId, List<Long> departmentIds) {
        if (userId == null || departmentIds == null || departmentIds.isEmpty()) {
            throw new IllegalArgumentException("User ID and department IDs cannot be null");
        }

        // Verify if the user exists
        Utilisateur user = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + userId));

        // Fetch all departments by their IDs
        List<Departement> departments = new ArrayList<>();
        for (long id : departmentIds
             ) {
            departments.add(departementRepository.findById(id).get());
        }
        if (departments.isEmpty()) {
            throw new IllegalArgumentException("No valid departments found for provided IDs.");
        }
        // Fetch existing user-department associations
        List<UtilisDep> existingAssignments = utilisDepRepository.findByIdutilisateur(user);

        // Remove old associations not in the new list
        for (UtilisDep existingAssignment : existingAssignments) {
            if (!departmentIds.contains(existingAssignment.getIddepartement().getId())) {
                utilisDepRepository.deleteById(existingAssignment.getId());
            }
        }

        // Add new associations that don't already exist
        for (Departement department : departments) {
            UtilisDepId id = new UtilisDepId(department.getId(), user.getId());
            UtilisDep utilisDep = new UtilisDep();
            utilisDep.setId(id);
            utilisDep.setIddepartement(department);
            utilisDep.setIdutilisateur(user);
            utilisDep.setDateEntree(LocalDateTime.now());
            utilisDepRepository.save(utilisDep);
            System.out.println("Departments updated for user ID: " + department.getNom());
        }

        System.out.println("Departments updated for user ID: " + userId);

    }
    }

