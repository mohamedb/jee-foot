<%@page import="football.entities.ContratJoueur"%>
<%@page import="football.entities.Joueur"%>
<%@page import="java.util.List" %>
<%java.text.DateFormat dateformatter = new java.text.SimpleDateFormat("dd-MM-yyyy"); %>
<jsp:useBean id="entraineur" scope="session" class="football.entities.Entraineur"></jsp:useBean>
<jsp:include page="/partials/header.jsp" />
<% if (entraineur.getId() == null) {%>
<jsp:include page="/partials/403.jsp" />
<% } else { %>
<jsp:include page="/partials/entraineur/topBar.jsp" />

<div class="row">
    
    <div class="col-md-12" update-fixed-headers>
        <h4>La liste des joueur</h4>
        <jsp:include page="/partials/recherche.jsp" />
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
                    <th>Début</th>
                    <th>Fin</th>
                    <th>Action</th>                     
                </tr>
            </thead>
            <tbody>
                <%  List<ContratJoueur> contratJoueurs = (List<ContratJoueur>) request.getAttribute("contratJoueurs");
                    for (ContratJoueur contrat : contratJoueurs) {%>
                <tr>
                    <td><%= contrat.getJoueur().getNom()%></td>
                    <td><%= contrat.getJoueur().getPrenom()%></td>
                    <td><%= contrat.getJoueur().getMaillot()%></td>
                    <td><%= contrat.getJoueur().getPosition()%></td>
                    <td><%= contrat.getJoueur().getBio()%></td>
                    <td><%= dateformatter.format(contrat.getDateDebut()) %></td>
                    <td><%= dateformatter.format(contrat.getDateFin())%></td>
                    <td>
                        <a href="index?action=annulerContrat&idContrat=<%= contrat.getId()%>" 
                           class="tooltip btn btn-sm btn-danger btn-outline"
                           title="Annuler le contrat pour ce joueur">Annuler contrat</a>
                    </td>

                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>
<% }%>
<jsp:include page="/partials/footer.jsp" />