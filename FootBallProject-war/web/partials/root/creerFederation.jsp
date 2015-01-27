<jsp:useBean id="utilisateur" scope="session" class="football.entities.Utilisateur"></jsp:useBean>
<jsp:include page="/partials/header.jsp" />
<jsp:include page="/partials/root/rootTopBar.jsp" />
<div class="col-md-5">
    <div class="login_container">
        <form id="login_form" method="get" action="index" class="form-horizontal">
            <h2 class="heading_a"><span class="heading_text">Créer un compte fédération</span></h2>
            <div class="form-group">
                <label class="col-sm-3 control-label">Email *</label>
                <div class="col-sm-9">
                    <input type="email" class="form-control" name="email" data-validation="email"
                           placeholder="utilisateur@iae.me">
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-3 control-label">Nom *</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" data-validation="required"
                           name="nom" placeholder="Moi Hero">
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-3 control-label">Mot de passe *</label>
                <div class="col-sm-9">
                    <input type="password" name="motDePasse" data-validation="required"
                           class="form-control" placeholder="mon-mot-de-passe">
                </div>
            </div>
             <div class="form-group">
             <label class="col-sm-3 control-label">Nom organisme</label><br>
              <div class="col-sm-9">
                  <input type="text" class="form-control" data-validation="required"
                         name="organisme" placeholder="Ex: FFF">
              </div>
             </div>
            <input type ="hidden" name="action" value="creerFederation">
            <div class="form-group sepH_c">
                <div class="col-sm-9 col-sm-offset-3">
                    <button type="submit" class="btn btn-sm btn-primary btn-block">Créer</button>
                </div>
            </div>
        </form>
         

    </div>

</div>
<jsp:include page="/partials/footer.jsp" />