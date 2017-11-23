package com.bookstore.controlador;

import com.bookstore.modelo.TiendaFacade;
import com.bookstore.modelo.VO.UsuarioVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;

import static com.bookstore.modelo.TiendaFacade.*;

@WebServlet(name = "RegisterManager", value= {CommonConstants.registerLocation})
public class RegisterManager extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter(CommonConstants.usernameParameterName);
        String password1 = request.getParameter(CommonConstants.password1ParameterName);
        String password2 = request.getParameter(CommonConstants.password2ParameterName);
        String email = request.getParameter(CommonConstants.emailProfileParameterName);
        String nombreCompleto = request.getParameter(CommonConstants.fullNameProfileParameterName);
        String apellidos = request.getParameter(CommonConstants.surnameProfileParameterName);
        String nacimiento = request.getParameter(CommonConstants.dayParameterName);
        String localidad = request.getParameter(CommonConstants.locationParameterName);

        // ERRORES -----------------------------------------------------------------------------------------------------
        response.getWriter().append("Served at: ").append(request.getContextPath());

        boolean errores = false;
        HashMap tablaErrores = new HashMap();
        System.out.println("He llegado aquí con el usuario logi");

        String errorNombre = "";
        if(username != null){
            if(username.trim().equals("")){
                errorNombre = "El nombre de usuario no puede ser vacio";
                tablaErrores.put(CommonConstants.usernameParameterName, errorNombre);
                errores = true;
            }
            try {
                if(leerUsuarioFacade(username) != null){
                    errorNombre = "El nombre de usuario ya esta en uso";
                    tablaErrores.put(CommonConstants.usernameParameterName, errorNombre);
                    errores = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //-----------------------------
        String errorMail = "";
        if(email != null) {
            if (email.trim().equals(new String(""))) {
                System.out.println("error email vacio ------------");
                errorMail = "El email no puede ser vacio";
                tablaErrores.put(CommonConstants.emailProfileParameterName, errorMail);
                errores = true;
            }
            if ( existeEmailFacade(email) ) {
                System.out.println("error email repet ------------");
                errorMail = "El email introducido ya esta en uso";
                tablaErrores.put(CommonConstants.emailProfileParameterName, errorMail);
                errores = true;
            }
        }
        //-----------------------------
        String errorPassword = "";
        if(password1 != null && password2!=null) {
            if (!password1.trim().equals(new String("")) || !password2.trim().equals(new String(""))) {
                if (!password1.trim().equals(password2.trim())) {
                    System.out.println("error pass difer ------------");
                    errorPassword = "Las contraseñas no coinciden";
                    tablaErrores.put("password", errorPassword);
                    errores = true;
                } else {
                    if (password1.trim().length() < 5) {
                        System.out.println("error pass corta ------------");
                        errorPassword = "Las contraseñas deben tener 5 caracteres como mínimo";
                        tablaErrores.put(CommonConstants.passwordParameterName, errorPassword);
                        errores = true;
                    }
                }
            }
        }

        String errorNombreCompleto = "";
        if(nombreCompleto != null) {
            if (nombreCompleto.trim().equals(new String(""))) {
                System.out.println("error nombre ------------");
                errorNombreCompleto = "El nombre no puede ser vacio";
                tablaErrores.put(CommonConstants.fullNameProfileParameterName, errorNombreCompleto);
                errores = true;
            }
        }

        String errorApellidos = "";
        if(apellidos != null) {
            if (apellidos.trim().equals(new String(""))) {
                System.out.println("error apellidos ------------");
                errorApellidos = "El apellido no puede ser vacio";
                tablaErrores.put(CommonConstants.surnameProfileParameterName, errorApellidos);
                errores = true;
            }
        }

        String errorLocalidad = "";
        if(localidad != null) {
            if (localidad.trim().equals(new String(""))) {
                errorLocalidad = "La localidad no puede ser vacia";
                System.out.println("error localidada ------------");
                tablaErrores.put(CommonConstants.locationParameterName, errorLocalidad);
                errores = true;
            }
        }

        System.out.println("Despues de leer parametros");
        if (errores==false){
            try{
                System.out.println("Voy a llamar a la fachada y métdo Actualziar Usuario de ");
                UsuarioVO user = new UsuarioVO(username, password1, nombreCompleto, apellidos, Calendar.getInstance(), email, localidad, new Long(666666666), "XXXX");
                insertarUsuarioFacade(user);
                if(TiendaFacade.authenticate(username, password1)){
                    request.getSession(true).setAttribute(CommonConstants.usernameParameterName,username);
                    response.sendRedirect(CommonConstants.profileLocation);

                }else{
                    request.setAttribute(CommonConstants.pageStatusParameterName,CommonConstants.loginAuthFailedPageStatus);
                    System.out.println("error login");
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                }
            }catch (Exception e){
                e.printStackTrace(System.err);
                System.out.println("Problemas con el VN");
                response.sendRedirect("errorInterno.html");
            }

        }else{
            request.setAttribute(CommonConstants.registerFailParameterName, tablaErrores);
            RequestDispatcher dispacher = request.getRequestDispatcher("/register.jsp");
            dispacher.forward(request, response);
        }

        // ERRORES -----------------------------------------------------------------------------------------------------



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Cuando se abre la sireccion por primera vez
        request.getRequestDispatcher("/register.jsp").forward(request,response);
    }
}
