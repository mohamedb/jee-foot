/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import football.entities.Entraineur;
import football.facades.EntraineurFacade;
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
public class GestionEntraineur {
    @EJB
    private EntraineurFacade entraineurFacade;
    
    public Entraineur login(String email, String motDePasse){
        return entraineurFacade.login(email, motDePasse);
    }
     public boolean creerEntraineur(String nom,String email, String motDePasse, String bio){
        return entraineurFacade.creer(nom, email, motDePasse,bio);
    }

    public List<Entraineur> getEntraineurs() {
        List<Entraineur> entraineurs= entraineurFacade.findAll();
       return entraineurs;
    }
     public boolean supprimer(Long id){
        if(entraineurFacade.find(id)!=null){
            entraineurFacade.remove(entraineurFacade.find(id));
            return true;
        }
        return false;   
    }
}
