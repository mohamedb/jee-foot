/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.facades;

import football.entities.Championnat;
import football.entities.Equipe;
import football.entities.MatchFoot;
import football.entities.Participation;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Mohamed
 */
@Stateless
public class MatchFootFacade extends AbstractFacade<MatchFoot> {
    @EJB
    private ParticipationFacade participationFacade;
    
    @PersistenceContext(unitName = "FootBallProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatchFootFacade() {
        super(MatchFoot.class);
    }
     
    public boolean creer(Championnat championnat, Equipe visiteur, Equipe recepteur){
        MatchFoot m = new MatchFoot();
        m.setChampionnat(championnat);
        m.setVisiteur(visiteur);
        m.setRecepteur(recepteur);
        getEntityManager().persist(m);        
        getEntityManager().flush();
        return true;
    }
    public String editerResultat(Long idMatch, int butVisiteur, int butRecepteur, Equipe visiteur, Equipe recepteur){
        MatchFoot m=this.find(idMatch);
        m.setButVisiteur(butVisiteur);
        m.setButReceveur(butRecepteur);
        m.setIsCommence(true);
        /**
         * Mise a jour des points ! en passant par les participations!
         * Recuperer la Participation par idMatch 
         */
        Participation visiteurParticipation = participationFacade.findByChampionnatEtEquipe(m.getChampionnat(), visiteur);
        Participation recepteurParticipation = participationFacade.findByChampionnatEtEquipe(m.getChampionnat(), recepteur);
        
        if(butRecepteur<butVisiteur){
            visiteurParticipation.setNombrePoint(3+ visiteurParticipation.getNombrePoint());
        }
        if(butRecepteur>butVisiteur)
            recepteurParticipation.setNombrePoint(3+ visiteurParticipation.getNombrePoint());
        if(butRecepteur==butVisiteur){
            recepteurParticipation.setNombrePoint(1+ visiteurParticipation.getNombrePoint());
            visiteurParticipation.setNombrePoint(1+ visiteurParticipation.getNombrePoint());
        }
        getEntityManager().persist(m);        
        getEntityManager().flush();
        return "ok";
    }
    
    public List<MatchFoot> getMatchParEquipe(Equipe equipe){
        String queryString= " select m From MatchFoot as m Where m.recepteur= :equipe Or m.visiteur=:equipe";
        Query query = getEntityManager().createQuery(queryString);
        query = query.setParameter("equipe",equipe);
        List<MatchFoot> matchs = (List<MatchFoot>)query.getResultList();
         
        return matchs;
    }
}
