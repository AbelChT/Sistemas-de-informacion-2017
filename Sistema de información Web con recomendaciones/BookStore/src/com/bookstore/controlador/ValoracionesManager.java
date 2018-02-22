package com.bookstore.controlador;

import com.bookstore.modelo.TiendaFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ValoracionesManager", value = {CommonConstants.valoracionManagerLocation})
public class ValoracionesManager extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = (String) request.getSession().getAttribute(CommonConstants.usernameParameterName);
        String valoracion = request.getParameter(CommonConstants.estrellasParameterName);
        String libro = (String) request.getSession().getAttribute(CommonConstants.comentaryIsbmParameterName);
        System.out.println("-----------------valoracion-------------" + valoracion);

        try {
            TiendaFacade.addValoracion(usuario,valoracion,libro);
        }catch (Exception e){

        }

        response.sendRedirect(CommonConstants.libroInfoLocation + "?" + CommonConstants.isbnParameterName + "=" + libro);

    }
}
