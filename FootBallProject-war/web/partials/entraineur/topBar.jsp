<jsp:useBean id="entraineur" scope="session" class="football.entities.Entraineur"></jsp:useBean>
<jsp:useBean id="equipe" scope="session" class="football.entities.Equipe"></jsp:useBean>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button class="navbar-toggle collapsed" data-target="#bs-example-navbar-collapse-1" data-toggle="collapse" type="button">

                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index?action=entraineurIndex">Accueil</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">                    
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            Joueurs <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="index?action=listeJoueur">Lister</a></li>
                            <li class="divider"></li>
                            <li><a href="index?action=creerJoueurPage">Ajouter</a></li>
                            
                            
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            Matchs <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="index?action=listeMatchParEquipe">Lister</a></li>                                                      
                        </ul>                       
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a>
                            <% if (equipe != null) {%>
                              Equipe  <b> <%= equipe.getNom() %></b>
                             <% }%>
                        </a>
                    </li>
                    <li>
                    <% if (entraineur.getNom() == null) {%>
                    <a href="login.jsp">Se Connecter</a>
                    <% } else {%>
                         
                    <a>Bonjour: <b><%= entraineur.getNom()%></b> </a>
                     
                     <li><a href="index?action=seDeconnecter">Se deconnecter</a></li>
                    <% }%>
                </li> 
            </ul>
        </div><!-- /.navbar-collapse -->



    </div><!-- /.container-fluid -->
</nav>
