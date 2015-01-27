/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author cheichnah
 */
@Entity
public class Championnat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Column(name = "nom")
    String nom;
    
    @Column(name = "description", nullable = true)
    String description;
    
    @Column(name = "annee",nullable = true)
    private int annee;
    
    @OneToMany(mappedBy = "championnat", cascade = CascadeType.ALL)
    private Collection<Participation> participations;

    @OneToMany(mappedBy = "championnat", cascade = CascadeType.ALL)
    private Collection<MatchFoot> matchs;

    public Collection<MatchFoot> getMatchs() {
        return matchs;
    }

    public void setMatchs(Collection<MatchFoot> matchs) {
        this.matchs = matchs;
    }
    
    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getAnnee() {
        return annee;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
    
    public Collection<Participation> getParticipations() {
        return participations;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setParticipations(Collection<Participation> participations) {
        this.participations = participations;
    }
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Championnat)) {
            return false;
        }
        Championnat other = (Championnat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "football.entities.Championnat[ id=" + id + " ]";
    }
  
    private String libelle;

    /**
     * Get the value of libelle
     *
     * @return the value of libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Set the value of libelle
     *
     * @param libelle new value of libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
  
}
