package com.wsconge.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UtilisDepId implements Serializable {
    @Column(name = "id_departement")

    private Long id_departement;
    @Column(name = "id_utilisateur")

    private Long id_utilisateur;

    public UtilisDepId() {

    }
    public UtilisDepId(Long id_departement, Long id_utilisateur) {
        this.id_departement = id_departement;
        this.id_utilisateur = id_utilisateur;
    }


    public Long getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(Long id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Long getId_departement() {
        return id_departement;
    }

    public void setId_departement(Long id_departement) {
        this.id_departement = id_departement;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtilisDepId that = (UtilisDepId) o;
        return Objects.equals(id_departement, that.id_departement) &&
                Objects.equals(id_utilisateur, that.id_utilisateur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_departement, id_utilisateur);
    }
}
