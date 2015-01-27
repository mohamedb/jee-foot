/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.servelet;

import football.entities.Entraineur;
import football.entities.Federation;
import football.entities.Utilisateur;
import gestion.GestionFederation;
import gestion.GestionUtilisateur;
import java.io.IOException;
import java.util.ArrayList;
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
public class RootAction {

    GestionFederation gestionFederation = lookupGestionFederationBean();
    GestionUtilisateur gestionUtilisateur = lookupGestionUtilisateurBean();
    MainServelet mainServelet;
    HttpServletRequest request;
    HttpServletResponse response;
    HttpSession session;
    Notification notification;

    public RootAction(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            GestionUtilisateur gestionUtilisateur, GestionFederation gestionFederation,
            MainServelet mainServelet) {
        this.request = request;
        this.response = response;
        this.session = session;
        this.gestionUtilisateur = gestionUtilisateur;
        this.gestionFederation = gestionFederation;
        this.mainServelet = mainServelet;
    }
    
    public String doLogin(){
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
            
            try {
                Utilisateur u = gestionUtilisateur.login(email, motDePasse);
                // Ajouter l'utilisateur dans la session!
                
                if (u != null) {
                    if(u instanceof Entraineur){
                       notification = new Notification(session,
                        "Vous n'avez pas un compte Root! votre compte est Entraineur", "warning");
                        return "login";  
                    }
                    session.setAttribute("utilisateur", u);
                    doListerFederation();
                    return "rootIndex";
                }
            } catch (Exception e) {
                notification = new Notification(session,
                        "Vérifier votre email et|ou votre mot de passe", "danger");
                 return  "login";
            }
            notification = new Notification(session,
                        "Vérifier votre email et | ou votre mot de passe", "danger");
            return "login";
    }
    
    public String doCreerFederation() throws ServletException, IOException {
        mainServelet.forcerAuth(request, response);
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
        String organisme = request.getParameter("organisme");

        gestionFederation.creerFederation(nom, email, motDePasse, organisme);
        notification = new Notification(session, "Fédération <u>" + nom + "</u> a été crée avec succès", "success");

        return doListerFederation();
    }

    public String doListerFederation() throws ServletException, IOException {
        mainServelet.forcerAuth(request, response);
        List<Federation> federations = new ArrayList<Federation>();
        federations = gestionFederation.getFederations();
        System.out.println("\n********* \n************** ");
        for (Federation f : federations) {
            System.out.println("\n Email! :" + f.getEmail());
        }
        request.setAttribute("listeFederation", federations);
        return "listeFederation";
    }

    public String doSupprimerFederation() throws ServletException, IOException {
        mainServelet.forcerAuth(request, response);
        String id = request.getParameter("id");
        Long idF = Long.valueOf(id);
        if (gestionUtilisateur.supprimer(idF)) {
            notification = new Notification(session, "Supression avec succès", "success");
            return doListerFederation();
        }
        return "listeFederation";
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

    private GestionFederation lookupGestionFederationBean() {
        try {
            Context c = new InitialContext();
            return (GestionFederation) c.lookup("java:global/FootBallProject/FootBallProject-ejb/GestionFederation!gestion.GestionFederation");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
