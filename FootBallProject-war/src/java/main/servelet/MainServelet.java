/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.servelet;

import football.entities.Entraineur;
import football.entities.Equipe;
import football.entities.Joueur;
import football.entities.Utilisateur;
import gestion.GestionEntraineur;
import gestion.GestionEquipe;
import gestion.GestionFederation;
import gestion.GestionUtilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cheichnah
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/index"})
public class MainServelet extends HttpServlet {
    @EJB
    private GestionEquipe gestionEquipe;
     
    @EJB
    private GestionFederation gestionFederation;
    @EJB
    private GestionEntraineur gestionEntraineur;
    @EJB
    private GestionUtilisateur gestionUtilisateur;


    /**
     * la route de la page auquelle l'utilisateur doit etre redirigé par
     * default: index , voir class RouteHelper pour la liste complete
     */
    String pageRoute = "index";
    RequestDispatcher requestDispatcher;
    RouterHelper router = new RouterHelper();
    HttpSession session;

    /**
     * Les subs Classes pour organiser un peu le code
     */
    RootAction rootAction;
    FederationAction federationAction;
    EntraineurAction entraineurAction;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        session = request.getSession(true);
        String action = request.getParameter("action");
        
        rootAction = new RootAction(request, response, session, gestionUtilisateur, gestionFederation, this);
        federationAction = new FederationAction(request, response, session, gestionUtilisateur, gestionFederation, this);
        entraineurAction= new EntraineurAction(request, response, session, gestionUtilisateur, gestionFederation, this);
        /* Pour un acces dans les JSP pour d eventuelle redirection ! */
        request.setAttribute("router", router);
        
        if (action != null && action.equals("creerUtilisateur")) {

            String nom = request.getParameter("nom");
            String email = request.getParameter("email");
            String motDePasse = request.getParameter("motDePasse");
            gestionUtilisateur.creerUtilisateur(nom, email, motDePasse);
            pageRoute = "login";
        }

        else if (action != null && action.equals("login")) {
            pageRoute= rootAction.doLogin();
        }
        else if (action != null && action.equals("rootIndex")) {
            rootAction.doListerFederation();
            pageRoute = "rootIndex";
        }
        else if (action != null && action.equals("seDeconnecter")) {
                    session.setAttribute("utilisateur", null);
                    pageRoute = "login";
        }
        /**
         * *********************************************************************
         * Federation actions
         * *********************************************************************
         */
        else if (action != null && action.equals("federationIndex")) {
            forcerAuth(request, response);
            federationAction.doListerChampionnat();
            federationAction.doListerEquipe();
            federationAction.doListerMatch();
            pageRoute = "federationIndex";

        }
        
        else if (action != null && action.equals("creerEntraineurPage")) {
            forcerAuth(request, response);
            pageRoute = "creerEntraineurPage";
        }
        
