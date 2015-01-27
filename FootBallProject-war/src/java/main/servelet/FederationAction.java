/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.servelet;

import football.entities.Championnat;
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
import java.util.Objects;
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
public class FederationAction {

    GestionEquipe gestionEquipe = lookupGestionEquipeBean();

    GestionEntraineur gestionEntraineur = lookupGestionEntraineurBean();
    GestionFederation gestionFederation = lookupGestionFederationBean();
    GestionUtilisateur gestionUtilisateur = lookupGestionUtilisateurBean();

    MainServelet mainServelet;
    HttpServletRequest request;
    HttpServletResponse response;
    HttpSession session;
    Notification notification;

    public FederationAction(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            GestionUtilisateur gestionUtilisateur, GestionFederation gestionFederation,
            MainServelet mainServelet) {
        this.request = request;
        this.response = response;
        this.session = session;
        this.gestionUtilisateur = gestionUtilisateur;
        this.gestionFederation = gestionFederation;
        this.mainServelet = mainServelet;
    }
    /* 
     ################### La Actions sur entraineurs ###########################
     */

    public String doCreerEntraineur() throws ServletException, IOException {
        mainServelet.forcerAuth(request, response);
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
        String bio = request.getParameter("bio");

        gestionEntraineur.creerEntraineur(nom, email, motDePasse, bio);
        notification = new Notification(session, "Compte Entraineur: <b>" + nom + "</b> Créee avec succès", "success");

        return doListerEntraineur();
    }

    public String doListerEntraineur() throws ServletException, IOException {
        mainServelet.forcerAuth(request, response);
        List<Entraineur> entraineur = new ArrayList<Entraineur>();
        entraineur = gestionEntraineur.getEntraineurs();
        System.out.println("\n********* \n************** ");
        for (Entraineur f : entraineur) {
            System.out.println("\n Email! :" + f.getEmail());
        }
        request.setAttribute("listeEntraineur", entraineur);
        return "listeEntraineur";
    }

    public String doSupprimerEntraineur() throws ServletException, IOException {
        mainServelet.forcerAuth(request, response);
        String id = request.getParameter("id");
        Long idF = Long.valueOf(id);
        if (gestionEntraineur.supprimer(idF)) {
            notification = new Notification(session, "Suppression avec succès", "success");
            return doListerEntraineur();
        } else {
            notification = new Notification(session, "Erreur lors de la suppression", "danger");
        }
        return "listeFederation";
    }

    /**
     * ##################### Equipes actions ###############################
     */
    /**
     *
     * @return @throws ServletException
     * @throws IOException
     */
    public String doCreerEquipe() throws ServletException, IOException {
        mainServelet.forcerAuth(request, response);
        String nom = request.getParameter("nom");
        int points = Integer.valueOf(request.getParameter("points"));

        gestionEquipe.creerEquipe(nom, points);
        notification = new Notification(session, "Equipe: <b>" + nom + "</b> Ajoutée avec succès", "success");

        return doListerEquipe();
    }

    public String doListerEquipe() throws ServletException, IOException {
       // mainServelet.forcerAuth(request, response);
        List<Equipe> equipes = new ArrayList<Equipe>();
        equipes = gestionEquipe.getEquipes();
        request.setAttribute("listeEquipe", equipes);
        return "listeEquipe";
    }

    public String doSupprimerEquipe() throws ServletException, IOException {
        mainServelet.forcerAuth(request, response);
        String id = request.getParameter("id");
        Long idF = Long.valueOf(id);
        if (gestionEquipe.supprimer(idF)) {
            notification = new Notification(session, "Suppression avec succès", "success");
            return doListerEquipe();
        } else {
            notification = new Notification(session, "Erreur lors de la suppression", "danger");
        }
        return doListerEquipe();
    }

