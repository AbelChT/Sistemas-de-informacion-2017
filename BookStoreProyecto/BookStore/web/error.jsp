<%@ page import="javafx.util.Pair" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.controlador.CommonConstants" %>
<%@ page import="com.bookstore.modelo.VO.GeneroVO" %>
<%@ page import="com.bookstore.modelo.TiendaFacade" %>
<%--
  Created by IntelliJ IDEA.
  User: Abel
  Date: 14/11/2017
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error_type = request.getParameter("error_type");
    if (error_type == null) error_type = "404";

    String titulo_pagina = "Error " + error_type;
    
    String username = (String) session.getAttribute(CommonConstants.usernameParameterName);
    
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

<div class="container-fluid center-block">
    <h1>Error: <%= error_type%></h1>
</div>

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
