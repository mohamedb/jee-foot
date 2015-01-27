/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.facades;

import football.entities.ContratEntraineur;
import football.entities.Entraineur;
import football.entities.Equipe;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mohamed
 */
@Stateless
public class ContratEntraineurFacade extends AbstractFacade<ContratEntraineur> {
    @PersistenceContext(unitName = "FootBallProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratEntraineurFacade() {
        super(ContratEntraineur.class);
    }
    
    public boolean creer(Equipe equipe, Entraineur entraineur,Date dateDebut, Date dateFin){
        ContratEntraineur c = new ContratEntraineur();
        c.setDateDebut(dateDebut);
        c.setDateFin(dateFin);
        c.setEntraineur(entraineur);
        c.setEquipe(equipe);
        getEntityManager().persist(c);
        getEntityManager().flush();
        em.clear();
        return true;
    }
    
}
