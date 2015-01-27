/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import football.entities.Equipe;
import football.entities.Joueur;
import football.entities.MatchFoot;
import football.facades.EquipeFacade;
import football.facades.JoueurFacade;
import football.facades.MatchFootFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mohamed
 */
@Stateless
@LocalBean
public class GestionEquipe {
    @EJB
    private MatchFootFacade matchFootFacade;
    @EJB
    private JoueurFacade joueurFacade;
    @PersistenceContext(unitName = "FootBallProject-ejbPU")
    private EntityManager em;
    @EJB
    private EquipeFacade equipeFacade;
    
    
    public List<Equipe> getEquipes(){
        return equipeFacade.findAll();
    }
    public boolean supprimer(Long id){
        if(equipeFacade.find(id)!=null){
            equipeFacade.remove(equipeFacade.find(id));
            return true;
        }
        return false;
    }
    public boolean creerEquipe(String nom, int points){
        return equipeFacade.creer(nom, points);
    }
    
    public String creerJoueur(String nom, String prenom, int maillot, String position, String bio,
            Long idEquipe, Date dateDebut, Date dateFin, int salaire){
        return joueurFacade.creer(nom, prenom, maillot, position, bio,
                equipeFacade.find(idEquipe), dateDebut, dateFin, salaire);
    }

    public List<MatchFoot> getMatchs(Equipe equipe) {
        return matchFootFacade.getMatchParEquipe(equipe);
    }
    
    /* Cette methode c est essentienllement pour updater la session ! */
    public Equipe getEquipe(Equipe e){
        return equipeFacade.find(e.getId());
    }
    /**
     * Cette methode et toute sa logique a besoin d'un
     * refactoring est necessaire !
     * @param equipe
     * @param idMatch
     * @param idsJoueur
     * @return 
     */
    public String creerListeJoueurPourMatch(Equipe equipe,Long idMatch,Long[] idsJoueur){
         StringBuilder messageBuilder = new StringBuilder();
        List<Joueur> joueurs = new ArrayList<Joueur>();
        MatchFoot match = (MatchFoot) matchFootFacade.find(idMatch);
        for(Long idJoueur: idsJoueur){
            Joueur joueurFromDb = joueurFacade.find(idJoueur);
            if(joueurFromDb!=null){
                joueurs.add(joueurFacade.find(idJoueur));
                joueurFromDb.getMatchs().add(match);
                messageBuilder.append("Le joueur:"+joueurFromDb.getNom()+" : ajouté à la liste avec succès <br>");
            }
            else{
                messageBuilder.append("Echec de l'une des opérations d'ajout <br>");
            }
             
        }
        match.setJoueurs(joueurs);
        em.flush();
        em.clear();
        return messageBuilder.toString();
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
   public  List<Joueur> getJoueurs(){
       return joueurFacade.findAll();
   }
    
     
}
