package com.wsconge.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="soldeconge")
public class SoldeConge {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "max_jours", nullable = false) // Ensure the column name matches your database schema
    private int maxJours;

    @OneToOne
    @JoinColumn(name = "solde_utilisateur_id")
    @JsonIgnore
    private Utilisateur soldeutilisateurid;
    public SoldeConge() {}

    public SoldeConge(int maxJours, Utilisateur soldeutilisateurid) {
        this.maxJours = maxJours;
        this.soldeutilisateurid = soldeutilisateurid;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMaxJours() {
        return maxJours;
    }

    public void setMaxJours(int maxJours) {
        this.maxJours = maxJours;
    }

    public Utilisateur getSoldeutilisateurid() {
        return soldeutilisateurid;
    }

    public void setSoldeutilisateurid(Utilisateur soldeutilisateurid) {
        this.soldeutilisateurid = soldeutilisateurid;
    }
    public void setUtilisateur(Utilisateur u){
        this.soldeutilisateurid=u;
    }
}
