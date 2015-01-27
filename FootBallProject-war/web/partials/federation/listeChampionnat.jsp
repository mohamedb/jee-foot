<%@page import="football.entities.Championnat"%>
<%@page import="java.util.List" %>
<jsp:useBean id="listeChampionnat" scope="request" class="java.util.List"></jsp:useBean>

<jsp:useBean id="utilisateur" scope="session" class="football.entities.Utilisateur"></jsp:useBean>
<jsp:include page="/partials/header.jsp" />
<% if (utilisateur.getId() == null) {%>
<jsp:include page="/partials/403.jsp" />
<% } else { %>
<jsp:include page="/partials/federation/topBar.jsp" />

<div class="row">
    <div class="col-md-12" update-fixed-headers>
        <h4>La liste des championnats</h4>
         <jsp:include page="/partials/recherche.jsp" />
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
                           class="tooltip btn btn-sm btn-default btn-outline"
                           title="Aller sur la page du championnat">Voir</a>
                        <a href="index?action=supprimerChampionnat&id=<%= championnat.getId()%>"
                           class="tooltip btn btn-sm btn-danger btn-outline"
                           title="Attention: action irréversible">Supprimer</a>
                    </td>

                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>
<% }%>
<jsp:include page="/partials/footer.jsp" />