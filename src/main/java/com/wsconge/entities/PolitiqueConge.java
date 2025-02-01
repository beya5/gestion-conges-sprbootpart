package com.wsconge.entities;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "politiqueConge")
public class PolitiqueConge {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "politiq_seq")
    private Long id;
    @Column(name = "typeConge")
    private String typeConge;
    @Column(name = "maxJours")
    private int maxJours;
    @Column(name = "description")
    private String desc;
    @OneToMany(mappedBy = "politique")
    private List<DemandesConge> demandesConge;

    public PolitiqueConge(Long id,String typeConge,int maxJours,String desc) {
        this.id = id;
        this.typeConge=typeConge;
        this.maxJours=maxJours;
        this.desc=desc;
    }
    public PolitiqueConge() {}

    public String getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(String typeConge) {
        this.typeConge = typeConge;
    }

    public int getMaxJours() {
        return maxJours;
    }

    public void setMaxJours(int maxJours) {
        this.maxJours = maxJours;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getId() {
        return this.id=id;
    }
}
