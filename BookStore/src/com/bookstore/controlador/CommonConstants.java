package com.bookstore.controlador;

public class CommonConstants {

    // Common
    public final static String pageStatusParameterName = "pagestatus";
    public final static String loginLocation = "/login/";
    public final static String registerLocation = "/register/";
    public final static String indexLocation = "/";
    public final static String errorLocation = "/error/";
    public final static String profileLocation = "/profile/";
    public final static String profileCommentLocation = "/comments/";
    public final static String profileLibraryLocation = "/library/";
    public final static String profileAccountEdit = "/EditarInformacionPersonalManager/";
    public final static String profilePersonalEdit = "/EditarCuentaManager/";
    public final static String logoutLocation = "/logout/";
    public final static String browserLocation = "/browser/";
    public final static String libroInfoLocation = "/libro/";
    public final static String compraLocation = "/compra/";
    public final static String comentaryManagerLocation = "/commentary/";
    public final static String valoracionManagerLocation = "/valoracionmanager/";

    public final static String usernameParameterName = "username";
    public final static String passwordParameterName = "password";
    public final static String isbnParameterName = "isbn";


    // Page comunication constast

    // Login Page
    public final static String loginAuthFailedPageStatus = "error";

    // Register Page
    public final static String dayParameterName = "day";
    public final static String locationParameterName = "location";
    public final static String registerFailParameterName = "error";

    // Index Page
    public final static String indexMasBuscadosPageStatus = "masBuscados";
    public final static String indexNovedadesPageStatus = "novedades";

    // Browser page
    public final static String browserBookNameParameterName = "nombreLibro";
    public final static String browserCategoryParameterName = "categoria";
    public final static String browserPageNumberParameterName = "pageNumber";

    // Profile Edit Page
    public final static String emailProfileParameterName = "mail";
    public final static String password1ParameterName = "password1";
    public final static String password2ParameterName = "password2";
    public final static String nameProfileParameterName = "nombre";
    public final static String surnameProfileParameterName = "apellidos";
    public final static String dirProfileParameterName = "direccion";
    public final static String fullNameProfileParameterName = "nombreCompleto";

    // Book page
    public final static String commentParameterName = "comentario";
    public final static String comprarParameterName = "comprar";

    // Comentary manager
    public final static String comentaryIsbmParameterName = "isbnComentary";

    //Valoracion manager
    public final static String estrellasParameterName = "estrellas";

}
