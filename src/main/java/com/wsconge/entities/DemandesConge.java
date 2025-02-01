package com.wsconge.entities;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
@Table(name = "demandesConge")
public class DemandesConge {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="dem_seq" )
    private Long id;
    @Column(name = "datedeb")
    private Date datedebut;
    @Column(name = "datefin")
    private Date datefin;

    public DemandesConge(Date datedebut, Date datefin, String status, Utilisateur utilisateur, PolitiqueConge politique) {
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.status = status;
        this.utilisateur = utilisateur;
        this.politique = politique;
    }

    public DemandesConge(Date datedebut, Date datefin, String status){
        this.datedebut=datedebut;
        this.datefin=datefin;
        this.status=status;

    }

//    public long calculateDays() {
//        LocalDate startDate = new java.sql.Date(datedebut.getTime()).toLocalDate();
//        LocalDate endDate = new java.sql.Date(datefin.getTime()).toLocalDate();
//        return ChronoUnit.DAYS.between(startDate, endDate);
//    }
    @Column(name = "status")
    private String status;


    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public PolitiqueConge getPolitique() {
        return politique;
    }

    public void setPolitique(PolitiqueConge politique) {
        this.politique = politique;
    }

    @ManyToOne
//    @JoinColumn(name = "utilisateur_id") // Vérifiez bien cette colonne dans votre BDD
    private Utilisateur utilisateur;
    @ManyToOne
    private PolitiqueConge politique;

    public DemandesConge() {

    }

    public Long getId() {
        return id;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "DemandesConge{" +
                "id=" + id +
                ", datedebut=" + datedebut +
                ", datefin=" + datefin +
                ", status='" + status + '\'' +

                ", utilisateur=" + utilisateur +
                ", politique=" + politique +
                '}';
    }

    public Long calculateDays() {
        LocalDate startDate= new java.sql.Date(datedebut.getTime()).toLocalDate();
        LocalDate endDate= new java.sql.Date(datefin.getTime()).toLocalDate();
        return ChronoUnit.DAYS.between(startDate,endDate);
    }
}
