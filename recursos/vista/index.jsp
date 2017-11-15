<%@ page import="static com.bookstore.TiendaFacade.listarLibrosMasPopulares" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.datos.VO.LibroVO" %>
<%@ page import="com.bookstore.datos.VO.AutorVO" %>
<%@ page import="static com.bookstore.TiendaFacade.listarLibrosMasVendidos" %>
<%@ page import="static com.bookstore.TiendaFacade.listarLibrosNovedades" %>
<%@ page import="static com.bookstore.TiendaFacade.*" %>
<%@ page import="javafx.util.Pair" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by Abel
  Date: 13/11/2017
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String titulo_pagina = null;

    List<Pair<String, List<LibroVO>>> carruseles = new ArrayList<>();

    String pagina = request.getParameter("page");
    if (pagina == null) pagina = "";
    switch (pagina) {
        case "maspopulares":
            titulo_pagina = "Libros más populares";
            carruseles.add(new Pair<>("Más vendidos esta semana", listarLibrosMasVendidosEstaSemana()));
            carruseles.add(new Pair<>("Más visitados esta semana", listarLibrosMasPopularesEstaSemana()));
            carruseles.add(new Pair<>("Más vendidos", listarLibrosMasVendidos()));
            break;
        case "novedades":
            titulo_pagina = "Novedades";
            carruseles.add(new Pair<>("Nuevos", listarLibrosNuevos()));
            carruseles.add(new Pair<>("Autores nuevos", listarLibrosAutoresNuevos()));
            carruseles.add(new Pair<>("Más vendidos", listarLibrosMasVendidos()));
            break;
        default:
            titulo_pagina = "Inicio";
            carruseles.add(new Pair<>("Más vendidos", listarLibrosMasVendidos()));
            carruseles.add(new Pair<>("Populares", listarLibrosMasPopulares()));
            carruseles.add(new Pair<>("Novedades", listarLibrosNovedades()));
    }

    String label_login = "Acceder";
    String href_login = "Login.html";
%>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= titulo_pagina%>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>

    <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="js/slick-1.8.0/slick/slick.min.js"></script>

    <link rel="stylesheet" type="text/css" href="js/slick-1.8.0/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="js/slick-1.8.0/slick/slick-theme.css"/>
    <link rel="stylesheet" type="text/css" href="css/mainpage.css"/>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-transp font-roboto">
    <div class="navbar-collapse">
        <ul class="nav navbar-nav">
            <li class="dropdown nav-item"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Géneros <span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Ciencia</a></li>
                    <li><a href="#">Ficción y literatura</a></li>
                    <li><a href="#">Finanzas e inversión</a></li>
                    <li><a href="#">Historia</a></li>
                    <li><a href="#">Informática y tecnología</a></li>
                    <li><a href="#">Infantiles</a></li>
                    <li><a href="#">Psicología</a></li>
                </ul>
            </li>

            <li class="nav-item"><a href="/">Inicio </a></li>
            <li class="nav-item"><a href="?page=maspopulares">Más populares </a></li>
            <li class="nav-item"><a href="?page=novedades">Novedades </a></li>

            <li class="nav-item"><a>Buscar</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="nav-item"><a href="<%= href_login%>"><%= label_login%>
            </a></li>
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
                <a href="LibroExtendido.html<%= i.getIsbn() %>"><img class="carrousel-element-image"
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

<script type="text/javascript" src="js/script.js"></script>

</body>
</html>
