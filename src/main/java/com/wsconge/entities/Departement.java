package com.wsconge.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "departement")
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "dep_seq")
    @Column(name = "id_dep")
    private Long id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "departement")
    private List<Utilisateur> utilisateurs;

    @OneToMany(mappedBy = "iddepartement", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UtilisDep> utilisateurDeps;

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public List<UtilisDep> getUtilisateurDeps() {
        return utilisateurDeps;
    }

    public void setUtilisateurDeps(List<UtilisDep> utilisateurDeps) {
        this.utilisateurDeps = utilisateurDeps;
    }

    public Departement(String nom, String desc) {
        this.nom = nom;
        this.description = desc;
    }
    public Departement() {}

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
