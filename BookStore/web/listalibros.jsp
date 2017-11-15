<%@ page import="javafx.util.Pair" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.modelo.VO.LibroVO" %>
<%@ page import="static com.bookstore.modelo.TiendaFacade.listarLibrosNuevos" %><%--
  Created by IntelliJ IDEA.
  User: Abel
  Date: 14/11/2017
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String genero_actual = request.getParameter("gen");
    // Problemas de seguridad
    //http://localhost:8080/listalibros.jsp?gen=ciencia<br><br><br><br><br><br>
    if (genero_actual == null) genero_actual = "";

   // String genero_actual = "Ciencia";
    String titulo_pagina = "Genero "+ genero_actual;
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

    List<LibroVO> libros;

    libros = listarLibrosNuevos();



    int num_pages = 2;

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
            <li class="dropdown nav-item"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Todos los productos <span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <% for (Pair<String, String> genero : generos) {%>
                    <li><a href="<%= genero.getValue() %>"><%= genero.getKey() %>
                    </a></li>
                    <%}%>
                </ul>
            </li>

            <li class="nav-item"><a href="/">Inicio </a></li>
            <li class="nav-item"><a href="/?page=maspopulares">Más populares </a></li>
            <li class="nav-item"><a href="/?page=novedades">Novedades </a></li>

            <li class="nav-item"><a>Buscar</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="nav-item"><a href="<%= href_login%>"><%= label_login%>
            </a></li>
        </ul>
    </div>
</nav>

<div class="container-fluid">
    <h1><%= genero_actual%></h1>

    <br>

    <div class="row">
        <% for (LibroVO i : libros) { %>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="<%= i.getPathImagen() %>" style="height: 200px; width: 100%;">
                <div class="caption">
                    <h3><%= i.getTitulo()%>
                    </h3>
                    <p><%= i.getDescricionCorta()%>
                    </p>
                    <p><a href="libroextendido.jsp?=<%= i.getIsbn()%>" class="btn btn-primary" role="button">Ver</a></p>
                </div>
            </div>
        </div>

        <%}%>
    </div>

    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>

            <% for (int i = 0; i <= num_pages; ++i) {%>

            <li><a href="?page=<%= Integer.toString(i) %>"><%= Integer.toString(i) %>
            </a></li>
            <%}%>

            <li>
                <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>

</div>

</body>
</html>
