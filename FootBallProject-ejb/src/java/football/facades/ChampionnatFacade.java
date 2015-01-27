/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.facades;

import football.entities.Championnat;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mohamed
 */
@Stateless
public class ChampionnatFacade extends AbstractFacade<Championnat> {
    @PersistenceContext(unitName = "FootBallProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChampionnatFacade() {
        super(Championnat.class);
    }
    public boolean creer(String nom, int annee, String description){
        Championnat c = new Championnat();
        c.setAnnee(annee);
        c.setDescription(description);
        c.setNom(nom);
        em.persist(c);
        em.flush();
        em.clear();
        return true;
    }
    
}
