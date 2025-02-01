package com.wsconge.repositories;

import com.wsconge.entities.UtilisDep;
import com.wsconge.entities.UtilisDepId;
import com.wsconge.entities.Utilisateur;
import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface UtilisDepRepository extends JpaRepository<UtilisDep, Object> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UtilisDep u WHERE u.id.id_departement = :departmentId AND u.id.id_utilisateur = :userId")
    boolean existsByIds(@Param("departmentId") Long departmentId, @Param("userId") Long userId);
    public List<UtilisDep> findByIdutilisateur(Utilisateur user) ;

}

