package com.bookstore.controlador;

import com.bookstore.modelo.TiendaFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookManager", value = {CommonConstants.libroInfoLocation})
public class BookManager extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter(CommonConstants.comprarParameterName);
        String username = (String) request.getSession().getAttribute(CommonConstants.usernameParameterName);

        if(isbn != null && username != null) {
            try{
                TiendaFacade.addCompra(username, isbn);
            }catch (Exception e){

            }

        response.sendRedirect(CommonConstants.libroInfoLocation + "?" + CommonConstants.isbnParameterName + "=" + isbn);
        }

        request.getRequestDispatcher("/book.jsp").forward(request,response);

    }
}
