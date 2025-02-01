package com.wsconge.services;

import com.wsconge.entities.PolitiqueConge;

import java.util.List;

public interface IPolitiqueService {
    List<PolitiqueConge> getallPolitiques();

    PolitiqueConge getPolitiqueById(Long id);

    void addPolitique(PolitiqueConge politiqueConge);

    void updatePolitique(Long id, PolitiqueConge updatedPolitique);

    void deletePolitique(Long id);
}
