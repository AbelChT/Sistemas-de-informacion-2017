package com.bookstore.controlador;

import com.bookstore.modelo.TiendaFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.bookstore.controlador.CommonConstants.*;

@WebServlet(name = "LoginManager", value= {CommonConstants.loginLocation})
public class LoginManager extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username= request.getParameter(CommonConstants.usernameParameterName);
        String password= request.getParameter(CommonConstants.passwordParameterName);

        if(username != null && password !=null && TiendaFacade.authentication_test(username, password)){
            request.getSession(true).setAttribute(CommonConstants.usernameParameterName,username);
            response.sendRedirect(CommonConstants.profileLocation);

        }else{
            request.setAttribute(CommonConstants.pageStatusParameterName,CommonConstants.loginAuthFailedPageStatus);
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Cuando se abre la sireccion por primera vez
        request.getRequestDispatcher("/login.jsp").forward(request,response);
    }
}
