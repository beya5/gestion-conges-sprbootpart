package com.wsconge.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="utilisateur")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"utilisateurDeps","utilisDeps"})
public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;  // Add serialVersionUID

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utilis_seq")
    private Long id_utilis;
    @Column(name="Nom")
    private String nom;
    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private Role role;
    @Column(name = "mdp")
    private String mdp;
    @Column(name = "email")
    private String email;
    @Column(name = "tel")
    private int tel;
    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<DemandesConge> demandesConge;

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<Presence> presences;
    @OneToMany(mappedBy = "idutilisateur", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<UtilisDep> utilisateurDeps;
@ManyToOne
@JoinColumn(name = "departement_id")
private Departement departement;
    @OneToOne(mappedBy = "soldeutilisateurid",cascade = CascadeType.ALL)
    private SoldeConge soldeConge;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Utilisateur manager;

    @OneToMany(mappedBy = "manager")
    private List<Utilisateur> subordonnnes;

    public List<UtilisDep> getUtilisDeps(){
        return utilisateurDeps;
    }
    public void setUtilisDeps(List<UtilisDep> utilisDeps){
        this.utilisateurDeps=utilisDeps;
    }
    public Utilisateur() {}

    public Utilisateur(String nom,Role role,String mdp,String email,int tel, SoldeConge solde) {
        this.nom = nom;
        this.role = role;
        this.mdp = mdp;
        this.email = email;
        this.tel = tel;
        this.soldeConge=solde;
    }

    public Long getId() {
        return id_utilis;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public Enum getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public List<DemandesConge> getDemandesConge() {
        return demandesConge;
    }

    public void setDemandesConge(List<com.wsconge.entities.DemandesConge> demandesConge) {
        this.demandesConge = demandesConge;
    }

    public List<Presence> getPresences() {
        return presences;
    }

    public void setPresences(List<Presence> presences) {
        this.presences = presences;
    }

    public List<UtilisDep> getUtilisateurDeps() {
        return utilisateurDeps;
    }

    public void setUtilisateurDeps(List<UtilisDep> utilisateurDeps) {
        this.utilisateurDeps = utilisateurDeps;
    }

    public com.wsconge.entities.Departement getDepartement() {
        return departement;
    }

    public void setDepartement(com.wsconge.entities.Departement departement) {
        this.departement = departement;
    }

    public SoldeConge getSoldeConge() {
        return soldeConge;
    }

    public void setSoldeConge(SoldeConge soldeConge) {
        this.soldeConge = soldeConge;
    }

    public Utilisateur getManager() {
        return manager;
    }

    public void setManager(Utilisateur manager) {
        this.manager = manager;
    }

    public List<Utilisateur> getSubordonnnes() {
        return subordonnnes;
    }

    @Override
    public String toString() {
        return "add user dry{" +
                "nom='" + nom + '\'' +
                ", role=" + role +
                ", mdp='" + mdp + '\'' +
                ", email='" + email + '\'' +
                ", tel=" + tel +
                '}';
    }

    public void setSubordonnnes(List<Utilisateur> subordonnnes) {
        this.subordonnnes = subordonnnes;
    }

}
