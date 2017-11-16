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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static com.bookstore.modelo.DAO.LibroDAO.encontrarDatosLibro;
import static com.bookstore.modelo.DAO.LibroDAO.insertarLibro;
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



    public static LibroVO listarLibroFacade(LibroVO libro) throws SQLException {
        Connection connection = GestorDeConexionesBD.getConnection();
        return encontrarDatosLibro(libro.getIsbn(), connection);
    }

    public static void main (String[] args) {
        UsuarioVO usuario = new UsuarioVO("abel1","maria2", "Lopez2", "M", new GregorianCalendar(12,12,12), "dos","fdsfsdfsdf",56433453L,"df");
        try{
            Connection connection = GestorDeConexionesBD.getConnection();
            insertarUsuario(usuario, connection);
            UsuarioVO user = leerUsuarioFacade("abel1");
            System.out.println(user.getNombre());
            Calendar c = Calendar.getInstance();
            LibroVO libro = new LibroVO("ISBN 2 2", "EDITORial", "titulo1", "pais publ", 99.99, 255, 99, "idioma", "des crip cion", "descrip cion cop", "ti tu lo", c, "images/img1.jpg");
            AutorVO autor = new AutorVO("Autor", "pais", "descripcion");
            List<AutorVO> listaAutor = new ArrayList<AutorVO>();
            listaAutor.add(autor);
            libro.setAutores(listaAutor);
            System.out.println(libro.getAutores().size());

                insertarLibro(libro, connection);

            System.out.println( listarLibroFacade(libro).getTitulo() );

            CompraVO compra = new CompraVO(user, libro, c, 99.99);
            insertarCompraLibroFachada(compra);
            List<CompraVO> lista = encontrarComprasRealizadasFachada(user.getNombreDeUsuario());
            for(CompraVO comp : lista){
                System.out.println("--" + comp.getLibro().getTitulo());
                //System.out.println("++" + comp.getLibro().getAutores().size());
            }
            ComentarioVO coment = new ComentarioVO(user, libro, c, "buen libro");
            List<ComentarioVO> listaCom = encontrarComentariosRealizadosFacade(user.getNombreDeUsuario());
            for(ComentarioVO com : listaCom){
                System.out.println(com.getComentario());
            }
        }catch (Exception e){
            e.printStackTrace(System.err);
        }
    }

    public static List<ComentarioVO> encontrarComentariosRealizadosFacade(String nombreDeUsuario) {
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return encontrarComentariosRealizados (nombreDeUsuario, connection);

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