        else if (action != null && action.equals("creerEntraineur")) {
            forcerAuth(request, response);
            pageRoute = federationAction.doCreerEntraineur();
        }
        else if (action != null && action.equals("listeEntraineur")) {
            forcerAuth(request, response);
            pageRoute = federationAction.doListerEntraineur();
        }
        else if (action != null && action.equals("supprimerEntraineur")) { 
            forcerAuth(request, response);
            pageRoute = federationAction.doSupprimerEntraineur();
        }
        /* -- Equipe --- */
        else if (action != null && action.equals("creerEquipePage")) {  
            forcerAuth(request, response);
            pageRoute = "creerEquipePage";
        }  
        else if (action != null && action.equals("creerEquipe")) { 
            forcerAuth(request, response);
            pageRoute = federationAction.doCreerEquipe();
        }
        else if (action != null && action.equals("listeEquipe")) { 
            forcerAuth(request, response);
            pageRoute = federationAction.doListerEquipe();
        }
        else if (action != null && action.equals("supprimerEquipe")) { 
            forcerAuth(request, response);
            pageRoute = federationAction.doSupprimerEquipe();
        }
        else if (action != null && action.equals("affecterEntraineurPage")) {
            forcerAuth(request, response);
            /* Necessaire pour avoir la liste des equipes! etsatisfaire les dependances  */
            pageRoute=federationAction.doRedirigerAffectationEntraineurPage();
        }
        else if (action != null && action.equals("affecterEntraineur")) {
            forcerAuth(request, response);
            pageRoute =  federationAction.doAffecterEntraineur();
        }
        /* -- Championnat --- */
        else if (action != null && action.equals("creerChampionnatPage")) { 
            forcerAuth(request, response);
            pageRoute = "creerChampionnatPage";
        }
        else if (action != null && action.equals("creerChampionnat")) {   
            forcerAuth(request, response);
            pageRoute = federationAction.doCreerChampionnat();
        }
        else if (action != null && action.equals("listeChampionnat")) {  
            forcerAuth(request, response);
            pageRoute = federationAction.doListerChampionnat();
        }
        else if (action != null && action.equals("supprimerChampionnat")) { 
            forcerAuth(request, response);
            pageRoute = federationAction.doSupprimerChampionnat();
        }
        else if (action != null && action.equals("voirChampionnat")) {
            /* Necessaire pour le retour après fin du Modal! */
            Long idChampionnat= (long) 0;
            pageRoute = federationAction.doVoirChampionnat(idChampionnat);
        }
        else if (action != null && action.equals("ajouterEquipeDansChampionnat")) {   
            pageRoute = federationAction.doAjouterEquipeDansChampionnat();
        }
        /* --- Match --- */
        else if (action != null && action.equals("ajouterMatch")) {   
            pageRoute = federationAction.doAjouterMatch();
        }
        else if (action != null && action.equals("listeMatch")) {   
            pageRoute = federationAction.doListerMatch();
        }
        else if (action != null && action.equals("editerMatch")) {   
            pageRoute = federationAction.doEditerMatch();
        }
        /**
         * *********************************************************************
         * Root actions
         * *********************************************************************
         */
        else if (action != null && action.equals("creerFederationPage")) {
            pageRoute = "creerFederationPage";
        }
        else if (action != null && action.equals("creerFederation")) {
            pageRoute = rootAction.doCreerFederation();
        }
        else if (action != null && action.equals("listeFederation")) {   
            pageRoute = rootAction.doListerFederation();
        }
        else if (action != null && action.equals("supprimerFederation")) {
            pageRoute = rootAction.doSupprimerFederation();
        }
        
        /**
         * ********************************************************************
         * Entraineur Action !
         * *******************************************************************
         */
        else if (action != null && action.equals("loginEntraineur")) {
            pageRoute= entraineurAction.doLogin();
        }
        else if (action != null && action.equals("creerJoueurPage")) {
            pageRoute= "creerJoueurPage";
        }
        else if (action != null && action.equals("creerJoueur")) {
            pageRoute= entraineurAction.doCreerJoueur();
        }
        else if (action != null && action.equals("listeJoueur")) {   
            pageRoute = entraineurAction.doListerJoueur();
        }       
        else if (action != null && action.equals("listeMatchParEquipe")) {   
            pageRoute = entraineurAction.doListerMatchParEquipe();
        }
        else if (action != null && action.equals("ajouterJoueurDansMatch")) {   
            pageRoute = entraineurAction.doAjouterJoueurDansMatch();
        }
        else if (action != null && action.equals("entraineurIndex")) {   
             pageRoute = entraineurAction.doListerMatchParEquipe();
        }
        
        else {
            federationAction.doListerChampionnat();
            federationAction.doListerMatch();
            federationAction.doListerEquipe();
            List<Joueur> joueurs = new ArrayList<>();
            joueurs = gestionEquipe.getJoueurs();
            
            request.setAttribute("listeJoueur", joueurs);
            pageRoute = "index";
        }

        requestDispatcher = getServletContext().getRequestDispatcher(router.get(pageRoute));
        requestDispatcher.forward(request, response);
        PrintWriter out = response.getWriter();

    }

    /**
     * L'idée c'est de mettre cette methode au debut de chaque page qui requit
     * l'auth! puis Rediriger l'utilisateur a la page login
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void forcerAuth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession(true);
        Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
        if (u == null || u.getId()==null) {
            pageRoute = "login";
            requestDispatcher = getServletContext().getRequestDispatcher(router.get(pageRoute));
            requestDispatcher.forward(request, response);
        }
    }
    /**
     * Forcer l'auth des Entraineurs!
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public void forcerAuthEntraineur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession(true);
        Entraineur e = (Entraineur) session.getAttribute("entraineur");
        if (e == null || e.getId()==null) {
            pageRoute = "loginEntraineur";
            requestDispatcher = getServletContext().getRequestDispatcher(router.get(pageRoute));
            requestDispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(MainServelet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(MainServelet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
