/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import football.entities.Utilisateur;
import football.facades.UtilisateurFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Mohamed
 */
@Stateless
@LocalBean
public class GestionUtilisateur {
    @EJB
    private UtilisateurFacade utilisateurFacade;
     
    public boolean creerUtilisateur(String nom,String email, String motDePasse){
        return utilisateurFacade.creer(nom, email, motDePasse);
    }
    public Utilisateur login(String email, String motDePasse){
        return utilisateurFacade.login(email, motDePasse);
    }
    public boolean supprimer(Long id){
        if(utilisateurFacade.find(id)!=null){
            utilisateurFacade.remove(utilisateurFacade.find(id));
            return true;
        }
        return false;   
    }
}
