<%@ page import="static com.bookstore.modelo.TiendaFacade.listarLibrosMasPopulares" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.modelo.VO.LibroVO" %>
<%@ page import="com.bookstore.modelo.VO.AutorVO" %>
<%@ page import="static com.bookstore.modelo.TiendaFacade.listarLibrosMasVendidos" %>
<%@ page import="static com.bookstore.modelo.TiendaFacade.listarLibrosNovedades" %>
<%@ page import="static com.bookstore.modelo.TiendaFacade.*" %>
<%@ page import="javafx.util.Pair" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bookstore.controlador.CommonConstants" %>
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
    try {
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
    } catch (Exception e) {
        response.sendRedirect("/error/");
    }

    String href_todos_los_productos = "listalibros.jsp";

    List<Pair<String, String>> generos = new ArrayList<>();
    generos.add(new Pair<>("Ciencia", href_todos_los_productos + "?gen=" + "ciencia"));
    generos.add(new Pair<>("Ficción y literatura", href_todos_los_productos + "?gen=" + "ciencia"));
    generos.add(new Pair<>("Finanzas e inversión", href_todos_los_productos + "?gen=" + "ciencia"));
    generos.add(new Pair<>("Historia", href_todos_los_productos + "?gen=" + "ciencia"));
    generos.add(new Pair<>("Informática y tecnología", href_todos_los_productos + "?gen=" + "ciencia"));
    generos.add(new Pair<>("Infantiles", href_todos_los_productos + "?gen=" + "ciencia"));
    generos.add(new Pair<>("Psicología", href_todos_los_productos + "?gen=" + "ciencia"));


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

            <li class="nav-item"><a>Buscar</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">

            <%
                String username = (String) session.getAttribute(CommonConstants.usernameParameterName);
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

<script type="text/javascript" src="/js/script.js"></script>

</body>
</html>
