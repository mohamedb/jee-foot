<jsp:useBean id="utilisateur" scope="session" class="football.entities.Utilisateur"></jsp:useBean>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button class="navbar-toggle collapsed" data-target="#bs-example-navbar-collapse-1" data-toggle="collapse" type="button">

                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index">Ligue-App</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">                    
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            Entraineurs <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="index?action=listeEntraineur">Lister</a></li>
                            <li class="divider"></li>
                            <li><a href="index?action=creerEntraineurPage">Ajouter</a></li>
                            <li class="divider"></li>
                            <li><a href="index?action=affecterEntraineurPage">Affecter entraineur</a></li>
                            
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            Equipes <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="index?action=listeEquipe">Lister</a></li>
                            <li class="divider"></li>
                            <li><a href="index?action=creerEquipePage">Ajouter</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            Championnats <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="index?action=listeChampionnat">Lister</a></li>
                            <li class="divider"></li>
                            <li><a href="index?action=creerChampionnatPage">Ajouter</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            Matchs <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="index?action=listeMatch">Lister</a></li>
                            <li class="divider"></li>                           
                        </ul>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li>
                    <% if (utilisateur.getNom() == null) {%>
                    <a href="login.jsp">Se Connecter</a>
                    <% } else {%>
                    <a>Bonjour: <%= utilisateur.getNom()%></a>
                <li><a href="index?action=seDeconnecter">Se deconnecter</a></li>
                    <% }%>
                </li> 
            </ul>
        </div><!-- /.navbar-collapse -->



    </div><!-- /.container-fluid -->
</nav>
