<jsp:useBean id="entraineur" scope="session" class="football.entities.Entraineur"></jsp:useBean>
<jsp:include page="/partials/header.jsp" />
<jsp:include page="/partials/entraineur/topBar.jsp" />
<div class="col-md-6">
    
    <div class="login_container">
        <form id="login_form" method="get" action="index" class="form-horizontal">
            <h2 class="heading_a"><span class="heading_text">Ajouter un joueur dans l'équipe</span></h2>
            <div class="form-group">
                <label class="col-sm-3 control-label">Nom *</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" 
                           data-validation="length" data-validation-length="4-30"
                           name="nom" placeholder="Benzima">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Prénom *</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" 
                           data-validation="length" data-validation-length="4-30"
                           name="prenom" placeholder="Karim">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Maillot *</label>
                <div class="col-sm-9">
                    <input type="number" class="form-control" 
                           data-validation="number" data-validation-allowing="range[1;100]"
                           name="maillot" placeholder="13">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Position</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="position" placeholder="Attaquant">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Bio</label><br>
                <div class="col-sm-9">
                    <textarea name="bio" class="form-control" placeholder="Bio ..."></textarea>
                </div>
            </div>
            <h2 class="heading_a"><span class="heading_text">Elements du contrat</span></h2>
           
                <div class="form-group">
                <label class="col-sm-3 control-label">Salaire en &euro;</label>
                <div class="col-sm-9">
                    <input type="number" class="form-control" data-validation="number" data-validation-allowing="range[3000;100000]"
                           name="salaire" placeholder="7000">
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
            <input type ="hidden" name="action" value="creerJoueur">
            <br>
            <div class="form-group sepH_c">
                <div class="col-sm-9 col-sm-offset-3">
                    <button type="submit" class="btn btn-sm btn-primary btn-block">Créer</button>
                </div>
            </div>
        </form>


    </div>

</div>
<jsp:include page="/partials/footer.jsp" />