package com.wsconge.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "presence")
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "presence_seq")
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;
    @Column(name = "statut")
    private String statut;
    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    @JsonBackReference
    private Utilisateur utilisateur;

    public Presence(Date date, String statut, Utilisateur utilisateur) {
        this.date = date;
        this.statut = statut;
        this.utilisateur = utilisateur;
    }

    public Presence() {

    }
}

