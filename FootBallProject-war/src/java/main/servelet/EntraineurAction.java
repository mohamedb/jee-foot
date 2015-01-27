/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.servelet;

import football.entities.ContratEntraineur;
import football.entities.ContratJoueur;
import football.entities.Entraineur;
import football.entities.Equipe;
import football.entities.MatchFoot;
import gestion.GestionEntraineur;
import gestion.GestionEquipe;
import gestion.GestionFederation;
import gestion.GestionUtilisateur;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mohamed
 */
public class EntraineurAction {

    GestionEquipe gestionEquipe = lookupGestionEquipeBean();

    GestionEntraineur gestionEntraineur = lookupGestionEntraineurBean();
    GestionFederation gestionFederation = lookupGestionFederationBean();
    GestionUtilisateur gestionUtilisateur = lookupGestionUtilisateurBean();

    MainServelet mainServelet;
    HttpServletRequest request;
    HttpServletResponse response;
    HttpSession session;
    Notification notification;

    /**
     * Injection de toutes les dépendences ....
     *
     * @param request
     * @param response
     * @param session
     * @param gestionUtilisateur
     * @param gestionFederation
     * @param mainServelet
     */
    public EntraineurAction(
            HttpServletRequest request, HttpServletResponse response, HttpSession session,
            GestionUtilisateur gestionUtilisateur, GestionFederation gestionFederation,
            MainServelet mainServelet) {
        this.request = request;
        this.response = response;
        this.session = session;
        this.gestionUtilisateur = gestionUtilisateur;
        this.gestionFederation = gestionFederation;
        this.mainServelet = mainServelet;
    }

    public String doLogin() {
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
        try {
            Entraineur entraineur = gestionEntraineur.login(email, motDePasse);

            // Ajouter l'utilisateur dans la session!
            if (entraineur != null) {
                session.setAttribute("entraineur", entraineur);
                /**
                 * Recupérer la derniere equipe pour cette entraineur et la
                 * mettre dans la session! *
                 */
                List<ContratEntraineur> contrats = (List<ContratEntraineur>) entraineur.getContratEntraineurs();
                if (contrats != null && !contrats.isEmpty()) {
                    System.out.print("\n ****\n" + contrats.get(contrats.size() - 1).getEquipe().getNom());
                    Equipe equipe = contrats.get(contrats.size() - 1).getEquipe();
                    /**
                     * Attention: ces instantiations son necessaires pour que la magie du LAZY Loading
                     * fonction correctement puisque on parcour le Graph d'objet a partir de Equipe 
                     * recuperée de la session !
                     */
                    equipe.getMatchsExterieur();equipe.getContratJoueurs();equipe.getMatchsInterieur();
                    session.setAttribute("equipe", equipe);
                }
                doListerJoueur();
                doListerMatchParEquipe();
                return "entraineurIndex";
            }
        } catch (Exception e) {
            notification = new Notification(session,
                    "Vérifier votre email et|ou votre mot de passe", "danger");
            return "loginEntraineur";
        }
        notification = new Notification(session,
                "Vérifier votre email et | ou votre mot de passe", "danger");
        return "loginEntraineur";
    }