    public String doAffecterEntraineur() throws ServletException, IOException, ParseException {
        mainServelet.forcerAuth(request, response);
        String equipe = request.getParameter("equipes");
        Long idEquipe = Long.valueOf(equipe);
        String entraineur = request.getParameter("entraineurs");
        Long idEntraineur = Long.valueOf(entraineur);
        String dateDebut = request.getParameter("dateDebut");
        String dateFin = request.getParameter("dateFin");
        Date fDateDebut, fDateFin;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            fDateDebut = sdf.parse(dateDebut);
            fDateFin = sdf.parse(dateFin);

        } catch (ParseException ex) {
            notification = new Notification(session, "Echec d'opération d'Affection: Merci de vérifier format des dates! <b>" + "</b>", "danger");
            return doRedirigerAffectationEntraineurPage();
        }
        if (fDateDebut.after(fDateFin)) {
            notification = new Notification(session,
                    "La date de fin doit être superieur a la date début", "danger");
            return doRedirigerAffectationEntraineurPage();
        }
        String resulat = gestionFederation.affecterEntraineur(idEquipe, idEntraineur, fDateDebut, fDateFin);
        if (resulat.equals("ok")) {
            notification = new Notification(session, "Affection: <b>" + "</b> a été éffectuée avec succès", "success");
            return doListerEquipe();
        }
        notification = new Notification(session, resulat, "danger");
        return doRedirigerAffectationEntraineurPage();
    }

    public String doRedirigerAffectationEntraineurPage() throws ServletException, IOException {
        doListerEquipe();
        doListerEntraineur();
        return "affecterEntraineur";
    }

    /**
     * ##################### Championnat actions ###############################
     */
    /**
     *
     * @return @throws ServletException
     * @throws IOException
     */
    public String doCreerChampionnat() throws ServletException, IOException {
        mainServelet.forcerAuth(request, response);
        String nom = request.getParameter("nom");
        int annee = Integer.valueOf(request.getParameter("annee"));
        String description = request.getParameter("description");

        gestionFederation.creerChampionnat(nom, annee, description);
        notification = new Notification(session, "Championnat: <b>" + nom + "</b> Ajoutée avec succès", "success");

        return doListerChampionnat();
    }

    public String doListerChampionnat() throws ServletException, IOException {
        // mainServelet.forcerAuth(request, response);
        List<Championnat> championnats = new ArrayList<Championnat>();
        championnats = gestionFederation.getChampionnats();
        request.setAttribute("listeChampionnat", championnats);
        return "listeChampionnat";
    }

    public String doVoirChampionnat(Long idChampionnat) throws ServletException, IOException {
        mainServelet.forcerAuth(request, response);
        if (idChampionnat == 0) {
            String id = request.getParameter("id");
            idChampionnat = Long.valueOf(id);
        }

        Championnat championnat = gestionFederation.getChampionnat(idChampionnat);
        request.setAttribute("championnat", championnat);
        /* Ajouter liste des equipes pour le modal! */
        doListerEquipe();
        return "voirChampionnat";
    }

    public String doSupprimerChampionnat() throws ServletException, IOException {
        mainServelet.forcerAuth(request, response);
        String id = request.getParameter("id");
        Long idF = Long.valueOf(id);
        if (gestionFederation.supprimerChampionnat(idF)) {
            notification = new Notification(session, "Suppression avec succès", "success");

        } else {
            notification = new Notification(session, "Erreur lors de la suppression", "danger");
        }
        return doListerChampionnat();
    }

    public String doAjouterEquipeDansChampionnat() throws ServletException, IOException {
        mainServelet.forcerAuth(request, response);
        String equipe = request.getParameter("equipes");
        Long idEquipe = Long.valueOf(equipe);
        Long idChampionnat = Long.valueOf(request.getParameter("idChampionnat"));
        notification = new Notification(session,
                gestionFederation.ajouterEquipeDansChampionnat(idChampionnat, idEquipe), "info");

        return doVoirChampionnat(idChampionnat);
    }
    /***
     *  ============== Match  ============= ! 
     */
    public String doAjouterMatch() throws ServletException, IOException {
        mainServelet.forcerAuth(request, response);
        Long idVisiteur = Long.valueOf(request.getParameter("visiteurs"));
        Long idRecepteur = Long.valueOf(request.getParameter("recepteurs")); 
        Long idChampionnat = Long.valueOf(request.getParameter("idChampionnat"));
        if(!Objects.equals(idVisiteur, idRecepteur)){
           gestionFederation.ajouterMatch(idChampionnat, idVisiteur, idRecepteur);
           notification = new Notification(session,"Match ajouté avec succès", "success"); 
        }
        else{
           notification = new Notification(session,"Une équipe ne peut pas jouer contre elle même !", "danger");  
        }

        return doVoirChampionnat(idChampionnat);
    }
    
    public String doListerMatch() throws ServletException, IOException {
       // mainServelet.forcerAuth(request, response);
        List<MatchFoot> matchs = new ArrayList<MatchFoot>();
        matchs = gestionFederation.getMatchs();
        request.setAttribute("listeMatch", matchs);
        return "listeMatch";
    }
    
    public String doEditerMatch() throws ServletException, IOException {
        mainServelet.forcerAuth(request, response);
        int butVisiteur =  Integer.valueOf(request.getParameter("butVisiteur"));
        int butRecepteur = Integer.valueOf(request.getParameter("butRecepteur")); 
        Long idMatch = Long.valueOf(request.getParameter("idMatch"));
        
        Long idVisiteur = Long.valueOf(request.getParameter("idVisiteur"));
        Long idRecepteur = Long.valueOf(request.getParameter("idRecepteur")); 
        gestionFederation.editerMatch(idMatch, butVisiteur, butRecepteur,idVisiteur,idRecepteur);
        notification = new Notification(session,"Match modifié avec succès", "success"); 
        return doListerMatch();
    }
    
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
