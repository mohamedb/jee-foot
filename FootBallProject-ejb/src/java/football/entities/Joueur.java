/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Mohamed
 */
@Entity
public class Joueur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Column(name = "nom")
    protected String nom;
    
    @NotNull
    @Column(name = "prenom")
    protected String prenom;
    
    @Column(name = "maillot", nullable = false)
    protected int maillot;
    
    @NotNull
    @Column(name = "position")
    protected String position="Milieu";
    
    @Column(name = "bio", nullable = true)
    String bio;
    
    @OneToMany(mappedBy = "joueur",cascade = CascadeType.ALL)
    private Collection<ContratJoueur> contratJoueurs;
    
     @ManyToMany(targetEntity=MatchFoot.class, mappedBy = "joueurs")
     private List<MatchFoot> matchs;

    public void setMatchs(List<MatchFoot> matchs) {
        this.matchs = matchs;
    }

    public List<MatchFoot> getMatchs() {
        return matchs;
    }
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getMaillot() {
        return maillot;
    }

    public String getPosition() {
        return position;
    }

    public String getBio() {
        return bio;
    }

    public Collection<ContratJoueur> getContratJoueurs() {
        return contratJoueurs;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMaillot(int maillot) {
        this.maillot = maillot;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setContratJoueurs(Collection<ContratJoueur> contratJoueurs) {
        this.contratJoueurs = contratJoueurs;
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
        if (!(object instanceof Joueur)) {
            return false;
        }
        Joueur other = (Joueur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "football.entities.Joueur[ id=" + id + " ]";
    }
    
}
