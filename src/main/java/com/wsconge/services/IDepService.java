package com.wsconge.services;

import com.wsconge.entities.Departement;

import java.util.*;

public interface IDepService {
    void addDepartement(Departement departement);
    List<Departement> getAllDepartements();
    Departement getDepartementById(Long id);
    void updateDepartement(Long id,Departement departement);
    void deleteDepartement(Long id);
}
