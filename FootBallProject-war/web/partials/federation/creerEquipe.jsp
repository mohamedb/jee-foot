<jsp:useBean id="utilisateur" scope="session" class="football.entities.Utilisateur"></jsp:useBean>
<jsp:include page="/partials/header.jsp" />
<jsp:include page="/partials/federation/topBar.jsp" />
<div class="col-md-5">
    <div class="login_container">
        <form id="login_form" method="get" action="index" class="form-horizontal">
            <h2 class="heading_a"><span class="heading_text">Créer une Equipe</span></h2>
            <div class="form-group">
                <label class="col-sm-3 control-label">Nom *</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="nom" 
                           data-validation="length" data-validation-length="2-30" 
                           placeholder="Olympique Lyonnais">
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-3 control-label">Points *</label>
                <div class="col-sm-9">
                    <input type="number" class="form-control" 
                           data-validation="number" data-validation-allowing="range[0;100]"
                           name="points" placeholder="0">
                </div>
            </div>
            <input type ="hidden" name="action" value="creerEquipe">
            <div class="form-group sepH_c">
                <div class="col-sm-9 col-sm-offset-3">
                    <button type="submit" class="btn btn-sm btn-primary btn-block">Créer</button>
                </div>
            </div>
        </form>
         

    </div>

</div>
<jsp:include page="/partials/footer.jsp" />