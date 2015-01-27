/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.facades;

import football.entities.Entraineur;
import football.entities.Equipe;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Mohamed
 */
@Stateless
public class EquipeFacade extends AbstractFacade<Equipe> {
    @PersistenceContext(unitName = "FootBallProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipeFacade() {
        super(Equipe.class);
    }
    
    public boolean creer(String nom, int points){
        Equipe e = new Equipe();
        e.setNom(nom);
        e.setPoints(points);
        getEntityManager().persist(e);
        getEntityManager().flush();
        em.clear();
        return true;
    }

    
     
}
