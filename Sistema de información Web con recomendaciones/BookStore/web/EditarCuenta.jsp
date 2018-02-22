<%@ page import="com.bookstore.controlador.CommonConstants" %>
<%@ page import="javafx.util.Pair" %>
<%@ page import="com.bookstore.modelo.TiendaFacade" %>
<%@ page import="com.bookstore.modelo.VO.GeneroVO" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bookstore.modelo.VO.UsuarioVO" %>
<%@ page import="java.util.List" %>
<%@ page import="static com.bookstore.modelo.TiendaFacade.leerUsuarioFacade" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Editar Cuenta</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/css/perfil.css"/>

</head>
<body>

<%
    String username = (String) session.getAttribute(CommonConstants.usernameParameterName);
    UsuarioVO user = leerUsuarioFacade(username);

    List<Pair<String, String>> generos = new ArrayList<>();

    for (GeneroVO i : TiendaFacade.listarGeneros()){
        generos.add(new Pair<>( i.getNombre(),CommonConstants.browserLocation + "?" +CommonConstants.browserCategoryParameterName + "=" + i.getNombre()));
    }
%>

<nav class="navbar navbar-expand-lg bg-transp font-roboto">
    <div class="navbar-collapse">
        <ul class="nav navbar-nav">
            <li class="dropdown nav-item"><a class="dropdown-toggle" data-toggle="dropdown" href="">Todos los productos
                <span
                        class="caret"></span></a>
                <ul class="dropdown-menu">
                    <% for (Pair<String, String> genero : generos) {%>
                    <li><a href="<%= genero.getValue() %>"><%= genero.getKey() %>
                    </a></li>
                    <%}%>
                </ul>
            </li>

            <li class="nav-item"><a href="<%= CommonConstants.indexLocation %>">Inicio </a></li>
            <li class="nav-item"><a href="<%= CommonConstants.indexLocation + "?" + CommonConstants.pageStatusParameterName + "=" + CommonConstants.indexMasBuscadosPageStatus %>">Más populares </a></li>
            <li class="nav-item"><a href="<%=  CommonConstants.indexLocation + "?" + CommonConstants.pageStatusParameterName + "=" + CommonConstants.indexNovedadesPageStatus %>">Novedades </a></li>

            <li class="nav-item"><a href="#search">Buscar</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">

            <%
                if (username != null) {%>

            <li class="dropdown nav-item"><a class="dropdown-toggle" data-toggle="dropdown" href=""><%= username%> <span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="<%= CommonConstants.profileLocation %>">Ver Perfil</a></li>
                    <li><a href="<%= CommonConstants.logoutLocation %>">Desconectarse</a></li>

                </ul>
            </li>

            <%} else {%>
            <li class="nav-item"><a href="<%= CommonConstants.loginLocation%>">Acceder </a></li>
            <%}%>


        </ul>
    </div>
</nav>


<div class="container-fluid">
    <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9 col-sm-push-3">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas" title="Toggle sidebar"><i
                        class="fa fa-chevron-right"></i></button>
            </p>


            <%
                String errorMail = "";
                String errorUsername = "";
                String errorPassword = "";
                HashMap errores = (HashMap) request.getAttribute("errores");
            if (errores != null){
                if (errores.get(CommonConstants.emailProfileParameterName)!=null) {
                    errorMail = (String) errores.get(CommonConstants.emailProfileParameterName);%>
            <div class="alert alert-danger">
                <strong>Error</strong> <%=errorMail%>
            </div>
            <%
                }
                if (errores.get(CommonConstants.passwordParameterName)!=null) {
                    errorPassword = (String) errores.get(CommonConstants.passwordParameterName);%>
            <div class="alert alert-danger">
                <strong>Error</strong> <%=errorPassword%>
            </div>
            <%
                    }
                }
            %>

            <%
                Boolean exito = (Boolean) request.getAttribute(CommonConstants.succesParameterName);
                System.out.println("-->exito: " + exito);
                if(exito != null){
                    if(exito){ %>
                        <div class="alert alert-success">
                            <strong>Exito</strong> Cambios realizados con exito.
                        </div>
                    <% }
                } %>

            <!-- edit form column -->
            <div class="col-md-9 personal-info">
                <!--<div class="alert alert-info alert-dismissable">
                  <a class="panel-close close" data-dismiss="alert">×</a>
                  <i class="fa fa-coffee"></i>
                  Las contraseñas no coinciden.
                </div>-->
                <h3>Cuenta</h3>
                <hr>

                <form action="/EditarCuenta" method="post">
                <form class="form-horizontal" role="form" action="Perfil.html">

                    <div class="form-group">
                        <label class="col-lg-3 control-label">Email:</label>
                        <div class="col-lg-8">
                            <input class="form-control" value="<%=user.getEmail()%>" type="text" name="<%=CommonConstants.emailProfileParameterName%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nombre de usuario:</label>
                        <div class="col-md-8">
                            <input readonly class="form-control" value="<%=user.getNombreDeUsuario()%>" type="text" name="<%=CommonConstants.usernameParameterName%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Contraseña:</label>
                        <div class="col-md-8">
                            <input class="form-control" value="" type="password" name="<%=CommonConstants.password1ParameterName%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Confirmar contraseña:</label>
                        <div class="col-md-8">
                            <input class="form-control" value="" type="password" name="<%=CommonConstants.password2ParameterName%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"></label>
                        <div class="col-md-8">
                            <input class="btn btn-primary" value="Guardar cambios" type="submit"></a>
                            <span></span>
                            <a href="<%=CommonConstants.profileLocation%>"><input class="btn btn-default" value="Cancelar" type="button"></a>
                        </div>
                    </div>
                </form>
                </form>
            </div>


        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 col-sm-pull-9 sidebar-offcanvas" id="sidebar">
            <div class="list-group">
                <a href="<%=CommonConstants.profileAccountEdit %>" class="list-group-item">Información personal</a>
                <a href="<%=CommonConstants.profilePersonalEdit%>" class="list-group-item active">Cuenta</a>
            </div>
        </div><!--/.sidebar-offcanvas-->
    </div><!--/row-->

    <hr>

</div><!--/.container-->
<div id="search">
    <button type="button" class="close">×</button>
    <form  role="form" action="<%= CommonConstants.browserLocation %>" method="get">
        <input type="search" value="" name="<%= CommonConstants.browserBookNameParameterName %>" placeholder="Introduce el nombre de un libro" />
        <input class="btn btn-primary" value="Buscar" type="submit">
    </form>
</div>

<script type="text/javascript" src="/js/script.js"></script>

</body>
</html>
