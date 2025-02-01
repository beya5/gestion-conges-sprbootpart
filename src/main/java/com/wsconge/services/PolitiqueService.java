package com.wsconge.services;

import com.wsconge.entities.Departement;
import com.wsconge.entities.PolitiqueConge;
import com.wsconge.repositories.IPolitiqueCongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class PolitiqueService implements IPolitiqueService{
    @Autowired
    private IPolitiqueCongeRepository politiqueCongeRepository;

    @Override
    public List<PolitiqueConge> getallPolitiques() {
        return politiqueCongeRepository.findAll();
    }

    @Override
    public PolitiqueConge getPolitiqueById(Long id) {
        return politiqueCongeRepository.findById(id).get();
    }

    @Override
    public void addPolitique(PolitiqueConge politiqueConge) {
         politiqueCongeRepository.save(politiqueConge);
    }

    @Override
    public void updatePolitique(Long id, PolitiqueConge updatedPolitique) {
        PolitiqueConge pol = politiqueCongeRepository.findById(id).get();
        if (pol != null) {
            pol.setTypeConge(updatedPolitique.getTypeConge());
            pol.setMaxJours(updatedPolitique.getMaxJours());
            pol.setDesc(updatedPolitique.getDesc());
            politiqueCongeRepository.save(pol);
        }

    }

    @Override
    public void deletePolitique(Long id) {
        politiqueCongeRepository.deleteById(id);
    }
}
