<%@ page import="javafx.util.Pair" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.controlador.CommonConstants" %>
<%@ page import="com.bookstore.modelo.TiendaFacade" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.bookstore.modelo.VO.*" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Abel
  Date: 14/11/2017
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    String titulo_pagina = "Book Information ";

    String username = (String) session.getAttribute(CommonConstants.usernameParameterName);

    List<Pair<String, String>> generos = new ArrayList<>();

    for (GeneroVO i : TiendaFacade.listarGeneros()){
        generos.add(new Pair<>( i.getNombre(),CommonConstants.browserLocation + "?" +CommonConstants.browserCategoryParameterName + "=" + i.getNombre()));
    }

    String isbn = request.getParameter(CommonConstants.isbnParameterName);

    LibroVO libro = null;

    try {
        libro = TiendaFacade.listarLibro(isbn);
    }catch (Exception e){
        response.sendRedirect(CommonConstants.errorLocation);
    }


    String EDITORIAL, TITULO, PAIS_DE_PUBLICACION, IDIOMA, DESCRIPCION, DESCRIPCION_CORTA, TITULO_ORIGINAL, IMAGEN, AUTOR;
    double PRECIO;
    Calendar FECHA_DE_PUBLICACION;
    Integer NUMERO_PAGINAS, NUMERO_DE_EDICION;
    EDITORIAL= libro.getEditorial();
    TITULO= libro.getTitulo();
    PAIS_DE_PUBLICACION= libro.getPaisDePublicacion();
    PRECIO= libro.getPrecio();
    NUMERO_PAGINAS= libro.getNumeroPaginas();
    NUMERO_DE_EDICION= libro.getNumeroDeEdicion();
    IDIOMA= libro.getIdioma();
    DESCRIPCION= libro.getDescricion();
    DESCRIPCION_CORTA= libro.getDescricionCorta();
    TITULO_ORIGINAL= libro.getTituloOriginal();
    FECHA_DE_PUBLICACION= libro.getFechaDePublicacion();
    IMAGEN= libro.getPathImagen();
    AUTOR ="";

    List<AutorVO> listaAutores = TiendaFacade.listarAutores(isbn);

    for (AutorVO autor: listaAutores ){
        AUTOR+= autor.getNombreCompleto();
        AUTOR+= "\n";
    }


   List<ComentarioVO> listaComentarios = TiendaFacade.listarComentarios(isbn);

    SimpleDateFormat formatter= new SimpleDateFormat("yyyy MM dd");
    String fecha_pub = formatter.format(FECHA_DE_PUBLICACION.getTime());

    session.setAttribute(CommonConstants.isbnParameterName,isbn);



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
    <div class="book">
        <h2> <%= TITULO %> </h2>
        <a href="<%= IMAGEN %>"><img src="<%= IMAGEN %>" ></a>
        <div class="ficha">
            <p>
                <b>Autor:</b>  <%= AUTOR %> <br>
                <b>Título original:</b> <%= TITULO_ORIGINAL%><br>
                <b>Editorial:</b> <%= EDITORIAL %><br>
                <b>País:</b>	<%= PAIS_DE_PUBLICACION %><br>
                <b>Fecha de publicación:</b> <%= fecha_pub %><br>
            </p>
        </div>
        <%if(username != null){%>
        <a href="<%= CommonConstants.libroInfoLocation+ "?" + CommonConstants.comprarParameterName + "=" + isbn %>" ><button class="btn" type="button">Comprar</button></a>
        <%}%>
        <div class="hr"><hr /></div>    <!--linea divisoria-->

        <div class="descrip">
            <p> <%= DESCRIPCION %> </p>
        </div>
    </div>

    <div class="comments">
        <div id="comment_form">

            <% for (ComentarioVO i: listaComentarios) {
                String user, fecha, comentario;
                UsuarioVO usuario = i.getUsuario();
                user = usuario.getNombreDeUsuario();
                Calendar date = i.getFecha();
                fecha = date.toString();
                comentario=i.getComentario();
            %>
            <div>
                <h4><%=user%></h4>
                <h4><%=fecha%></h4>
                <p><%=comentario%></p>
            </div>

            <% } %>

            <%if(username != null){%>
            <form class="form-horizontal" role="form" action="" method="get">

                <div class="form-group">
                    <label class="col-md-3 control-label">Comentario:</label>
                    <div class="col-md-8">
                        <input class="form-control" value="" type="text" name="<%= CommonConstants.commentParameterName%>" >
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label"></label>
                    <div class="col-md-8">
                        <input class="btn btn-primary" value="Comentar" type="submit">
                    </div>
                </div>
            </form>
            <%}%>


        </div>
    </div>

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

