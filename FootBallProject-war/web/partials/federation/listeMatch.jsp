<%@page import="football.entities.MatchFoot"%>
<%@page import="football.entities.Championnat"%>
<%@page import="java.util.List" %>
<jsp:useBean id="listeMatch" scope="request" class="java.util.List"></jsp:useBean>
<%java.text.DateFormat dateformatter = new java.text.SimpleDateFormat("dd-MM-yyyy"); %>
<jsp:useBean id="utilisateur" scope="session" class="football.entities.Utilisateur"></jsp:useBean>
<jsp:include page="/partials/header.jsp" />
<% if (utilisateur.getId() == null) {%>
<jsp:include page="/partials/403.jsp" />
<% } else { %>
<jsp:include page="/partials/federation/topBar.jsp" />

<div class="row">

    <jsp:include page="/partials/recherche.jsp" />
    <div class="col-sm-5">
        <b>La liste des rencontres</b>
    </div>
    <div class="col-sm-7 text-right">
        Mes actions pour cette liste:<br>
        <a href="index?action=listeMatch" 
           class="btn btn-sm btn-info btn-outline">Actualiser</a>
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
                    <th>Actions</th>
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
                    <td>
                        <% if (!match.isIsCommence()) {%>
                        <a data-toggle="modal" data-target="#editerEquipeModal_<%= match.getId()%>"
                           class=" tooltip btn btn-sm btn-default btn-outline"
                           title="Ajouter le résultat a ce match">Editer</a>
                        <% }%>


                    </td>

                </tr>

                <!-- Modal Ajout Match -->
            <div class="modal fade" id="editerEquipeModal_<%= match.getId()%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Modifier le Match</h4>
                        </div>
                        <form id="login_form" method="get" action="index" class="form-horizontal">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Buts <%= match.getVisiteur().getNom()%></label>
                                    <div class="col-sm-9">
                                        <input type="number" class="form-control" name="butVisiteur" value="0">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Buts <%= match.getRecepteur().getNom()%></label>
                                    <div class="col-sm-9">
                                        <input type="number" class="form-control" name="butRecepteur" value="0">
                                    </div>
                                </div>
                                <br><br>
                                <input type ="hidden" name="idVisiteur" value="<%= match.getVisiteur().getId()%>">
                                <input type ="hidden" name="idRecepteur" value="<%= match.getRecepteur().getId()%>">
                                <input type ="hidden" name="idMatch" value="<%= match.getId()%>">
                                <input type ="hidden" name="action" value="editerMatch">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                                <button type="submit" class="btn btn-primary">Enregistrer</button>
                            </div>
                        </form>                        
                    </div>
                </div>
            </div>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
<% }%>
<jsp:include page="/partials/footer.jsp" />