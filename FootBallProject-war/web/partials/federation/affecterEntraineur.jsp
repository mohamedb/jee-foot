<%@page import="football.entities.Utilisateur"%>
<%@page import="football.entities.Entraineur"%>
<%@page import="football.entities.ContratEntraineur"%>
<%@page import="java.util.List" %>
<%@page import="football.entities.Equipe" %>
<jsp:useBean id="listeEquipe" scope="request" class="java.util.List"></jsp:useBean>

<jsp:useBean id="utilisateur" scope="session" class="football.entities.Utilisateur"></jsp:useBean>
<jsp:include page="/partials/header.jsp" />
<% if ( utilisateur.getId() == null) {%>
<jsp:include page="/partials/403.jsp" />
<% } else { %>
<jsp:include page="/partials/federation/topBar.jsp" />

<div class="row">

    <div class="col-md-5">
        <div class="login_container">
            <form id="login_form" method="get" action="index" class="form-horizontal">
                <h2 class="heading_a"><span class="heading_text">Affecter un entraineur à une équipe</span></h2>
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
                
                <div class="form-group">
                    <label class="col-sm-3 control-label">Entraineur :</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="entraineurs">
                            <%  List<Entraineur> entraineurs = (List<Entraineur>) request.getAttribute("listeEntraineur");
                                for (Entraineur entraineur : entraineurs) {%>
                            <option value="<%= entraineur.getId()%>"><%= entraineur.getNom()%></option>
                            <% } %>          
                        </select> 
                    </div>
                </div>
                <div class="form-group">
                <label class="col-sm-3 control-label">Date début *</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" 
                           data-validation="date" data-validation-format="dd-mm-yyyy"
                           name="dateDebut" placeholder="23-04-2015">
                </div>
                </div>
                <div class="form-group">
                <label class="col-sm-3 control-label">Date fin *</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" 
                           data-validation="date" data-validation-format="dd-mm-yyyy"
                           name="dateFin" placeholder="14-09-2016">
                </div>
                </div>
                        <br><br>       
                <input type ="hidden" name="action" value="affecterEntraineur">
                <div class="form-group sepH_c">
                    <div class="col-sm-9 col-sm-offset-3">
                        <button type="submit" class="btn btn-sm btn-primary btn-block">Affecter</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
    <% }%>