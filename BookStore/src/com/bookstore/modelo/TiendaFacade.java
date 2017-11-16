package com.bookstore.modelo;

import com.bookstore.modelo.DAO.GeneroDAO;
import com.bookstore.modelo.DAO.LibroDAO;
import com.bookstore.modelo.DAO.UsuarioDAO;
import com.bookstore.modelo.GestorDeConexiones.GestorDeConexionesBD;
import com.bookstore.modelo.VO.GeneroVO;
import com.bookstore.modelo.VO.LibroVO;
import com.bookstore.modelo.VO.UsuarioVO;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TiendaFacade {

    // Correcto
    public static boolean authenticate(String user, String pass) throws SQLException {

        Connection connection = null;
        boolean resultado = false;
        try {
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            resultado = UsuarioDAO.comprobarPassUsuario(user, pass, connection);
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally {
            connection.close();
        }
        return resultado;


    }

    public enum CategoriaLibrosListar {MAS_VENDIDOS, MAS_VISITADOS, NUEVOS}

    public enum RangosTiempoLibrosListar {ESTA_SEMANA, ESTE_MES}

    // TODO:La primera componente son los resultados y la segunda el numero de conjuntos de esos resultados que se podrían devolver
    public static List<LibroVO> listarLibrosRecomendados(UsuarioVO usuario, Integer num_libros) throws SQLException {

        return listarLibros(CategoriaLibrosListar.NUEVOS, null, num_libros);

    }

    // Correcto
    public static List<LibroVO> listarLibros(CategoriaLibrosListar c, RangosTiempoLibrosListar r, Integer num_libros) throws SQLException {

        Connection connection = null;
        List<LibroVO> listado = null;
        try {
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            Date fecha = null;
            if (r != null) {
                switch (r) {
                    case ESTE_MES:
                        LocalDate previous_month = LocalDate.now().minusMonths(1);
                        fecha = Date.valueOf(previous_month);
                        break;
                    case ESTA_SEMANA:
                        LocalDate previous_week = LocalDate.now().minusWeeks(1);
                        fecha = Date.valueOf(previous_week);
                }
            }

            switch (c) {
                case NUEVOS:
                    listado = LibroDAO.encontrarDatosLibrosNuevos(num_libros, connection);
                    break;
                case MAS_VENDIDOS:
                    listado = LibroDAO.encontrarDatosLibrosMasVendidos(fecha, num_libros, connection);
                    break;
                case MAS_VISITADOS:
                    listado = LibroDAO.encontrarDatosLibrosMasVisitados(fecha, num_libros, connection);
            }
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally {
            connection.close();
        }
        return listado;
    }

    // Correcto
    public static Pair<List<LibroVO>, Integer> listarLibros(GeneroVO genero, Integer cursor, Integer num_libros) throws SQLException {

        Connection connection = null;
        List<LibroVO> listado = null;
        int numlibros = 0;
        try {
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            listado = LibroDAO.encontrarDatosLibros(genero, cursor - 1, num_libros, connection);
            numlibros = LibroDAO.sizeEncontrarDatosLibros(genero, connection);
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally {
            connection.close();
        }
        return new Pair<>(listado, numlibros);

    }

    // Correcto
    public static Pair<List<LibroVO>, Integer> listarLibros(String nombre_libro, Integer cursor, Integer num_libros) throws SQLException {

        Connection connection = null;
        List<LibroVO> listado = null;
        Integer numlibros = 0;
        try {
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            listado = LibroDAO.encontrarDatosLibros(nombre_libro, cursor - 1, num_libros, connection);
            numlibros = LibroDAO.sizeEncontrarDatosLibros(nombre_libro, connection);
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally {
            connection.close();
        }
        return new Pair<>(listado, numlibros);
    }

    // Correcto
    public static List<GeneroVO> listarGeneros() throws SQLException {
        Connection connection = null;
        List<GeneroVO> generos = null;
        try {
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            generos = GeneroDAO.encontrarGeneros(connection);
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally {
            connection.close();
        }
        return generos;
    }
}
