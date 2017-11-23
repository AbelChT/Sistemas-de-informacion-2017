<%@ page import="com.bookstore.modelo.VO.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="static com.bookstore.modelo.TiendaFacade.leerUsuarioFacade" %>
<%@ page import="com.bookstore.controlador.CommonConstants" %>
<%@ page import="javafx.util.Pair" %>
<%@ page import="com.bookstore.modelo.TiendaFacade" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Editar Información Personal</title>
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
    //UsuarioVO user = leerUsuarioFacade("userprueba");
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
                    String errorNombre = "";
                    String errorApellidos = "";
                    String errorDireccion = "";
                    HashMap errores = (HashMap) request.getAttribute("errores");
                    if (errores != null){
                        if (errores.get(CommonConstants.nameProfileParameterName)!= null){
                            errorNombre = (String)errores.get(CommonConstants.nameProfileParameterName); %>
                            <div class="alert alert-danger">
                                <strong>Error</strong> <%=errorNombre%>
                            </div>
                        <%
                        }
                        if (errores.get(CommonConstants.surnameProfileParameterName)!=null) {
                            errorApellidos = (String) errores.get(CommonConstants.surnameProfileParameterName);%>
                            <div class="alert alert-danger">
                                <strong>Error</strong> <%=errorApellidos%>
                            </div>
                            <%
                        }
                            if (errores.get(CommonConstants.dirProfileParameterName)!=null) {
                                errorDireccion = (String) errores.get(CommonConstants.dirProfileParameterName);%>
                                <div class="alert alert-danger">
                                    <strong>Error</strong> <%=errorDireccion%>
                                </div>
                            <%
                        }
                    }

                                SimpleDateFormat formatter1= new SimpleDateFormat("yyyy MM dd");
                                String fecha = formatter1.format(user.getFechaDeNacimiento().getTime());

                %>

                <!-- edit form column -->
                <div class="col-md-9 personal-info">

                    <h3>Información personal</h3>
                    <hr>


                    <form action="/EditarInformacionPersonal" method="post">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Nombre:</label>
                            <div class="col-lg-8">
                                <input class="form-control" value="<%=user.getNombre()%>" type="text" name="<%=CommonConstants.fullNameProfileParameterName%>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Apellidos:</label>
                            <div class="col-lg-8">
                                <input class="form-control" value="<%=user.getApellidos()%>" type="text" name="<%=CommonConstants.surnameProfileParameterName%>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Fecha nacimiento:</label>
                            <div class="col-lg-8">
                                <input class="form-control" value="<%=fecha%>" type="datetime-local" name="bdaytime">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Localidad:</label>
                            <div class="col-lg-8">
                                <input class="form-control" value="<%=user.getDireccionPostal()%>" type="text" name="<%=CommonConstants.dirProfileParameterName%>">
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-md-3 control-label"></label>
                            <div class="col-md-8">
                                <input class="btn btn-primary" value="Guardar cambios" type="submit"></a>
                                <span></span>
                                <a href="Perfil.html"><input class="btn btn-default" value="Cancelar" type="reset"></a>
                            </div>
                        </div>
                    </form>
                    </form>
                </div>


            </div><!--/.col-xs-12.col-sm-9-->

            <div class="col-xs-6 col-sm-3 col-sm-pull-9 sidebar-offcanvas" id="sidebar">
                <div class="list-group">
                    <a href="<%=CommonConstants.profileAccountEdit%>" class="list-group-item active">Información personal</a>
                    <a href="<%=CommonConstants.profilePersonalEdit%>" class="list-group-item">Cuenta</a>
                </div>
            </div><!--/.sidebar-offcanvas-->
        </div><!--/row-->

        <hr>

    </div><!--/.container-->


</body>
</html>
