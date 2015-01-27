<jsp:useBean id="utilisateur" scope="session" class="football.entities.Utilisateur"></jsp:useBean>
<jsp:include page="/partials/header.jsp" />
<jsp:include page="/partials/federation/topBar.jsp" />
<div class="col-md-5">
    <div class="login_container">
        <form id="login_form" method="get" action="index" class="form-horizontal">
            <h2 class="heading_a"><span class="heading_text">Créer Championnat</span></h2>
            
            <div class="form-group">
                <label class="col-sm-3 control-label">Nom *</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" 
                           data-validation="length" data-validation-length="2-30"
                           name="nom" placeholder="Premier League">
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-3 control-label">Année/Saison *</label>
                <div class="col-sm-9">
                    <input type="number" name="annee" 
                           data-validation="number" data-validation-allowing="range[2015;2020]"
                           class="form-control" value="2015" placeholder="2015">
                </div>
            </div>
             <div class="form-group">
             <label class="col-sm-3 control-label">Description</label><br>
              <div class="col-sm-9">
                  <textarea name="description" class="form-control" placeholder="Premieère Division  d'Angleterre ..."></textarea>
              </div>
             </div>
            <input type ="hidden" name="action" value="creerChampionnat">
            <div class="form-group sepH_c">
                <div class="col-sm-9 col-sm-offset-3">
                    <button type="submit" class="btn btn-sm btn-primary btn-block">Créer</button>
                </div>
            </div>
        </form>
         

    </div>

</div>
<jsp:include page="/partials/footer.jsp" />