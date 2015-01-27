/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mohamed
 */
@Entity
public class MatchFoot implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(optional = false)
    private Equipe recepteur;
    
    @ManyToOne(optional = false)
    private Equipe visiteur;
    
    @ManyToOne(optional = false)
    private Championnat championnat;
    
    @Column(name = "dateDebut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut= new Date();
    
    @Column(name = "isCommence")
    private boolean isCommence=false;
    
    @Column(name = "butVisiteur")
    private int butVisiteur=0;
    
    @Column(name = "butReceveur")
    private int butReceveur=0;
 
    @ManyToMany(targetEntity=Joueur.class)
    private List<Joueur> joueurs;

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Equipe getRecepteur() {
        return recepteur;
    }

    public Equipe getVisiteur() {
        return visiteur;
    }

    public Championnat getChampionnat() {
        return championnat;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public boolean isIsCommence() {
        return isCommence;
    }

    public int getButVisiteur() {
        return butVisiteur;
    }

    public int getButReceveur() {
        return butReceveur;
    }

    public void setRecepteur(Equipe recepteur) {
        this.recepteur = recepteur;
    }

    public void setVisiteur(Equipe visiteur) {
        this.visiteur = visiteur;
    }

    public void setChampionnat(Championnat championnat) {
        this.championnat = championnat;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setIsCommence(boolean isCommence) {
        this.isCommence = isCommence;
    }

    public void setButVisiteur(int butVisiteur) {
        this.butVisiteur = butVisiteur;
    }

    public void setButReceveur(int butReceveur) {
        this.butReceveur = butReceveur;
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
        if (!(object instanceof MatchFoot)) {
            return false;
        }
        MatchFoot other = (MatchFoot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "football.entities.Match[ id=" + id + " ]";
    }
    
}
