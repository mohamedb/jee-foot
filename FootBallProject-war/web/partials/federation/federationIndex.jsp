<%@page import="football.entities.MatchFoot"%>
<%@page import="football.entities.ContratEntraineur"%>
<%@page import="football.entities.Equipe"%>
<%@page import="football.entities.Championnat"%>
<jsp:useBean id="utilisateur" scope="session" class="football.entities.Utilisateur"></jsp:useBean>
<%@page import="java.util.List" %>
<jsp:useBean id="listeChampionnat" scope="request" class="java.util.List"></jsp:useBean>
<jsp:include page="/partials/header.jsp" />
<% if(utilisateur.getId()==null){%>
            <jsp:include page="/partials/403.jsp" />
            <% } else { %>
            <jsp:include page="/partials/federation/topBar.jsp" />
<!-- La liste des championnats  -->
<div class="row">
    
    <div class="col-md-12" update-fixed-headers>
        <jsp:include page="/partials/recherche.jsp" />
        <h4>La liste des championnats</h4>
        <table id="datatable_demo" data-filter="#filter"
               class="footable table table-striped table-bordered" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Année/Saison</th>
                    <th>Description</th>
                    <th>Action</th>                     
                </tr>
            </thead>
            <tbody>
                <%  List<Championnat> championnats = (List<Championnat>) request.getAttribute("listeChampionnat");
                    for (Championnat championnat : championnats) {%>
                <tr>
                    <td><%= championnat.getNom()%></td>
                    <td><%= championnat.getAnnee()%></td>
                    <td><%= championnat.getDescription() %></td>
                    <td>
                        <a href="index?action=voirChampionnat&id=<%= championnat.getId()%>" 
                           class="btn btn-sm btn-default btn-outline">Voir</a>
                        <a href="index?action=supprimerChampionnat&id=<%= championnat.getId()%>"
                           class="btn btn-sm btn-danger btn-outline">Supprimer</a>
                    </td>

                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
   
<!--    <h4>La liste des équipes</h4>-->
    <div class="col-md-12" update-fixed-headers>
        <h4>La liste des équipes</h4>
        <table id="datatable_demo" data-filter="#filter"
               class="footable table table-striped table-bordered" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Points</th>
                    <th>Entraineur</th>
                    <th>Action</th>                     
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
                        <b>Date nomination</b> : <%= contrat.getDateDebut() %><br>
                        <% }  %> 
                    </td> 
                    <td>
<!--                        <a href="index?action=gererEquipe&id=<%=equipe.getId()%>" class="btn btn-sm btn-info btn-outline">Gérer</a> |-->
                        <a href="index?action=supprimerEquipe&id=<%=equipe.getId()%>" class="btn btn-sm btn-danger btn-outline">Supprimer</a>                     
                    </td>

                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
            
<!--La liste des matches !-->
     <div class="col-md-12" update-fixed-headers>
        <h4>La liste des matchs</h4>
        <table id="datatable_demo" data-filter="#filter"
               class=" footable table table-striped table-bordered" cellspacing="0" width="100%">
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
                    <td><%= match.getDateDebut()%></td>
                    <td>
                        <% if (match.isIsCommence()) {%>
                        <%= match.getButVisiteur()%> <b>-</b> <%= match.getButReceveur()%>
                        <% } else { %>
                        <span class="label label-warning">En attente / En cours</span>
                        <% }%>
                    </td>
                     

                </tr>

            <% } %>
            </tbody>
        </table>
    </div>
</div>
 <% } %>
<jsp:include page="/partials/footer.jsp" />