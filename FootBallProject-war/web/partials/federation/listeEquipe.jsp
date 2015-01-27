<%@page import="football.entities.ContratEntraineur"%>
<%@page import="java.util.List" %>
<%@page import="football.entities.Equipe" %>
<jsp:useBean id="listeEquipe" scope="request" class="java.util.List"></jsp:useBean>
<%java.text.DateFormat dateformatter = new java.text.SimpleDateFormat("dd-MM-yyyy"); %>
<jsp:useBean id="utilisateur" scope="session" class="football.entities.Utilisateur"></jsp:useBean>
<jsp:include page="/partials/header.jsp" />
<% if (utilisateur.getId() == null) {%>
<jsp:include page="/partials/403.jsp" />
<% } else { %>
<jsp:include page="/partials/federation/topBar.jsp" />

<div class="row">
    
    <div class="col-md-12" update-fixed-headers>
        <h4>La liste des équipes</h4>
        <jsp:include page="/partials/recherche.jsp" />
        <table id="datatable_demo" 
               data-filter="#filter"
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
                        <b>Date nomination</b> : <%= dateformatter.format(contrat.getDateDebut()) %><br>
                        <% }  %> 
                    </td> 
                    <td>
<!--                        <a href="index?action=gererEquipe&id=<%=equipe.getId()%>" class="btn btn-sm btn-info btn-outline">Gérer</a> |-->
                        <a href="index?action=supprimerEquipe&id=<%=equipe.getId()%>" 
                           title="Attention! action irréversible"
                           class=" tooltip btn btn-sm btn-danger btn-outline">Supprimer</a>                     
                    </td>

                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>
<% }%>
<jsp:include page="/partials/footer.jsp" />