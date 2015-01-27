/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.facades;

import football.entities.ContratEntraineur;
import football.entities.Entraineur;
import football.entities.Utilisateur;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Mohamed
 */
@Stateless
public class EntraineurFacade extends AbstractFacade<Entraineur> {
    @PersistenceContext(unitName = "FootBallProject-ejbPU")
    private EntityManager em;
    private UtilisateurFacade utilisateurFacade = new UtilisateurFacade();
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntraineurFacade() {
        super(Entraineur.class);
    }
    
   public boolean creer(String nom,String email, String motDePasse, String bio){
//        if(utilisateurFacade.existe(email))
//           return false;
        Entraineur e = new Entraineur();
        e.setEmail(email);
        e.setMotDePasse(motDePasse);
        e.setNom(nom);
        e.setBio(bio);
        getEntityManager().persist(e);
        getEntityManager().flush();
        em.clear();
        return true;
    }
   public boolean isEntraineurLibre(Date dateDebut, Date dateFin, Long entraineurId){
        
        String queryString= "Select c From ContratEntraineur as c "
                          + "Where c.dateDebut < :dateDebut And c.dateFin >:dateFin ";
        Query query = getEntityManager().createQuery(queryString);
        query = query.setParameter("dateDebut",dateDebut,TemporalType.DATE);
        query = query.setParameter("dateFin",dateFin,TemporalType.DATE);
        List<ContratEntraineur> contrats  = query.getResultList();
        if(!contrats.isEmpty()){
            for(ContratEntraineur c:contrats){
                if(c.getEntraineur().getId()==entraineurId)
                    return false;
            }
        }
        return true;
   }
    public Entraineur login(String email, String motDePasse){
        try{
        String queryString= " select e From Entraineur as e "
                               + "Where e.email= :email And e.motDePasse=:motDePasse";
        Query query = getEntityManager().createQuery(queryString);
        query = query.setParameter("email",email).setParameter("motDePasse", motDePasse);
        
        Entraineur entraineur;
        entraineur = (Entraineur)query.getSingleResult();
        return entraineur;
        } 
        catch(NoResultException e) {
            return null;
        }
    }
   
    
}
