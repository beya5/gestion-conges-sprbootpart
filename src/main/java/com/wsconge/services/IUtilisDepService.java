package com.wsconge.services;

import com.wsconge.DTO.ListeDepartementsDTO;
import com.wsconge.entities.UtilisDep;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IUtilisDepService {

    UtilisDep addUnion(Long departementId, Long utilisateurId, LocalDateTime dateEntree);

    //    public UtilisDep getUnionById(Long id) {
//        return utilisDepRepository.findById(id).get();
//    }

    List<Map<String, Object>> getDepartmentsWithUserAssignment(Long userId);

    UtilisDep getUnion(Long departementId, Long utilisateurId);
    UtilisDep updateUnion(Long departementId, Long utilisateurId, LocalDateTime dateEntree);


    void deleteUnion(Long departementId, Long utilisateurId);


    void deleteByUtilisateurId(Long userId);

    void updateUserDepartments(Long userId, List<Long> listedeps);
}
