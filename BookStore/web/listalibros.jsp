<%@ page import="javafx.util.Pair" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.modelo.VO.LibroVO" %>
<%@ page import="com.bookstore.controlador.CommonConstants" %>
<%@ page import="com.bookstore.modelo.TiendaFacade" %>
<%@ page import="com.bookstore.modelo.VO.GeneroVO" %>
<%@ page import="static com.bookstore.modelo.TiendaFacade.*" %>
<%--
  Created by IntelliJ IDEA.
  User: Abel
  Date: 14/11/2017
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String genero_actual = request.getParameter(CommonConstants.browserCategoryParameterName);

    String termino_busqueda = request.getParameter(CommonConstants.browserBookNameParameterName);

    String pag_numb = request.getParameter(CommonConstants.browserPageNumberParameterName);

    String titulo_pagina = null;

    List<LibroVO> libros = null;


    int num_pages = 0;
    int pagina_actual = 1;

    if (pag_numb != null) {
        try {
            pagina_actual = Integer.parseInt(pag_numb);
        } catch (Exception e) {
            response.sendRedirect("/error/");
        }
    }

    if (genero_actual != null) {
        titulo_pagina = "Genero " + genero_actual;
        Pair<List<LibroVO>, Integer> resultado_ = listarLibros(new GeneroVO(genero_actual), ((pagina_actual - 1) * 10) + 1, 10);
        libros = resultado_.getKey();
        num_pages = resultado_.getValue();

    } else if (termino_busqueda != null) {
        titulo_pagina = "Buscando: " + termino_busqueda;
        Pair<List<LibroVO>, Integer> resultado_ = listarLibros(termino_busqueda, ((pagina_actual - 1) * 10) + 1, 10);
        libros = resultado_.getKey();
        System.out.println("Numlibros = " + Integer.toString(libros.size()));
        num_pages = resultado_.getValue();

    } else {
        response.sendRedirect("/error/");
    }


    String username = (String) session.getAttribute(CommonConstants.usernameParameterName);


    List<Pair<String, String>> generos = new ArrayList<>();

    for (GeneroVO i : TiendaFacade.listarGeneros()) {
        generos.add(new Pair<>(i.getNombre(), CommonConstants.browserLocation + "?" + CommonConstants.browserCategoryParameterName + "=" + i.getNombre()));
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
            <li class="nav-item"><a
                    href="<%= CommonConstants.indexLocation + "?" + CommonConstants.pageStatusParameterName + "=" + CommonConstants.indexMasBuscadosPageStatus %>">Más
                populares </a></li>
            <li class="nav-item"><a
                    href="<%=  CommonConstants.indexLocation + "?" + CommonConstants.pageStatusParameterName + "=" + CommonConstants.indexNovedadesPageStatus %>">Novedades </a>
            </li>

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
    <h1><%= titulo_pagina%>
    </h1>

    <br>

    <div class="row">
        <% if (libros.size() == 0) { %>
        <h2> No se disponen de libros con las características solicitadas</h2>
        <%}%>

        <% for (LibroVO i : libros) { %>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="<%= i.getPathImagen() %>" style="height: 200px; width: 100%;">
                <div class="caption">
                    <h3><%= i.getTitulo()%>
                    </h3>
                    <p><%= i.getDescricionCorta()%>
                    </p>
                    <p>
                        <a href="<%= CommonConstants.libroInfoLocation + "?" + CommonConstants.isbnParameterName + "=" + i.getIsbn() %>"
                           class="btn btn-primary" role="button">Ver</a></p>
                </div>
            </div>
        </div>

        <%}%>
    </div>
    <% if (num_pages > 0) {%>
    <nav aria-label="Page navigation">
        <%
            String href_previus;
            if (pagina_actual - 1 > 1) {
                href_previus = CommonConstants.browserPageNumberParameterName + "=" + Integer.toString(pagina_actual - 1);
            } else {
                href_previus = "#";
            }

            String href_next;

            if (pagina_actual + 1 <= num_pages) {
                href_next = CommonConstants.browserPageNumberParameterName + "=" + Integer.toString(pagina_actual + 1);
            } else {
                href_next = "#";
            }

        %>

        <ul class="pagination">
            <li>
                <a href="<%= href_previus%>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>

            <% for (int i = 1; i <= num_pages; ++i) {%>

            <li>
                <a href="?<%= CommonConstants.browserPageNumberParameterName %>=<%= Integer.toString(i) %>"><%= Integer.toString(i) %>
                </a></li>
            <%}%>

            <li>
                <a href="<%= href_next%>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>

    <%}%>

</div>

</body>
</html>
