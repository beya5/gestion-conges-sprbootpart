
package com.wsconge.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "utilisateur_dep")
public class UtilisDep {
    @EmbeddedId
    private UtilisDepId id;
    @ManyToOne
    @MapsId("id_departement")
    @JoinColumn(name = "id_departement")
    private Departement iddepartement;
    @ManyToOne
    @MapsId("id_utilisateur")
    @JoinColumn(name = "id_utilisateur")
    @JsonBackReference
    private Utilisateur idutilisateur;
    private LocalDateTime dateEntree;

    public void setId(UtilisDepId id) {
        this.id = id;
    }

    public UtilisDepId getId() {
        return id;
    }

    public Departement getIddepartement() {
        return iddepartement;
    }

    public UtilisDep() {
    }

    public UtilisDep(Departement iddepartement, Utilisateur idutilisateur, LocalDateTime dateEntree) {
        this.iddepartement = iddepartement;
        this.idutilisateur = idutilisateur;
        this.dateEntree = dateEntree;
    }

    public void setIddepartement(Departement iddepartement) {
        this.iddepartement = iddepartement;
        if (id == null) {
            id = new UtilisDepId();
        }
        this.id.setId_departement(iddepartement.getId());// Set the ID in the composite key
    }
    public Utilisateur getIdutilisateur(){
        return idutilisateur;
    }
    public void setIdutilisateur(Utilisateur idutilisateur) {

        this.idutilisateur = idutilisateur;
        if (id == null) {
            id = new UtilisDepId();
        }
        this.id.setId_utilisateur(idutilisateur.getId());    }
    public LocalDateTime getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(LocalDateTime dateEntree) {
        this.dateEntree = dateEntree;
    }
}


