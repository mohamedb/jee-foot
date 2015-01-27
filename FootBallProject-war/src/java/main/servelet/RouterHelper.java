/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.servelet;

import java.util.HashMap;

/**
 *
 * @author Mohamed
 */
public class RouterHelper {
      HashMap<String, String> route= new HashMap<>();

    public RouterHelper() {
        this.init();
    }
      
    public final  void init(){
        route.put("index", "/index.jsp");
        /* Auth & co */
        route.put("login", "/login.jsp");
        /* -- les joueurs -- */
        route.put("listeJoueur", "/listeJoueur.jsp");
        /* -- Les utilités -- */
        route.put("succes", "/partials/succes.jsp");
        route.put("routeIntrouvale", "/routeIntrouvable.jsp");
        /* -- Les routes du championnat -- */
        route.put("championnatIndex", "indexChampionnat.jsp");
        
        /* -- Root actions -- */
        route.put("creerFederationPage", "/partials/root/creerFederation.jsp");
        route.put("rootIndex", "/rootIndex.jsp");
        route.put("listeFederation", "/partials/root/listeFederation.jsp");
        
        /* === Federation actions ==== */
        route.put("federationIndex", "/partials/federation/federationIndex.jsp");
        route.put("listeEntraineur", "/partials/federation/listeEntraineur.jsp");
        route.put("creerEntraineurPage", "/partials/federation/creerEntraineur.jsp");
        
        route.put("creerEquipePage", "/partials/federation/creerEquipe.jsp");
        route.put("listeEquipe", "/partials/federation/listeEquipe.jsp");
        route.put("affecterEntraineur", "/partials/federation/affecterEntraineur.jsp");
        
        route.put("creerChampionnatPage", "/partials/federation/creerChampionnat.jsp");
        route.put("listeChampionnat", "/partials/federation/listeChampionnat.jsp");
        route.put("voirChampionnat", "/partials/federation/voirChampionnat.jsp");
        
        route.put("listeMatch", "/partials/federation/listeMatch.jsp");
        
        /* === Entraineur actions ===== */
        route.put("loginEntraineur", "/loginEntraineur.jsp");
        route.put("entraineurIndex", "/partials/entraineur/entraineurIndex.jsp");
        route.put("creerJoueurPage", "/partials/entraineur/creerJoueur.jsp");
        route.put("listeJoueur", "/partials/entraineur/listeJoueur.jsp");
        route.put("listeMatchParEquipe", "/partials/entraineur/listeMatchParEquipe.jsp");
        
        
    }
    public   String get(String cle){
        return route.get(cle);
    }
}
