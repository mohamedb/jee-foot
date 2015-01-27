/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mohamed
 */
@Entity
public class ContratJoueur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(optional = false)
    private Joueur joueur;
    
    @ManyToOne(optional = true)
    private Equipe equipe;

    @Column(name = "dateDebut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Column(name = "dateFin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    
    @Column(name = "salaire")
    private int salaire=5000;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
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
        if (!(object instanceof ContratJoueur)) {
            return false;
        }
        ContratJoueur other = (ContratJoueur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "football.entities.ContratJoueur[ id=" + id + " ]";
    }
    
}
