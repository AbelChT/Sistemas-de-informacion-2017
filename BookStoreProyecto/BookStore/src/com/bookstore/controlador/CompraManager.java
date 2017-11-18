package com.bookstore.controlador;

import com.bookstore.modelo.TiendaFacade;
import com.bookstore.modelo.VO.CompraVO;
import com.bookstore.modelo.VO.LibroVO;
import com.bookstore.modelo.VO.UsuarioVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CompraManager", value = {CommonConstants.compraLocation})
public class CompraManager extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter(CommonConstants.usernameParameterName);
        String libro = request.getParameter(CommonConstants.isbnParameterName);
        try {
            TiendaFacade.addCompra(usuario,libro);
        }catch (Exception e){

        }

        response.sendRedirect(CommonConstants.indexLocation);


    }
}