    public String doCreerJoueur() throws ServletException, IOException {
        mainServelet.forcerAuthEntraineur(request, response);
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String position = request.getParameter("position");
        String bio = request.getParameter("bio");
        int maillot = Integer.valueOf(request.getParameter("maillot"));

        int salaire = Integer.valueOf(request.getParameter("salaire"));
        Equipe equipe = (Equipe) session.getAttribute("equipe");
        String dateDebut = request.getParameter("dateDebut");
        String dateFin = request.getParameter("dateFin");
        Date fDateDebut, fDateFin;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            fDateDebut = sdf.parse(dateDebut);
            fDateFin = sdf.parse(dateFin);

        } catch (ParseException ex) {
            notification = new Notification(session, "Echec d'opération: Merci de vérifier format des dates! <b>" + "</b>", "danger");
            return "creerJoueurPage";
        }
        if (fDateDebut.after(fDateFin)) {
            notification = new Notification(session,
                    "La date de fin doit être superieure a la date début", "danger");
            return "creerJoueurPage";
        }
        notification = new Notification(session,
                gestionEquipe.creerJoueur(nom, prenom, maillot, position, bio, equipe.getId(), fDateDebut, fDateFin, salaire), "info");
        return doListerJoueur();
    }
    public String doListerJoueur() throws ServletException, IOException {
        mainServelet.forcerAuthEntraineur(request, response);
        updateSessionPourEquipe();
        List<ContratJoueur> contratJoueurs = new ArrayList<ContratJoueur>();
        Equipe equipe = (Equipe) session.getAttribute("equipe");
        contratJoueurs = (List<ContratJoueur>)equipe.getContratJoueurs();
        contratJoueurs.size();
        request.setAttribute("contratJoueurs", contratJoueurs);
        return "listeJoueur";
    }
    public String doListerMatchParEquipe() throws ServletException, IOException {
        mainServelet.forcerAuthEntraineur(request, response);
        updateSessionPourEquipe();
        List<MatchFoot> matchs = new ArrayList<MatchFoot>();
        Equipe equipe = (Equipe) session.getAttribute("equipe");
        matchs = gestionEquipe.getMatchs(equipe);
        request.setAttribute("listeMatchParEquipe", matchs);
        /* Lister les joueur pour le Modal afin de les ajouter aux matchs ! */
        doListerJoueur();
        return "listeMatchParEquipe";
    }
    
    public String doAjouterJoueurDansMatch() throws ServletException, IOException {
        mainServelet.forcerAuthEntraineur(request, response);
        String [] idsTitulaires = request.getParameterValues("titulaires");
        StringBuilder builder = new StringBuilder();
        for(String s : idsTitulaires) {
            builder.append(s);
            System.out.println(" \n ========= les ids des titulaires: "+s);
        }
         
        Long idMatch = Long.valueOf(request.getParameter("idMatch"));
        Equipe equipe = (Equipe) session.getAttribute("equipe");
        //String [] idsTitulaires = titulaires.split(",");
        if(idsTitulaires.length<=1){
            notification = new Notification(session,"Vous devez séléctionner quelques joueurs !", "danger");
            return doListerMatchParEquipe();
        }
        Long[] ids = new Long[idsTitulaires.length];
        for(int i=0; i<idsTitulaires.length; i++){
            ids[i]= Long.valueOf(idsTitulaires[i]);
        }
        String reponseServer=gestionEquipe.creerListeJoueurPourMatch(equipe,idMatch, ids);
        System.out.println(" \n ******************* \n ****** Reponse du serveur: "+reponseServer);
        notification = new Notification(session,reponseServer, "info");
        return doListerMatchParEquipe();
        }
    
     /* Cette methode c est essentienllement pour updater la session ! */
    public void updateSessionPourEquipe(){
        Equipe equipeFromSession = (Equipe) session.getAttribute("equipe");
        Equipe equipe =gestionEquipe.getEquipe(equipeFromSession);
        /* Faire fonctionner le LAZY loading ! */
        equipe.getMatchsExterieur();equipe.getContratJoueurs();equipe.getMatchsInterieur();
        
        session.setAttribute("equipe", equipe);
    }
    /* ================ Generation automatique des LookUp ============= */
    private GestionFederation lookupGestionFederationBean() {
        try {
            Context c = new InitialContext();
            return (GestionFederation) c.lookup("java:global/FootBallProject/FootBallProject-ejb/GestionFederation!gestion.GestionFederation");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private GestionUtilisateur lookupGestionUtilisateurBean() {
        try {
            Context c = new InitialContext();
            return (GestionUtilisateur) c.lookup("java:global/FootBallProject/FootBallProject-ejb/GestionUtilisateur!gestion.GestionUtilisateur");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private GestionEntraineur lookupGestionEntraineurBean() {
        try {
            Context c = new InitialContext();
            return (GestionEntraineur) c.lookup("java:global/FootBallProject/FootBallProject-ejb/GestionEntraineur!gestion.GestionEntraineur");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private GestionEquipe lookupGestionEquipeBean() {
        try {
            Context c = new InitialContext();
            return (GestionEquipe) c.lookup("java:global/FootBallProject/FootBallProject-ejb/GestionEquipe!gestion.GestionEquipe");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
