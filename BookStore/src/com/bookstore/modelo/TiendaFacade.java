package com.bookstore.modelo;



import com.bookstore.modelo.DAO.LibroDAO;
import com.bookstore.modelo.GestorDeConexiones.GestorDeConexionesBD;
import com.bookstore.modelo.VO.LibroVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TiendaFacade {

    public static List<LibroVO> listarLibrosMasPopulares() throws SQLException {
        Connection connection = null;
        List<LibroVO> listado = null;
        try{
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            listado = LibroDAO.encontrarDatosLibro(connection);
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");
            List<LibroVO> listado2 = new ArrayList<>();
            int counter = 0;
            for (LibroVO i : listado){
                ++counter;
                listado2.add(i);
                if(counter > 5) break;
            }
            listado = listado2;

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }
        return listado;

    }

    public static List<LibroVO> listarLibrosMasVendidos() throws SQLException {
        Connection connection = null;
        List<LibroVO> listado = null;
        try{
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            listado = LibroDAO.encontrarDatosLibro(connection);
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");
            List<LibroVO> listado2 = new ArrayList<>();
            int counter = 0;
            for (LibroVO i : listado){
                ++counter;
                listado2.add(i);
                if(counter > 5) break;
            }
            listado = listado2;

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }
        return listado;

    }

    public static List<LibroVO> listarLibrosNovedades() throws SQLException {
        Connection connection = null;
        List<LibroVO> listado = null;
        try{
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            listado = LibroDAO.encontrarDatosLibro(connection);
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");
            List<LibroVO> listado2 = new ArrayList<>();
            int counter = 0;
            for (LibroVO i : listado){
                ++counter;
                listado2.add(i);
                if(counter > 5) break;
            }
            listado = listado2;

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }
        return listado;

    }


    public static List<LibroVO> listarLibrosNuevos() throws SQLException {
        Connection connection = null;
        List<LibroVO> listado = null;
        try{
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            listado = LibroDAO.encontrarDatosLibro(connection);
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");
            List<LibroVO> listado2 = new ArrayList<>();
            int counter = 0;
            for (LibroVO i : listado){
                ++counter;
                listado2.add(i);
                if(counter > 5) break;
            }
            listado = listado2;

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }
        return listado;

    }

    public static List<LibroVO> listarLibrosAutoresNuevos() throws SQLException {
        Connection connection = null;
        List<LibroVO> listado = null;
        try{
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            listado = LibroDAO.encontrarDatosLibro(connection);
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");
            List<LibroVO> listado2 = new ArrayList<>();
            int counter = 0;
            for (LibroVO i : listado){
                ++counter;
                listado2.add(i);
                if(counter > 5) break;
            }
            listado = listado2;

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }
        return listado;

    }

    public static List<LibroVO> listarLibrosMasPopularesEstaSemana() throws SQLException {
        Connection connection = null;
        List<LibroVO> listado = null;
        try{
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            listado = LibroDAO.encontrarDatosLibro(connection);
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");
            List<LibroVO> listado2 = new ArrayList<>();
            int counter = 0;
            for (LibroVO i : listado){
                ++counter;
                listado2.add(i);
                if(counter > 5) break;
            }
            listado = listado2;

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }
        return listado;

    }

    public static List<LibroVO> listarLibrosMasVendidosEstaSemana() throws SQLException {
        Connection connection = null;
        List<LibroVO> listado = null;
        try{
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            listado = LibroDAO.encontrarDatosLibro(connection);
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");
            List<LibroVO> listado2 = new ArrayList<>();
            int counter = 0;
            for (LibroVO i : listado){
                ++counter;
                listado2.add(i);
                if(counter > 5) break;
            }
            listado = listado2;

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }
        return listado;

    }

    public static boolean authentication_test(String user, String pass){
        return user.equals("abel");
    }
}