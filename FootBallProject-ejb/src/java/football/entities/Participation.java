/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Mohamed
 */
@Entity
public class Participation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(optional = false)
    private Equipe equipe;
    
    @ManyToOne(optional = false)
    private Championnat championnat;
    
    @Column(name = "nombrePoint", nullable = true)
    int nombrePoint;
    
    @Column(name = "classement", nullable = true)
    int classement;
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public Championnat getChampionnat() {
        return championnat;
    }

    public int getNombrePoint() {
        return nombrePoint;
    }

    public int getClassement() {
        return classement;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public void setChampionnat(Championnat championnat) {
        this.championnat = championnat;
    }

    public void setNombrePoint(int nombrePoint) {
        this.nombrePoint = nombrePoint;
    }

    public void setClassement(int classement) {
        this.classement = classement;
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
        if (!(object instanceof Participation)) {
            return false;
        }
        Participation other = (Participation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "football.entities.Participation[ id=" + id + " ]";
    }
    
}
