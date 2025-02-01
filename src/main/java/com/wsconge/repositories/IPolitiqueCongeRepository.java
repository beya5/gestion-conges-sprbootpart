package com.wsconge.repositories;

import com.wsconge.entities.PolitiqueConge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPolitiqueCongeRepository extends JpaRepository<PolitiqueConge,Long> {
}
