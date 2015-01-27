<%@page import="football.entities.Federation"%>
<%@page import="java.util.List"%>
<jsp:useBean id="utilisateur" scope="session" class="football.entities.Utilisateur"></jsp:useBean>
<jsp:include page="/partials/header.jsp" />
<% if (utilisateur.getId() == null) {%>
<jsp:include page="/partials/403.jsp" />
<% } else { %>
<jsp:include page="/partials/root/rootTopBar.jsp" />

<div class="row">
    <div class="col-md-12" update-fixed-headers>
        <h4>Liste des fédérations</h4>

        <!-- Lq recherche et le filtre -->
        <jsp:include page="/partials/recherche.jsp" />
        <!--Fin rechercher/-->


        <table id="datatable_demo" data-filter="#filter"
               class="footable table table-striped table-bordered" 
               cellspacing="0" width="100%">
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
                    <td data-index="0"><%= federation.getNom()%></td>
                    <td><%= federation.getEmail()%></td>
                    <td><%= federation.getOrganisme()%></td>
                    <td>
                        <a href="index?action=supprimerFederation&id=<%= federation.getId()%>" 
                           class="tooltip btn btn-sm btn-danger btn-outline" title="Attention! action irréversible">Supprimer</a>
                    </td>

                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>

<% }%>
<jsp:include page="/partials/footer.jsp" />
<script type="text/javascript">
    $(function () {
        $('table').footable();

        $('.sort-column').click(function (e) {
            e.preventDefault();

            //get the footable sort object
            var footableSort = $('table').data('footable-sort');

            //get the index we are wanting to sort by
            var index = $(this).data('index');

            footableSort.doSort(index, 'toggle');
        });
    });
</script>