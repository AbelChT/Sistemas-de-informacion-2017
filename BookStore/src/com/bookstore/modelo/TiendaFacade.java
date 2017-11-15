package com.bookstore.modelo;



import com.bookstore.modelo.DAO.LibroDAO;
import com.bookstore.modelo.GestorDeConexiones.GestorDeConexionesBD;
import com.bookstore.modelo.VO.GeneroVO;
import com.bookstore.modelo.VO.LibroVO;
import com.bookstore.modelo.VO.UsuarioVO;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TiendaFacade {

     public static boolean authenticate(String user, String pass){
        return user.equals("abel");
    }

    public enum CategoriaLibrosListar{RECOMENDACIONES, MAS_VENDIDOS, MAS_VISITADOS, NUEVOS}
    public enum RangosTiempoLibrosListar{ESTA_SEMANA, ESTE_MES}

    //La primera componente son los resultados y la segunda el numero de conjuntos de esos resultados que se podrían devolver
    public static List<LibroVO> listarLibros (CategoriaLibrosListar c, RangosTiempoLibrosListar r, UsuarioVO usuario, Integer num_libros) throws SQLException{

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

    //La primera componente son los resultados y la segunda el numero de conjuntos de esos resultados que se podrían devolver
    public static List<LibroVO> listarLibros (CategoriaLibrosListar c, RangosTiempoLibrosListar r, Integer num_libros) throws SQLException{

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

    //La primera componente son los resultados y la segunda el numero de conjuntos de esos resultados que se podrían devolver
    public static Pair<List<LibroVO>,Integer> listarLibros (GeneroVO genero, Integer cursor, Integer num_libros) throws SQLException{

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
        return new Pair<>(listado,3);

    }

    //La primera componente son los resultados y la segunda el numero de conjuntos de esos resultados que se podrían devolver
    public static Pair<List<LibroVO>,Integer> listarLibros (String nombre_libro, Integer cursor, Integer num_libros) throws SQLException{

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
        return new Pair<>(listado,3);
    }

    public static List<GeneroVO> listarGeneros (){
        List<GeneroVO> generos = new ArrayList<>();
        generos.add(new GeneroVO("Ciencia"));
        generos.add(new GeneroVO("Historia"));
        generos.add(new GeneroVO("Psicología"));
        /*
        generos.add(new Pair<>("Ciencia", href_todos_los_productos + "?gen=" + "ciencia"));
        generos.add(new Pair<>("Ficción y literatura", href_todos_los_productos + "?gen=" + "ciencia"));
        generos.add(new Pair<>("Finanzas e inversión", href_todos_los_productos + "?gen=" + "ciencia"));
        generos.add(new Pair<>("Historia", href_todos_los_productos + "?gen=" + "ciencia"));
        generos.add(new Pair<>("Informática y tecnología", href_todos_los_productos + "?gen=" + "ciencia"));
        generos.add(new Pair<>("Infantiles", href_todos_los_productos + "?gen=" + "ciencia"));
        generos.add(new Pair<>("Psicología", href_todos_los_productos + "?gen=" + "ciencia"));*/
        return generos;
    }
}