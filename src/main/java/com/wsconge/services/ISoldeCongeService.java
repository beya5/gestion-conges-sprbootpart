package com.wsconge.services;

import com.wsconge.entities.SoldeConge;

public interface ISoldeCongeService {
    int trackLeaveBalance(Long userId);

    void addSoldeConge(SoldeConge soldeConge);

    void deleteSoldeConge(Long id);

    SoldeConge getSoldeCongeById(Long id);
}
