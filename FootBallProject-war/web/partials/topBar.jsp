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
                <li>
                    <a href="index?action=entraineurIndex">Accès Entraineurs</a>
                </li>
                <li>
                    <a href="index?action=federationIndex">Accès Fédération</a>
                </li> 
                <li>
                    <a href="index?action=rootIndex">Accès Admin</a>
                </li>
            </ul>
            
            <ul class="nav navbar-nav navbar-right">
            <li>
                <% if(utilisateur.getNom()==null){%>
                    <a href="login.jsp">Se Connecter</a>
                <% } else { %>
                <a>Bonjour: <%= utilisateur.getNom() %></a>
                <% } %>
            </li> 
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>