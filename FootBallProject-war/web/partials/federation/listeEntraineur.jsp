<%@page import="java.util.List" %>
<%@page import="football.entities.Entraineur" %>
<jsp:useBean id="listeEntraineur" scope="request" class="java.util.List"></jsp:useBean>

<jsp:useBean id="utilisateur" scope="session" class="football.entities.Utilisateur"></jsp:useBean>
<jsp:include page="/partials/header.jsp" />
<% if (utilisateur.getId() == null) {%>
<jsp:include page="/partials/403.jsp" />
<% } else { %>
<jsp:include page="/partials/federation/topBar.jsp" />

<div class="row">
    
    <div class="col-md-12" update-fixed-headers>
        <h4>La liste des entraineurs</h4>
         <jsp:include page="/partials/recherche.jsp" />
        <table id="datatable_demo" data-filter="#filter"
               class="footable table table-striped table-bordered" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Email</th>
                    <th>Bio</th>
                    <th>Action</th>                     
                </tr>
            </thead>
            <tbody>
                <%  List<Entraineur> entraineurs = (List<Entraineur>) request.getAttribute("listeEntraineur");
                    for (Entraineur entraineur : entraineurs) {%>
                <tr>
                    <td><%= entraineur.getNom()%></td>
                    <td><%= entraineur.getEmail()%></td>
                    <td><%= entraineur.getBio()%></td>
                    <td>
                        <a href="index?action=supprimerEntraineur&id=<%= entraineur.getId()%>"
                           class="tooltip btn btn-sm btn-danger btn-outline" title="Attention! Action irréversible">
                            Supprimer</a>
                    </td>

                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>
<% }%>
<jsp:include page="/partials/footer.jsp" />