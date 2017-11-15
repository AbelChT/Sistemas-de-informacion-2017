<%@ page import="javafx.util.Pair" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.controlador.CommonConstants" %><%--
  Created by IntelliJ IDEA.
  User: Abel
  Date: 14/11/2017
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    String titulo_pagina = "Error ";
    List<Pair<String, String>> generos = new ArrayList<>();
    generos.add(new Pair<>("Ciencia", "#"));
    generos.add(new Pair<>("Ficción y literatura", "#"));
    generos.add(new Pair<>("Finanzas e inversión", "#"));
    generos.add(new Pair<>("Historia", "#"));
    generos.add(new Pair<>("Informática y tecnología", "#"));
    generos.add(new Pair<>("Infantiles", "#"));
    generos.add(new Pair<>("Psicología", "#"));

    String label_login = "Acceder";
    String href_login = "Login.html";

    String href_perfil = "";
    String href_desconectarse = "";
    String label_perfil = "";
    String label_desconectarse = "";
    boolean is_logged = false;

    String href_loggin_manager= "?";
    String user_input_variable_name = CommonConstants.usernameParameterName;
    String pass_input_variable_name = CommonConstants.passwordParameterName;
    String href_registrarse = "/";
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
            <li class="dropdown nav-item"><a class="dropdown-toggle" data-toggle="dropdown" href="">Todos los productos <span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <% for (Pair<String,String> genero : generos) {%>
                    <li><a href="<%= genero.getValue() %>" > <%= genero.getKey() %></a></li>
                    <%}%>
                </ul>
            </li>

            <li class="nav-item"><a href="/">Inicio </a></li>
            <li class="nav-item"><a href="?page=maspopulares">Más populares </a></li>
            <li class="nav-item"><a href="?page=novedades">Novedades </a></li>

            <li class="nav-item"><a>Buscar</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <%if(is_logged){%>
            <li class="nav-item"><a href="<%= href_perfil%>"><%= label_perfil%></a></li>
            <li class="nav-item"><a href="<%= href_desconectarse%>"><%= label_desconectarse%></a></li>
            <%} else{%>
            <li class="nav-item"><a href="<%= href_login%>"><%= label_login%></a></li>
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
                <h3>Inicia sesión</h3>
                <hr>

                <form class="form-horizontal" role="form" action="<%= href_loggin_manager%>" method="post">

                    <div class="form-group">
                        <label class="col-md-3 control-label">Nombre de usuario:</label>
                        <div class="col-md-8">
                            <input class="form-control" value="" type="text" name="<%= user_input_variable_name%>" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Contraseña:</label>
                        <div class="col-md-8">
                            <input class="form-control" value="" type="password" name="<%= pass_input_variable_name%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label"></label>
                        <div class="col-md-8">
                            <input class="btn btn-primary" value="Acceder" type="submit">
                            <span></span>
                            <a href="<%= href_registrarse%>"><input class="btn btn-default" value="Registrarse"
                                                           type="button"></a>
                        </div>
                    </div>
                </form>
            </div>


        </div><!--/.col-xs-12.col-sm-9-->

    </div><!--/row-->

    <hr>

</div><!--/.container-->

</body>
</html>
