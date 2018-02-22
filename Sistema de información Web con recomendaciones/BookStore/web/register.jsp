<%@ page import="javafx.util.Pair" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.controlador.CommonConstants" %>
<%@ page import="com.bookstore.modelo.TiendaFacade" %>
<%@ page import="com.bookstore.modelo.VO.GeneroVO" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: Abel
  Date: 14/11/2017
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    String titulo_pagina = "Login ";

    String username = (String) session.getAttribute(CommonConstants.usernameParameterName);

    String pageStatus = (String) request.getAttribute(CommonConstants.pageStatusParameterName);

    String href_registrarse = "?";
    
    List<Pair<String, String>> generos = new ArrayList<>();

    for (GeneroVO i : TiendaFacade.listarGeneros()){
        generos.add(new Pair<>( i.getNombre(),CommonConstants.browserLocation + "?" +CommonConstants.browserCategoryParameterName + "=" + i.getNombre()));
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= titulo_pagina%>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="/js/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="/js/slick-1.8.0/slick/slick.min.js"></script>

    <link rel="stylesheet" type="text/css" href="/js/slick-1.8.0/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="/js/slick-1.8.0/slick/slick-theme.css"/>
    <link rel="stylesheet" type="text/css" href="/css/mainpage.css"/>
    <link rel="stylesheet" type="text/css" href="/css/common.css"/>
</head>
<body>

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


            <!-- edit form column -->
            <div class="col-md-9 personal-info">
                <h3>Registrarse</h3>
                <hr>

                <%
                    HashMap errores = (HashMap) request.getAttribute(CommonConstants.registerFailParameterName);
                    if (errores != null){
                        if (errores.get(CommonConstants.emailProfileParameterName)!= null){
                            %>
                            <div class="alert alert-danger">
                                <strong>Error</strong> <%=(String)errores.get(CommonConstants.emailProfileParameterName)%>
                            </div>
                <%
                    }
                %>
                <%   if (errores.get(CommonConstants.usernameParameterName)!=null) {
                %>
                            <div class="alert alert-danger">
                                <strong>Error</strong> <%=(String)errores.get(CommonConstants.usernameParameterName)%>
                            </div>
                <%
                    }%>
                <%   if (errores.get(CommonConstants.passwordParameterName)!=null) {
                        %>
                        <div class="alert alert-danger">
                                <strong>Error</strong> <%=(String)errores.get(CommonConstants.passwordParameterName)%>
                        </div>
                <%
                    }%>
                <%
                    if (errores.get(CommonConstants.fullNameProfileParameterName)!=null) {
                %>
                        <div class="alert alert-danger">
                            <strong>Error</strong> <%=(String)errores.get(CommonConstants.fullNameProfileParameterName)%>
                        </div>
                <%
                        }
                %>
                <%
                    if (errores.get(CommonConstants.surnameProfileParameterName)!=null) {
                %>
                        <div class="alert alert-danger">
                            <strong>Error</strong> <%=(String)errores.get(CommonConstants.surnameProfileParameterName)%>
                        </div>
                <%
                    }
                %>
                <%
                    if (errores.get(CommonConstants.locationParameterName)!=null) {
                %>
                <div class="alert alert-danger">
                    <strong>Error</strong> <%=(String)errores.get(CommonConstants.locationParameterName)%>
                </div>
                <%
                    }
                %>

                <%
                    }else{
                        System.out.println("***************************************************");
                    }
                %>

                <form class="form-horizontal" role="form" action="<%= CommonConstants.registerLocation %>" method="post">

                    <div class="form-group">
                        <label class="col-md-3 control-label">Nombre de usuario:</label>
                        <div class="col-md-8">
                            <input class="form-control" value="" type="text" name="<%= CommonConstants.usernameParameterName%>" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Contraseña:</label>
                        <div class="col-md-8">
                            <input class="form-control" value="" type="password" name="<%= CommonConstants.password1ParameterName%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Repetir contraseña:</label>
                        <div class="col-md-8">
                            <input class="form-control" value="" type="password" name="<%= CommonConstants.password2ParameterName%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Email:</label>
                        <div class="col-md-8">
                            <input class="form-control" value="" type="text" name="<%= CommonConstants.emailProfileParameterName%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nombre:</label>
                        <div class="col-md-8">
                            <input class="form-control" value="" type="text" name="<%= CommonConstants.fullNameProfileParameterName%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Apellidos:</label>
                        <div class="col-md-8">
                            <input class="form-control" value="" type="text" name="<%= CommonConstants.surnameProfileParameterName%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Fecha de nacimiento:</label>
                        <div class="col-md-8">
                            <input class="form-control" value="dd/mm/aaaa" type="text" name="<%= CommonConstants.dayParameterName%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Localidad:</label>
                        <div class="col-md-8">
                            <input class="form-control" value="" type="text" name="<%= CommonConstants.locationParameterName%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label"></label>
                        <div class="col-md-8">
                            <input class="btn btn-primary" value="Registrar" type="submit">
                            <span></span>
                            <a href="<%= href_registrarse%>"><input class="btn btn-default" value="Cancelar"
                                                           type="button"></a>
                        </div>
                    </div>
                </form>
            </div>


        </div><!--/.col-xs-12.col-sm-9-->

    </div><!--/row-->

    <hr>
    <%
        if(pageStatus!=null) System.out.println(pageStatus);

        if(pageStatus!=null && pageStatus.equals(CommonConstants.loginAuthFailedPageStatus)){
            System.out.println("Page estatua failed");
    %>

    <div class="alert alert-danger" role="alert">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span class="sr-only">Error:</span>
        Nombre de usuario o contraseña incorrecta
    </div>

    <%}%>

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
