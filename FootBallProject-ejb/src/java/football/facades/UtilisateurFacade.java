/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.facades;

import football.entities.Utilisateur;
import football.facades.AbstractFacade;
import java.util.ArrayList;
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
public class UtilisateurFacade extends AbstractFacade<Utilisateur> {
    @PersistenceContext(unitName = "FootBallProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }
    
    public boolean creer(String nom,String email, String motDePasse){
        if(existe(email))
           return false;
        Utilisateur u = new Utilisateur();
        u.setEmail(email);
        u.setMotDePasse(motDePasse);
        u.setNom(nom);
        getEntityManager().persist(u);
        getEntityManager().flush();
        return true;
    }
    public Utilisateur getUtilisateurParEmail(String email){
        try{
        String queryString= " select u From Utilisateur as u Where u.email= :email";
        Query query = getEntityManager().createQuery(queryString);
        query = query.setParameter("email",email);
        Utilisateur utilisateur;
        utilisateur = (Utilisateur)query.getSingleResult();
        return utilisateur;
        } 
        catch(NoResultException e) {
            return null;
        }
    }
    
    public List<Utilisateur> listerParRole(String typeRole){
        
        return new ArrayList<Utilisateur>();
    }
    
    public boolean existe(String email){
        return getUtilisateurParEmail(email)!=null;
    }
    
    public Utilisateur login(String email, String motDePasse){
        try{
        String queryString= " select u From Utilisateur as u "
                               + "Where u.email= :email And u.motDePasse=:motDePasse";
        Query query = getEntityManager().createQuery(queryString);
        query = query.setParameter("email",email).setParameter("motDePasse", motDePasse);
        
        Utilisateur utilisateur;
        utilisateur = (Utilisateur)query.getSingleResult();
        return utilisateur;
        } 
        catch(NoResultException e) {
            return null;
        }
    }
}
