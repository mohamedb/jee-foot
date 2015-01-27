<%@page import="java.util.List" %>
<%@page import="football.entities.Federation" %>
<jsp:useBean id="listeFederation" scope="request" class="java.util.List"></jsp:useBean>
<jsp:include page="/partials/header.jsp" />
<jsp:include page="/partials/root/rootTopBar.jsp" />


<div class="row">
    <div class="col-md-12" update-fixed-headers>
        <h4>Liste des fédérations</h4>
        <!-- Lq recherche et le filtre -->
         <jsp:include page="/partials/recherche.jsp" />
        <!--Fin rechercher/-->
        <table id="datatable_demo" data-filter="#filter"
               class=" footable table table-striped table-bordered" cellspacing="0" width="100%">
            <thead>
                <tr class="tooltip" title="cliquer pour changer l'odre de l'affichage">
                    <th>Nom</th>
                    <th>Email</th>
                    <th>Organisme</th>
                    <th>Action</th>                     
                </tr>
            </thead>
            <tbody>
                <%  List<Federation> federations = (List<Federation>) request.getAttribute("listeFederation");
                    for (Federation federation : federations) {%>
                <tr>
                    <td><%= federation.getNom()%></td>
                    <td><%= federation.getEmail()%></td>
                    <td><%= federation.getOrganisme()%></td>
                    <td>
                        <a href="index?action=supprimerFederation&id=<%= federation.getId()%>" 
                           class="tooltip btn btn-sm btn-danger btn-outline" title="Attention! action irréversible">Supprimer</a>
                    </td>

                </tr>
                <% }%>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/partials/footer.jsp" />

