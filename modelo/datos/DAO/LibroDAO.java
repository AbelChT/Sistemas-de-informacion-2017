package modelo.datos.DAO;

import modelo.datos.VO.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class LibroDAO {
    public void insertarLibro (LibroVO libro, Connection connection) {
        try{
            /* Create "preparedStatement". */
            String queryString = "INSERT INTO LIBRO " +
                    "(ISBN, EDITORIAL, TITULO, PAIS_DE_PUBLICACION, PRECIO, NUMERO_PAGINAS, NUMERO_DE_EDICION, IDIOMA, DESCRICION, DESCRICION_CORTA, TITULO_ORIGINAL, FECHA_DE_PUBLICACION) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, libro.getIsbn());
            preparedStatement.setString(2, libro.getEditorial());
            preparedStatement.setString(3, libro.getTitulo());
            preparedStatement.setString(4, libro.getPaisDePublicacion());
            preparedStatement.setDouble(5, libro.getPrecio());
            preparedStatement.setInt(6, libro.getNumeroPaginas());
            preparedStatement.setInt(7, libro.getNumeroDeEdicion());
            preparedStatement.setString(8, libro.getIdioma());
            preparedStatement.setString(9, libro.getDescricion());
            preparedStatement.setString(10, libro.getDescricionCorta());
            preparedStatement.setString(11, libro.getTituloOriginal());
            preparedStatement.setDate(12, new java.sql.Date(libro.getFechaDePublicacion().getTimeInMillis()));


            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) {
                throw new SQLException( "Problemas insertando libro!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void insertarAutoresLibro (LibroVO libro, Connection connection) {
        /*TODO*/
    }

    public void insertarGenerosLibro (LibroVO libro, Connection connection) {
        /*TODO*/
    }


    public void insertarComentarioLibro (ComentarioVO comentarioVO, Connection connection) {
        /*TODO*/
    }

    public void insertarValoracionLibro (ValoracionVO valoracionVO, Connection connection) {
        /*TODO*/
    }

    public void actualizarLibro (LibroVO libro, Connection connection) {
        try{
            /* Create "preparedStatement". */
            String queryString = "UPDATE LIBRO " +
                    "SET EDITORIAL=?,  TITULO=?, PAIS_DE_PUBLICACION=?, PRECIO=?, NUMERO_PAGINAS=?, NUMERO_DE_EDICION=?, IDIOMA=?, DESCRICION=?, DESCRICION_CORTA=?, TITULO_ORIGINAL=?, FECHA_DE_PUBLICACION=?" +
                    "WHERE ISBN = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, libro.getEditorial());
            preparedStatement.setString(2, libro.getTitulo());
            preparedStatement.setString(3, libro.getPaisDePublicacion());
            preparedStatement.setDouble(4, libro.getPrecio());
            preparedStatement.setInt(5, libro.getNumeroPaginas());
            preparedStatement.setInt(6, libro.getNumeroDeEdicion());
            preparedStatement.setString(7, libro.getIdioma());
            preparedStatement.setString(8, libro.getDescricion());
            preparedStatement.setString(9, libro.getDescricionCorta());
            preparedStatement.setString(10, libro.getTituloOriginal());
            preparedStatement.setDate(11, new java.sql.Date(libro.getFechaDePublicacion().getTimeInMillis()));

            preparedStatement.setString(12, libro.getIsbn());

            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) {
                throw new SQLException( "Problemas actualizando libro!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void actualizarAutoresLibro (LibroVO libro, Connection connection) {
        /*TODO*/
    }

    public void actualizarGenerosLibro (LibroVO libro, Connection connection) {
        /*TODO*/
    }

    public void actualizarComentarioLibro (ComentarioVO comentarioVO, Connection connection) {
        /*TODO*/
    }

    public void actualizarValoracionLibro (ValoracionVO valoracionVO, Connection connection) {
        /*TODO*/
    }


    public void eliminarLibro(LibroVO libro, Connection connection){
        try{
            /* Create "preparedStatement". */
            String queryString = "DELETE FROM libro " +
                    "WHERE isbn = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);
            /* Fill "preparedStatement". */
            preparedStatement.setString(1, libro.getIsbn());

            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) {
                throw new SQLException( "Problemas eliminando libro!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void eliminarComentarioLibro (ComentarioVO comentarioVO, Connection connection) {
        /*TODO*/
    }

    public void eliminarValoracionLibro (ValoracionVO valoracionVO, Connection connection) {
        /*TODO*/
    }


    public LibroVO encontrarDatosLibro (String isbn, Connection connection){
        LibroVO libroVO = null;
        try{
            /* Create "preparedStatement". */
            String queryString = "SELECT EDITORIAL, TITULO, PAIS_DE_PUBLICACION, PRECIO, NUMERO_PAGINAS, NUMERO_DE_EDICION, IDIOMA, DESCRICION, DESCRICION_CORTA, TITULO_ORIGINAL, FECHA_DE_PUBLICACION" +
                    "FROM LIBRO WHERE  ISBN = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, isbn);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.first()) {
                throw new SQLException( "Libro no encontrado!!!!");
            }

            /* Execute query. */
            String editorial = resultSet.getString(1);
            String titulo = resultSet.getString(2);
            String pais_de_publicacion = resultSet.getString(3);
            Double precio = resultSet.getDouble(4);
            Integer numero_paginas = resultSet.getInt(5);
            Integer numero_edicion = resultSet.getInt(6);
            String idioma = resultSet.getString(7);
            String descripcion = resultSet.getString(8);
            String descripcion_corta = resultSet.getString(9);
            String titulo_original = resultSet.getString(10);

            Date fecha_de_publicacion_date = resultSet.getDate(11);
            Calendar fecha_de_publicacion;
            fecha_de_publicacion = new GregorianCalendar();

            if(fecha_de_publicacion_date!=null){
                fecha_de_publicacion.setTime(fecha_de_publicacion_date);
            }


            libroVO = new LibroVO(isbn,editorial,titulo,pais_de_publicacion,precio,numero_paginas,numero_edicion,idioma,
                    descripcion,descripcion_corta,titulo_original,fecha_de_publicacion);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return libroVO;
    }

    public List<LibroVO>  encontrarDatosLibro (Connection connection){
        List<LibroVO> resultado = new ArrayList<>();
        try{
            LibroVO libroVO = null;
            /* Create "preparedStatement". */
            String queryString = "SELECT ISBN, EDITORIAL, TITULO, PAIS_DE_PUBLICACION, PRECIO, NUMERO_PAGINAS, NUMERO_DE_EDICION, IDIOMA, DESCRICION, DESCRICION_CORTA, TITULO_ORIGINAL, FECHA_DE_PUBLICACION " +
                    "FROM LIBRO ";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	/* Execute query. */
                String isbn = resultSet.getString(1);
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

                if(fecha_de_publicacion_date!=null){
                    fecha_de_publicacion.setTime(fecha_de_publicacion_date);
                }


                libroVO = new LibroVO(isbn,editorial,titulo,pais_de_publicacion,precio,numero_paginas,numero_edicion,idioma,
                        descripcion,descripcion_corta,titulo_original,fecha_de_publicacion);

                resultado.add(libroVO);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Aquí estoy en el DAO y da Error al listar libros");

        }
        return resultado;
    }

    public List<AutorVO> encontrarAutoresLibro (String isbn, Connection connection){
        /* TODO */
    }

    public List<GeneroVO> encontrarGenerosLibro (String isbn, Connection connection){
        /* TODO */
    }

    public List<ComentarioVO> encontrarComentariosLibro (String isbn, Connection connection){
        /* TODO */
    }

    public List<ValoracionVO> encontrarValoracionesLibro (String isbn, Connection connection){
        /* TODO */
    }

    public List<CompraVO> encontrarVentasLibro (String isbn, Connection connection){
        /* TODO */
    }

    public List<VisitaVO> encontrarVisitasLibro (String isbn, Connection connection){
        /* TODO */
    }


}
