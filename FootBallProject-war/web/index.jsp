<%@page import="football.entities.ContratJoueur"%>
<%@page import="football.entities.Joueur"%>
<%@page import="football.entities.Championnat"%>
<%@page import="java.util.List" %>
<%@page import="football.entities.MatchFoot"%>
<%@page import="football.entities.Championnat"%>
<%@page import="football.entities.ContratEntraineur"%> 
<%@page import="football.entities.Equipe" %>
 
<%java.text.DateFormat dateformatter = new java.text.SimpleDateFormat("dd-MM-yyyy"); %>
<jsp:include page="/partials/header.jsp" />
<jsp:include page="/partials/topBar.jsp" />

<div class="row">
    <div class="col-md-12" update-fixed-headers>
        
         <jsp:include page="/partials/recherche.jsp" />
         <b>La liste des championnats</b>
        <table id="datatable_demo" data-filter="#filter"
               class="footable table table-striped table-bordered" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Année/Saison</th>
                    <th>Description</th>
                                       
                </tr>
            </thead>
            <tbody>
                <%  List<Championnat> championnats = (List<Championnat>) request.getAttribute("listeChampionnat");
                    for (Championnat championnat : championnats) {%>
                <tr>
                    <td><%= championnat.getNom()%></td>
                    <td><%= championnat.getAnnee()%></td>
                    <td><%= championnat.getDescription() %></td>
<!--                    <td>
                        <a href="index?action=voirChampionnat&id=<%= championnat.getId()%>" 
                           class="tooltip btn btn-sm btn-default btn-outline"
                           title="Aller sur la page du championnat">Voir</a>
                        <a href="index?action=supprimerChampionnat&id=<%= championnat.getId()%>"
                           class="tooltip btn btn-sm btn-danger btn-outline"
                           title="Attention: action irréversible">Supprimer</a>
                    </td>-->

                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>

<div class="row">

     
    <div class="col-sm-5">
        <b>La liste des rencontres</b>
    </div>
     
    <div class="col-md-12" update-fixed-headers>     
        <table id="datatable_demo" data-filter="#filter"
               class="footable table table-striped table-bordered" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Championnat</th>
                    <th>Année/Saison</th>
                    <th>Visiteur</th>
                    <th>Recepteur</th>
                    <th>Date</th>
                    <th>Resultat</th>
                    
                </tr>
            </thead>
            <tbody>
                <%  List<MatchFoot> matchs = (List<MatchFoot>) request.getAttribute("listeMatch");
                    for (MatchFoot match : matchs) {%>
                <tr>
                    <td><%= match.getChampionnat().getNom()%></td>
                    <td><%= match.getChampionnat().getAnnee()%></td>
                    <td><%= match.getVisiteur().getNom()%></td>
                    <td><%= match.getRecepteur().getNom()%></td>
                    <td><%= dateformatter.format(match.getDateDebut())%></td>
                    <td>
                        <% if (match.isIsCommence()) {%>
                        <%= match.getButVisiteur()%> <b>-</b> <%= match.getButReceveur()%>
                        <% } else { %>
                        <span class="label label-warning">En attente / En cours</span>
                        <% }%>
                    </td>
                   

                </tr>

                <!-- Modal Ajout Match -->
             
            <% } %>
            </tbody>
        </table>
    </div>
</div>
 
            <div class="row">
    
    <div class="col-md-12" update-fixed-headers>
        <b>La liste des équipes</b>
        <code>Cliquer sur 'Points' pour avoir le classement</code>
        <table id="datatable_demo" 
               data-filter="#filter"
               class="footable table table-striped table-bordered" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Points</th>
                    <th>Entraineur</th>
                                        
                </tr>
            </thead>
            <tbody>
                <%  List<Equipe> equipes = (List<Equipe>) request.getAttribute("listeEquipe");
                    for (Equipe equipe : equipes) {%>
                <tr>
                    <td><%= equipe.getNom()%></td>
                    <td><%= equipe.getPoints()%></td>
                    <td><% List<ContratEntraineur> contrats = (List<ContratEntraineur>)equipe.getContratEntraineurs();%>
                        <% if(!contrats.isEmpty()) {
                          ContratEntraineur contrat =contrats.get(contrats.size()-1); %>
                        <%= contrat.getEntraineur().getNom() %> /
                        <b>Date nomination</b> : <%= dateformatter.format(contrat.getDateDebut()) %><br>
                        <% }  %> 
                    </td> 
                     

                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>
            

 <div class="row">
    
    <div class="col-md-12" update-fixed-headers>
        <b>La liste des joueur</b>
         
        <table id="datatable_demo" 
               data-filter="#filter"
               class="footable table table-striped table-bordered" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Prenom</th>
                    <th>Maillot</th>
                    <th>Position </th>
                    <th>Bio</th>
                    <th>Equipe actuelle</th> 
                    <th>Nombre de matchs</th>
                </tr>
            </thead>
            <tbody>
                <%  List<Joueur> joueurs= (List<Joueur>) request.getAttribute("listeJoueur");
                    for (Joueur joueur : joueurs) {%>
                <tr>
                    <td><%= joueur.getNom()%></td>
                    <td><%= joueur.getPrenom()%></td>
                    <td><%= joueur.getMaillot()%></td>
                    <td><%= joueur.getPosition()%></td>
                    <td><%= joueur.getBio()%></td>
                    <% List<ContratJoueur> contratJoueur = (List<ContratJoueur>)joueur.getContratJoueurs();
                       List<MatchFoot> matchsParJoueur = (List<MatchFoot>) joueur.getMatchs(); %>
                       <td> 
                           <% if(contratJoueur!=null && !contratJoueur.isEmpty()) { %>
                           <%= contratJoueur.get(contratJoueur.size()-1).getEquipe().getNom() %>
                           <% } else { %>
                           <b>Aucune !</b><i>Bonne chance</i>
                            <% }  %>
                       </td>
                       <td>
                           <% int nombreMatch=0;
                           for(MatchFoot m:matchs){
                               for(Joueur j:m.getJoueurs()){
                                   if(joueur.getId()==j.getId())
                                       nombreMatch++;
                               }
                           }
                               %>
                               <b><%= nombreMatch %></b>  
                       </td>

                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>           

<jsp:include page="/partials/footer.jsp" />