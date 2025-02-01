package com.wsconge.repositories;

import com.wsconge.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository <Departement,Long> {

}
