package com.bookstore.modelo;

import com.bookstore.modelo.DAO.GeneroDAO;
import com.bookstore.modelo.DAO.LibroDAO;
import com.bookstore.modelo.DAO.UsuarioDAO;
import com.bookstore.modelo.GestorDeConexiones.GestorDeConexionesBD;
import com.bookstore.modelo.VO.*;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static com.bookstore.modelo.DAO.UsuarioDAO.*;

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
        Connection connection = null;
        List<LibroVO> listado = null;
        try {
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            listado = LibroDAO.encontrarDatosLibrosRecomendados(usuario.getNombreDeUsuario(), 10,connection );
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

    //TODO
    public static LibroVO listarLibro(String ISBN) throws SQLException {
        Connection connection = null;
        LibroVO libro = null;
        try {
            connection = GestorDeConexionesBD.getConnection();

            libro = LibroDAO.encontrarDatosLibro(ISBN, connection);
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }
        return libro;

    }


    public static List<ComentarioVO> listarComentarios(String ISBN)  throws SQLException {
        Connection connection = null;
        List<ComentarioVO> lista = null;
        try {
            connection = GestorDeConexionesBD.getConnection();

            lista = LibroDAO.encontrarComentariosLibro(ISBN,connection);
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }
        return lista;

    }

    public static void addCompra(String usuario, String ISBN) throws SQLException{
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();

            LibroDAO.insertarCompra(usuario ,ISBN,connection);
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }

    }

    public static void addComentario(String usuario, String comentario, String ISBN) throws SQLException{

        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();

            LibroDAO.insertarComentario(usuario ,ISBN, comentario, connection);
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }

    }

    public static List<AutorVO> listarAutores(String ISBN) throws SQLException {
        Connection connection = null;
        List<AutorVO> lista = null;
        try {
            connection = GestorDeConexionesBD.getConnection();

            lista = LibroDAO.encontrarAutoresLibro(ISBN,connection);
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }
        return lista;
    }


    public static UsuarioVO leerUsuarioFacade(String nombre) throws SQLException {
        Connection connection = null;
        UsuarioVO user = null;
        try {
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            user = UsuarioDAO.encontrarDatosUsuario(nombre, connection);
            System.out.println("En la fachada despues de encontrar en  a llamar al DAO");

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally {
            connection.close();
        }
        return user;
    }
    public static List<ComentarioVO> encontrarComentariosRealizadosFacade(String nombreDeUsuario) {
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return encontrarComentariosRealizados(nombreDeUsuario, connection);

    }

    public static List<CompraVO> encontrarComprasRealizadasFachada(String nombre) {
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return encontrarComprasRealizadas(nombre, connection);
    }

    public static void insertarCompraLibroFachada(CompraVO compra) {
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertarCompraLibro(compra, connection);
    }

    public static void actualizarUsuarioFacade(UsuarioVO user) {
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        actualizarUsuario(user, connection);
    }

}
