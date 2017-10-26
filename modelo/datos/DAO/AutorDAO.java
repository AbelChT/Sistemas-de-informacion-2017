package modelo.datos.DAO;

import modelo.datos.VO.AutorVO;
import modelo.datos.VO.LibroVO;


import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class AutorDAO {
    public static void insertarAutor (AutorVO autor, Connection connection) {
        try{
            /* Create "preparedStatement". */
            String queryString = "INSERT INTO autor " +
                    "(nombre_completo, pais_de_nacimiento, descripcion) " +
                    "VALUES (?,?,?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, autor.getNombreCompleto());
            preparedStatement.setString(2, autor.getPaisDeNacimiento());
            preparedStatement.setString(3, autor.getDescripcion());


            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) {
                throw new SQLException( "Problemas insertando autor!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static void actualizarAutor (AutorVO autor, Connection connection) {
        try{
            /* Create "preparedStatement". */
            String queryString = "UPDATE autor " +
                    "SET pais_de_nacimiento = ?, descripcion = ?" +
                    "WHERE nombre_completo = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);
            /* Fill "preparedStatement". */
            preparedStatement.setString(1, autor.getPaisDeNacimiento());
            preparedStatement.setString(2, autor.getDescripcion());
            preparedStatement.setString(3, autor.getNombreCompleto());

            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) {
                throw new SQLException( "Problemas actualizando autor!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static void eliminarAutor(AutorVO autor, Connection connection){
        try{
            /* Create "preparedStatement". */
            String queryString = "DELETE autor " +
                    "WHERE nombre_completo = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);
            /* Fill "preparedStatement". */
            preparedStatement.setString(1, autor.getNombreCompleto());

            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) {
                throw new SQLException( "Problemas eliminando autor!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static AutorVO encontrarDatosAutor (String nombre_completo_autor, Connection connection){
        AutorVO usuarioVO = null;
        try{
            /* Create "preparedStatement". */
            String queryString = "SELECT pais_de_nacimiento,  descripcion " +
                    "FROM autor WHERE  nombre_completo = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, nombre_completo_autor);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.first()) {
                throw new SQLException( "Autor no encontrado!!!!");
            }

            /* Execute query. */
            String pais_de_nacimiento = resultSet.getString(1);
            String descripcion = resultSet.getString(2);

            usuarioVO = new AutorVO (nombre_completo_autor, pais_de_nacimiento,
                    descripcion);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return usuarioVO;
    }

    public static List<AutorVO>  encontrarDatosAutor (Connection connection){
        List<AutorVO> resultado = new ArrayList<>();
        try{
            AutorVO autorVO = null;
            /* Create "preparedStatement". */
            String queryString = "SELECT nombre_completo, pais_de_nacimiento, descripcion " +
                    "FROM autor";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	/* Execute query. */
                String nombre_completo = resultSet.getString(1);
                String pais_de_nacimiento = resultSet.getString(2);
                String descripcion = resultSet.getString(3);

                System.out.println("Leyendo autor "+ nombre_completo);
                autorVO = new AutorVO (nombre_completo, pais_de_nacimiento,
                        descripcion);
                resultado.add(autorVO);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Aquí estoy en el DAO y da Error al listar autor");

        }
        return resultado;
    }

    public static List<LibroVO> encontrarLibrosAutor (String nombre_completo_autor, Connection connection){
        List<LibroVO> list = new ArrayList<>();
        try{
            /* Create "preparedStatement". */
            String queryString = "SELECT LIBRO.ISBN ,LIBRO.EDITORIAL, LIBRO.TITULO, LIBRO.PAIS_DE_PUBLICACION, LIBRO.PRECIO, LIBRO.NUMERO_PAGINAS, LIBRO.NUMERO_DE_EDICION, LIBRO.IDIOMA, LIBRO.DESCRICION, LIBRO.DESCRICION_CORTA, LIBRO.TITULO_ORIGINAL, LIBRO.FECHA_DE_PUBLICACION " +
                    "FROM ESCRITO_POR "+
                    "INNER JOIN LIBRO ON libro.isbn = ESCRITO_POR.libro "+
                    "WHERE ESCRITO_POR.AUTOR = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, nombre_completo_autor);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	/* Execute query. */
                String isbn  = resultSet.getString(1);

                String editorial = resultSet.getString(2);
                String titulo = resultSet.getString(3);
                String pais_de_publicacion = resultSet.getString(4);
                Double precio = resultSet.getDouble(5);
                Integer numero_paginas = resultSet.getInt(6);
                Integer numero_edicion = resultSet.getInt(7);
                String idioma = resultSet.getString(8);
                String descripcion = resultSet.getString(9);
                String descripcion_corta = resultSet.getString(10);
                String titulo_original = resultSet.getString(11);

                Date fecha_de_publicacion_date = resultSet.getDate(12);
                Calendar fecha_de_publicacion;
                fecha_de_publicacion = new GregorianCalendar();

                if (fecha_de_publicacion_date != null) {
                    fecha_de_publicacion.setTime(fecha_de_publicacion_date);
                }

                LibroVO libroVO = new LibroVO(isbn, editorial, titulo, pais_de_publicacion, precio, numero_paginas, numero_edicion, idioma,
                        descripcion, descripcion_corta, titulo_original, fecha_de_publicacion);

                list.add(libroVO);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return list;
    }

    public boolean existeAutor(String nombre_completo, Connection connection){
        return encontrarDatosAutor(nombre_completo,connection)!=null;
    }

}
