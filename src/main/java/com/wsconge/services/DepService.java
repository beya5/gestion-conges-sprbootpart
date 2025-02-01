package com.wsconge.services;

import com.wsconge.entities.Departement;
import com.wsconge.repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepService implements IDepService{
@Autowired
private DepartementRepository departementRepository;

    @Override
    public void addDepartement(Departement departement) {
        departementRepository.save(departement);
    }

    @Override
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement getDepartementById(Long id) {
        return departementRepository.findById(id).orElse(null);
    }

    @Override
    public void updateDepartement(Long id,Departement departement) {
        Departement existingDepartement = departementRepository.findById(id).get();
        if (existingDepartement != null) {
            existingDepartement.setNom(departement.getNom());
            existingDepartement.setDescription(departement.getDescription());
            departementRepository.save(existingDepartement);
        }

    }

    @Override
    public void deleteDepartement(Long id) {
        departementRepository.deleteById(id);
    }
}
