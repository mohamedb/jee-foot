/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.facades;

import football.entities.Federation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mohamed
 */
@Stateless
public class FederationFacade extends AbstractFacade<Federation> {
    @PersistenceContext(unitName = "FootBallProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FederationFacade() {
        super(Federation.class);
    }
       public boolean creer(String nom,String email, String motDePasse, String organisme){
//        if(utilisateurFacade.existe(email))
//           return false;
        Federation f = new Federation();
        f.setEmail(email);
        f.setMotDePasse(motDePasse);
        f.setNom(nom);
        f.setOrganisme(organisme);
        getEntityManager().persist(f);
        getEntityManager().flush();
        em.clear();
        return true;
    }
}
