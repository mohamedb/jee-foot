<%@page import="football.entities.Equipe"%>
<%@page import="football.entities.Participation"%>
<%@page import="football.entities.Championnat"%>
<%@page import="java.util.List" %>


<jsp:useBean id="utilisateur" scope="session" class="football.entities.Utilisateur"></jsp:useBean>
<jsp:include page="/partials/header.jsp" />
<% if (utilisateur.getId() == null) {%>
<jsp:include page="/partials/403.jsp" />
<% } else { %>
<jsp:include page="/partials/federation/topBar.jsp" />
<% Championnat championnat = (Championnat) request.getAttribute("championnat"); %>
<% List<Participation> participations = (List<Participation>) championnat.getParticipations();%>
<div class="row">
    <div class="col-sm-5">
        <b>Championnat: <%= championnat.getNom()%> </b>
        <h4>Saison:<%= championnat.getAnnee()%> </h4>
    </div>
    <div class="col-sm-7 text-right">
        Mes actions pour ce championnat:<br>
        <a href="index?action=voirChampionnat&id=<%= championnat.getId()%>" 
                               class="tooltip btn btn-sm btn-info btn-outline"
                               title="Actualiser la liste">Actualiser</a>
        <a data-toggle="modal" data-target="#ajoutEquipeModal"
           class="tooltip btn btn-sm btn-default btn-outline"
           title="Ajouter une équipe à ce championnat">Ajouter Equipe</a>
        <a data-toggle="modal" data-target="#ajoutMatchModal"
           class="tooltip btn btn-sm btn-default btn-outline"
           title="Programmer un match dans ce championnat">Ajouter Match</a>
        <a href="index?action=supprimerChampionnat&id=<%= championnat.getId()%>" 
           class="tooltip btn btn-sm btn-danger btn-outline" 
           title="Attention: suppression championnat !"> Supprimer</a>

    </div>
    
    <div class="row">
        <div class="col-md-12" update-fixed-headers>
            <jsp:include page="/partials/recherche.jsp" />
            <code>Cliquer sur 'Nombre points' pour avoir le classement</code>
            <table id="datatable_demo" data-filter="#filter"
                   class="footable table table-striped table-bordered" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>Nom Equipe</th>
                        <th>Nombre points</th>
                        
                                            
                    </tr>
                </thead>
                <tbody>
                    <% for (Participation participation : participations) {%>
                    <tr>
                        <td><%= participation.getEquipe().getNom()%></td>
                        <td><%= participation.getNombrePoint()%></td>
                        

                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- Modal Ajout equipe -->
<div class="modal fade" id="ajoutEquipeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Ajouter une Equipe dans ce championnat</h4>
            </div>
            <form id="login_form" method="get" action="index" class="form-horizontal">
                <div class="modal-body">
                <div class="form-group">
                    <label class="col-sm-3 control-label">Equipe :</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="equipes">
                            <%  List<Equipe> equipes = (List<Equipe>) request.getAttribute("listeEquipe");
                                for (Equipe equipe : equipes) {%>
                            <option value="<%= equipe.getId()%>"><%= equipe.getNom()%></option>
                            <% } %>          
                        </select> 
                    </div>
                </div>
                <input type ="hidden" name="idChampionnat" value="<%= championnat.getId()%>">
                <input type ="hidden" name="action" value="ajouterEquipeDansChampionnat">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                </div>
            </form>

        </div>
    </div>
</div>
                
<!-- Modal Ajout Match -->
<div class="modal fade" id="ajoutMatchModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Ajouter une Equipe dans ce championnat</h4>
            </div>
            <form id="login_form" method="get" action="index" class="form-horizontal">
                <div class="modal-body">
                <div class="form-group">
                    <label class="col-sm-3 control-label">Equipe <i>Visiteur</i> :</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="visiteurs" data-validation="required">
                            <% for (Equipe equipe : equipes) {%>
                            <option value="<%= equipe.getId()%>"><%= equipe.getNom()%></option>
                            <% } %>          
                        </select> 
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Equipe <i>Recepteur</i> :</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="recepteurs"data-validation="required">
                            <% for (Equipe equipe : equipes) {%>
                            <option value="<%= equipe.getId()%>"><%= equipe.getNom()%></option>
                            <% } %>          
                        </select> 
                    </div>
                </div>        
                <input type ="hidden" name="idChampionnat" value="<%= championnat.getId()%>">
                <input type ="hidden" name="action" value="ajouterMatch">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                </div>
            </form>

        </div>
    </div>
</div>             
<% }%>
<jsp:include page="/partials/footer.jsp" />