/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.facades;

import football.entities.ContratJoueur;
import football.entities.Equipe;
import football.entities.Joueur;
import java.util.Date;
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
public class JoueurFacade extends AbstractFacade<Joueur> {
    @PersistenceContext(unitName = "FootBallProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JoueurFacade() {
        super(Joueur.class);
    }
    
    public String creer(String nom, String prenom, int maillot, String position, String bio,
            Equipe equipe, Date dateDebut, Date dateFin, int salaire){
        if(getByNomPrenom(nom, prenom)!=null)
            return "Joueur <b>"+nom+prenom+"</b> existe déjà !";
        Joueur joueur = new Joueur();
        joueur.setBio(bio);
        joueur.setMaillot(maillot);
        joueur.setNom(nom);
        joueur.setPrenom(prenom);
        joueur.setPosition(position);
        
        
        ContratJoueur contrat = new ContratJoueur();
        contrat.setDateDebut(dateDebut);
        contrat.setDateFin(dateFin);
        contrat.setEquipe(equipe);
        contrat.setJoueur(joueur);
        em.persist(joueur);
        em.persist(contrat);
        em.flush();
        em.clear();
        return "Ajout du joueur et création du contrat avec succès";
    }
    
    public Joueur getByNomPrenom(String nom,String prenom){
        try{
        String queryString= " Select j From Joueur as j "
                           + "Where j.nom= :nom And j.prenom=:prenom";
        Query query = getEntityManager().createQuery(queryString);
        query = query.setParameter("nom",nom).setParameter("prenom", prenom);
        Joueur j =(Joueur)query.getSingleResult(); /* peut générer exception ! */
        return j;
        }
        catch(NoResultException e) {
            return null;
        }
    }
}
