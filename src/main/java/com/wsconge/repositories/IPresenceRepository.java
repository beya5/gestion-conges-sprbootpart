package com.wsconge.repositories;

import com.wsconge.entities.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPresenceRepository extends JpaRepository<Presence,Long> {
}
