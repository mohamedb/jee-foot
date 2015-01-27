/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.facades;

import football.entities.Championnat;
import football.entities.Equipe;
import football.entities.Participation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Mohamed
 */
@Stateless
public class ParticipationFacade extends AbstractFacade<Participation> {
    @PersistenceContext(unitName = "FootBallProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParticipationFacade() {
        super(Participation.class);
    }
    
    public String creer(Championnat championnat,Equipe equipe){
        if(isExistsEquipeDansChampionnat(championnat, equipe)){
            return "Equipe déjà dans le championnat";
        }
        Participation p = new Participation();
        p.setChampionnat(championnat);
        p.setEquipe(equipe);
        p.setClassement(19);
        p.setNombrePoint(0);
        /* Parfois faut redemarer Netbeans pour qu'il prend en considération le persiste !*/
        /*
        Collection<Participation> cp = new ArrayList<Participation>();
        cp.add(p);
        championnat.setParticipations(cp);
        getEntityManager().persist(championnat);
        */
        getEntityManager().persist(p);        
        getEntityManager().flush();
        return "Equipe <b>"+equipe.getNom()+"</b> Ajoutée avec succès";
    }
    public boolean isExistsEquipeDansChampionnat(Championnat championnat,Equipe equipe){
        String queryString= " select p From Participation as p "
                               + "Where p.championnat= :championnat And p.equipe=:equipe";
        Query query = getEntityManager().createQuery(queryString);
        query = query.setParameter("championnat",championnat);
        query=query.setParameter("equipe", equipe);
        List<Participation> participations = query.getResultList();
        return !participations.isEmpty();
    }
    public Participation findByChampionnatEtEquipe(Championnat championnat, Equipe equipe){
        try{
        String queryString= " select p From Participation as p "
                               + "Where p.championnat= :championnat And p.equipe=:equipe";
        Query query = getEntityManager().createQuery(queryString);
        query = query.setParameter("championnat",championnat);
        query=query.setParameter("equipe", equipe);
        return (Participation) query.getSingleResult(); 
        }
        catch(NoResultException e) {
            return null;
        }
    }
}
