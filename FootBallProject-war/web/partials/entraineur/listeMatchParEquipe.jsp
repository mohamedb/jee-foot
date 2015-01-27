<%@page import="football.entities.Joueur"%>
<%@page import="football.entities.ContratJoueur"%>
<%@page import="football.entities.MatchFoot"%>
<%@page import="football.entities.Championnat"%>
<%@page import="java.util.List" %>
<%java.text.DateFormat dateformatter = new java.text.SimpleDateFormat("dd-MM-yyyy"); %>
<%List<ContratJoueur> contratJoueurs = (List<ContratJoueur>) request.getAttribute("contratJoueurs"); %>
<jsp:include page="/partials/header.jsp" />

<jsp:include page="/partials/entraineur/topBar.jsp" />

<div class="row">
    <div class="col-sm-5">
        <b>La liste des rencontres</b>
    </div>
    <div class="col-sm-7 text-right">
        Mes actions pour cette liste:<br>
        <a href="index?action=listeMatchParEquipe" 
           class="tooltip btn btn-sm btn-info btn-outline"
           title="Mettre à jour la liste">Actualiser</a>
    </div>

    <div class="col-md-12" update-fixed-headers>  
        <jsp:include page="/partials/recherche.jsp" />
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
                <%  List<MatchFoot> matchs = (List<MatchFoot>) request.getAttribute("listeMatchParEquipe");
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
                           class="tooltip btn btn-sm btn-default btn-outline"
                           title="Créer la liste des titulaires pour cette rencontre">Créer liste joueurs
                        </a>
                        <% }%>
                        <a data-toggle="modal" data-target="#listeJoueurModal_<%= match.getId()%>"
                           class="tooltip btn btn-sm btn-default btn-outline"
                           title="Voir la liste des titulaires pour cette rencontre">Voir liste joueurs
                        </a>
                    </td>

                </tr>

                <!-- Modal Ajout Liste des joueurs! -->
            <div class="modal fade" id="editerEquipeModal_<%= match.getId()%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Selectionner les joueurs pour le Match</h4>
                        </div>
                        <form id="login_form" method="get" action="index" class="form-horizontal">
                            <div class="modal-body">
                                <div class="form-group">
                                    <div class="col-sm-9">
                                        <% for (ContratJoueur contrat : contratJoueurs) {%>
                                        <label class="checkbox-inline">
                                            <input type="checkbox" name="titulaires" checked data-validation="checkbox_group" data-validation-qty="min3"
                                                   value="<%= contrat.getJoueur().getId()%>" id="inlineCheckbox1"> <%= contrat.getJoueur().getNom()%>
                                        </label>
                                        <br>
                                        <% }%>          

                                    </div>
                                </div>
                                <br><br>

                                <input type ="hidden" name="idMatch" value="<%= match.getId()%>">
                                <input type ="hidden" name="action" value="ajouterJoueurDansMatch">
                            </div>
                            <br><br>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                                <button type="submit" class="btn btn-primary">Enregistrer</button>
                            </div>
                        </form>                        
                    </div>
                </div>
            </div>


            <!-- Modal Liste des joueurs -->
            <div class="modal fade" id="listeJoueurModal_<%= match.getId()%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">La liste des titulaires pour le Match</h4>
                        </div>

                        <div class="modal-body">
                             
                            <ul class="asTh">
                                        <li>
                                        Nom  | 
                                        Prenom |
                                        Maillot |
                                        Position </li>                            
                                    </ul>
                                 

                                    <%
                    for (Joueur joueur : match.getJoueurs()) {%>
                    <ul class="asTr">
                                       <li><%= joueur.getNom()%>  |
                                        <%= joueur.getPrenom()%>  |
                                        <%= joueur.getMaillot()%>  |
                                        <%= joueur.getPosition()%></li>


                                    </ul>
                                    <% } %>
                                
                        </div>
                        <br><br>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>

                        </div>

                    </div>
                </div>

            </div>

            <% }%>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/partials/footer.jsp" />