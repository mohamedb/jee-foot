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
 * @author Mohamed
 */
@Entity
public class Equipe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Column(name = "nom")
    String nom;
    
    @NotNull
    @Column(name = "points")
    int points;
    
    @OneToMany(mappedBy = "equipe",cascade = CascadeType.ALL)
    private Collection<ContratEntraineur> contratEntraineurs;

   @OneToMany(mappedBy = "equipe",cascade = CascadeType.ALL)
    private Collection<ContratJoueur> contratJoueurs;

   
    
    @OneToMany(mappedBy = "visiteur",cascade = CascadeType.ALL)
    private Collection<MatchFoot> matchsExterieur;
    
    @OneToMany(mappedBy = "recepteur",cascade = CascadeType.ALL)
    private Collection<MatchFoot> matchsInterieur;
    
    @OneToMany(mappedBy = "equipe")
    private Collection<Participation> participations;

    public void setMatchsExterieur(Collection<MatchFoot> matchsExterieur) {
        this.matchsExterieur = matchsExterieur;
    }
    public Collection<ContratJoueur> getContratJoueurs() {
        return contratJoueurs;
    }

    public void setContratJoueurs(Collection<ContratJoueur> contratJoueurs) {
        this.contratJoueurs = contratJoueurs;
    }
    
    public void setMatchsInterieur(Collection<MatchFoot> matchsInterieur) {
        this.matchsInterieur = matchsInterieur;
    }

    
     public Collection<MatchFoot> getMatchsExterieur() {
        return matchsExterieur;
    }

    public Collection<MatchFoot> getMatchsInterieur() {
        return matchsInterieur;
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Collection<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(Collection<Participation> participations) {
        this.participations = participations;
    }
     
    public Collection<ContratEntraineur> getContratEntraineurs() {
        return contratEntraineurs;
    }

    public void setContratEntraineurs(Collection<ContratEntraineur> contratEntraineurs) {
        this.contratEntraineurs = contratEntraineurs;
    }
    
   
    
    /*
    *OnetoMany avec contrat !
    */
    public String getNom() {
        return nom;
    }

    public int getPoints() {
        return points;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPoints(int points) {
        this.points = points;
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
        if (!(object instanceof Equipe)) {
            return false;
        }
        Equipe other = (Equipe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "football.entities.Equipe[ id=" + id + " ]";
    }
    
}
