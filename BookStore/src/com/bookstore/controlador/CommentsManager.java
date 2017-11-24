package com.bookstore.controlador;

import com.bookstore.modelo.TiendaFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CommentsManager", value = {CommonConstants.comentaryManagerLocation})
public class CommentsManager extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = (String) request.getSession().getAttribute(CommonConstants.usernameParameterName);
        String comentario = request.getParameter(CommonConstants.commentParameterName);
        String libro = (String) request.getSession().getAttribute(CommonConstants.comentaryIsbmParameterName);

        try {
            TiendaFacade.addComentario(usuario,comentario,libro);
        }catch (Exception e){

        }

        response.sendRedirect(CommonConstants.libroInfoLocation + "?" + CommonConstants.isbnParameterName + "=" + libro);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
