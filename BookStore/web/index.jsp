<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.modelo.VO.LibroVO" %>
<%@ page import="com.bookstore.modelo.VO.AutorVO" %>
<%@ page import="static com.bookstore.modelo.TiendaFacade.*" %>
<%@ page import="javafx.util.Pair" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bookstore.controlador.CommonConstants" %>
<%@ page import="com.bookstore.modelo.VO.GeneroVO" %>
<%@ page import="com.bookstore.modelo.TiendaFacade" %>
<%@ page import="com.bookstore.modelo.VO.UsuarioVO" %>
<%--
  Created by Abel
  Date: 13/11/2017
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String titulo_pagina = null;

    List<Pair<String, List<LibroVO>>> carruseles = new ArrayList<>();

    String username = (String) session.getAttribute(CommonConstants.usernameParameterName);

    String pagina = request.getParameter(CommonConstants.pageStatusParameterName);
    if (pagina == null) pagina = "";
    
    try {
        switch (pagina) {
            case CommonConstants.indexMasBuscadosPageStatus:
                titulo_pagina = "Libros más populares";
                carruseles.add(new Pair<>("Más vendidos", listarLibros(CategoriaLibrosListar.MAS_VENDIDOS, null, 6)));
                carruseles.add(new Pair<>("Más vendidos esta semana", listarLibros(CategoriaLibrosListar.MAS_VENDIDOS, RangosTiempoLibrosListar.ESTA_SEMANA , 6)));
                carruseles.add(new Pair<>("Más vendidos este mes", listarLibros(CategoriaLibrosListar.MAS_VENDIDOS, RangosTiempoLibrosListar.ESTE_MES , 6)));
                carruseles.add(new Pair<>("Más visitados", listarLibros(CategoriaLibrosListar.MAS_VISITADOS, null, 6)));
                carruseles.add(new Pair<>("Más visitados esta semana", listarLibros(CategoriaLibrosListar.MAS_VISITADOS, RangosTiempoLibrosListar.ESTA_SEMANA , 6)));
                carruseles.add(new Pair<>("Más visitados este mes", listarLibros(CategoriaLibrosListar.MAS_VISITADOS, RangosTiempoLibrosListar.ESTE_MES , 6)));
                break;
            case CommonConstants.indexNovedadesPageStatus:
                titulo_pagina = "Novedades";
                carruseles.add(new Pair<>("Últimas publicaciones", listarLibros(CategoriaLibrosListar.NUEVOS, null , 6)));
                carruseles.add(new Pair<>("Publicados esta semana", listarLibros(CategoriaLibrosListar.NUEVOS, RangosTiempoLibrosListar.ESTA_SEMANA , 6)));
                carruseles.add(new Pair<>("Publicados este mes", listarLibros(CategoriaLibrosListar.NUEVOS, RangosTiempoLibrosListar.ESTE_MES , 6)));
                break;
            default:
                titulo_pagina = "Inicio";
                if (username != null)
                carruseles.add(new Pair<>("Recomendaciones", listarLibros(CategoriaLibrosListar.RECOMENDACIONES, null, new UsuarioVO(username), 6)));

                carruseles.add(new Pair<>("Más vendidos", listarLibros(CategoriaLibrosListar.MAS_VENDIDOS, null, 6)));
                carruseles.add(new Pair<>("Populares", listarLibros(CategoriaLibrosListar.MAS_VISITADOS, null, 6)));
                carruseles.add(new Pair<>("Novedades", listarLibros(CategoriaLibrosListar.NUEVOS, null, 6)));
        }
    } catch (Exception e) {
        response.sendRedirect("/error/");
    }

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

<%
    for (Pair<String, List<LibroVO>> carrusel_actual : carruseles) {

%>

<div class="carrousel-element">
    <div class="carrousel-title">
        <h1 id="carrousel-category-n1"><%= carrusel_actual.getKey() %>
        </h1>
    </div>

    <div class='carrousel-element-container'>
        <div class='single-item'>
            <%
                for (LibroVO i : carrusel_actual.getValue()) {
                    String autores = "---";
                    if (i.getAutores() != null) {
                        autores = "";
                        for (AutorVO j : i.getAutores()) {
                            if (!autores.equals("")) {
                                autores += ", ";
                            }
                            autores += j.getNombreCompleto();
                        }
                    }
            %>
            <div class='item'>
                <a href="<%= CommonConstants.libroInfoLocation + "?" + CommonConstants.isbnParameterName + "=" + i.getIsbn() %>"><img class="carrousel-element-image"
                   src="<%= i.getPathImagen() %>"></a>
                <h3 class="carrousel-element-titulo"><%= i.getTitulo() %>
                </h3>
                <h4 class="carrousel-element-autor"><%= autores %>
                </h4>
            </div>
            <%}%>
        </div>
    </div>
</div>
<%}%>

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
