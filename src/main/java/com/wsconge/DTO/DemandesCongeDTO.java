package com.wsconge.DTO;

import java.util.Date;

public class DemandesCongeDTO {

    private Long id;
    private Date datedebut;  // ISO date format (e.g., "2025-01-01")
    private Date datefin;
    private String statut;




    private Long idpolitique;  // Assuming this corresponds to a "Politique" entity
    private Long idutilisateur;  // Assuming this corresponds to a "Utilisateur" entity

    // Constructors
    public DemandesCongeDTO() {}

    public DemandesCongeDTO(Long id, Date datedebut, Date datefin, String statut, Long idpolitique, Long idutilisateur) {
        this.id = id;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.statut = statut;
        this.idpolitique = idpolitique;
        this.idutilisateur = idutilisateur;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Long getIdpolitique() {
        return idpolitique;
    }

    public void setIdpolitique(Long idpolitique) {
        this.idpolitique = idpolitique;
    }

    public Long getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Long idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    @Override
    public String toString() {
        return "DemandesCongeDTO{" +
                "id=" + id +
                ", datedebut='" + datedebut + '\'' +
                ", datefin='" + datefin + '\'' +
                ", statut='" + statut + '\'' +
                ", idpolitique=" + idpolitique +
                ", idutilisateur=" + idutilisateur +
                '}';
    }
}