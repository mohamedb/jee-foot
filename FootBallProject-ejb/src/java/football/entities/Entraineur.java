package football.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Mohamed
 */
@Entity
@Table(name = "Entraineur")
public class Entraineur extends Utilisateur implements Serializable {

    @Column(name = "bio", nullable = true)
    String bio;
    @OneToMany(mappedBy = "entraineur")
    private Collection<ContratEntraineur> contratEntraineurs;

    public Collection<ContratEntraineur> getContratEntraineurs() {
        return contratEntraineurs;
    }

    public String getBio() {
        return bio;
    }

    public void setContratEntraineurs(Collection<ContratEntraineur> contratEntraineurs) {
        this.contratEntraineurs = contratEntraineurs;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}
