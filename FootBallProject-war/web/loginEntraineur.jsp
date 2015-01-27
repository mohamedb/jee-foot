<jsp:include page="/partials/header.jsp" />
<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-5">
        <div class="login_container">
            <form id="login_form"  method="post" action="index"  class="form-horizontal">
                <h2 class="heading_a"><span class="heading_text">Authentification Entraineur</span></h2>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Email</label>
                    <div class="col-sm-9">
                        <input type="email" name="email" data-validation="email"
                               class="form-control" placeholder="utilisateur@iae.me">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Mot de passe</label>
                    <div class="col-sm-9">
                        <input type="password" name="motDePasse" data-validation="required"
                               class="form-control" placeholder="mon-mot-de-passe">
                    </div>
                </div>
                <input type ="hidden" name="action" value="loginEntraineur">
                <div class="form-group sepH_c">
                    <div class="col-sm-9 col-sm-offset-3">
                        <button type="submit" class="btn btn-sm btn-primary btn-block">Log in</button>
                    </div>
                </div>
            </form>
            <b class="heading_a">
                <span class="heading_text">
                    <a href="creerCompte.jsp" class="btn btn-sm">Créer un compte</a>
                </span>
            </b>
            <b class="heading_a pull-right">
                <span class="heading_text">
                    <a href="login.jsp" class="btn btn-sm">Se connecter en tant que Root</a>
                </span>
            </b>

        </div>

    </div>
</div>
<jsp:include page="/partials/footer.jsp" />