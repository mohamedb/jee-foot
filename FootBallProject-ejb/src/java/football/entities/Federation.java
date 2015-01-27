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

/**
 *
 * @author Mohamed
 */
@Entity
public class Federation extends Utilisateur implements Serializable {
   
    
    @Column(name = "organisme", nullable = true)
    String organisme;

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }

    public String getOrganisme() {
        return organisme;
    }
    
    
    
}
