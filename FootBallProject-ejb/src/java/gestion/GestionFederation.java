/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import football.entities.Championnat;
import football.entities.Federation;
import football.entities.MatchFoot;
import football.facades.ChampionnatFacade;
import football.facades.ContratEntraineurFacade;
import football.facades.EntraineurFacade;
import football.facades.EquipeFacade;
import football.facades.FederationFacade;
import football.facades.MatchFootFacade;

import football.facades.ParticipationFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Mohamed
 */
@Stateless
@LocalBean
public class GestionFederation {
    @EJB
    private MatchFootFacade matchFootFacade;
    
    @EJB
    private ParticipationFacade participationFacade;
    @EJB
    private ChampionnatFacade championnatFacade;
    
    @EJB
    private EquipeFacade equipeFacade;
    @EJB
    private EntraineurFacade entraineurFacade;
    @EJB
    private ContratEntraineurFacade contratEntraineurFacade;
    @EJB
    private FederationFacade federationFacade;
    
    
    
    public boolean creerFederation(String nom,String email, String motDePasse, String organisme){
        return federationFacade.creer(nom, email, motDePasse,organisme);
    }
    
    public List getFederations(){
        List<Federation> federations= federationFacade.findAll();
        return federations;
    }
    
    public String affecterEntraineur(Long idEquipe,Long idEntraineur,Date dateDebut,Date dateFin){
        
       if(!entraineurFacade.isEntraineurLibre(dateDebut, dateFin, idEntraineur)){
           return "Entraineur pas libre pour cette date";
       }
       contratEntraineurFacade.creer(equipeFacade.find(idEquipe), entraineurFacade.find(idEntraineur), dateDebut, dateFin);
       return "ok";
    }
    public boolean creerChampionnat(String nom, int annee, String description){
        return championnatFacade.creer(nom, annee, description);
    }
    public List getChampionnats(){
        List<Championnat> championnats= championnatFacade.findAll();
        return championnats;
    }
     public boolean supprimerChampionnat(Long id){
        if(championnatFacade.find(id)!=null){
            championnatFacade.remove(championnatFacade.find(id));
            return true;
        }
        return false;
    }
     public Championnat getChampionnat(Long id){
         return championnatFacade.find(id);
     }

    public String ajouterEquipeDansChampionnat(Long idChampionnat, Long idEquipe) {       
        return  participationFacade.creer(championnatFacade.find(idChampionnat),
                equipeFacade.find(idEquipe));
    }
    
    public boolean ajouterMatch(Long idChampionnat, Long idVisiteur, Long idRecepteur){
        
        return matchFootFacade.creer(championnatFacade.find(idChampionnat),
                equipeFacade.find(idVisiteur), equipeFacade.find(idRecepteur));
    }
    public List getMatchs(){
        List<MatchFoot> matchs= matchFootFacade.findAll();
        return matchs;
    }
    public String editerMatch(Long idMatch, int butVisiteur, int butRecepteur, Long idVisiteur, Long idRecepteur) {       
        return matchFootFacade.editerResultat(idMatch, butVisiteur, butRecepteur,
                equipeFacade.find(idVisiteur), equipeFacade.find(idRecepteur));
    }
 
}
