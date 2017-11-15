package com.bookstore.modelo.DAO;


import com.bookstore.modelo.VO.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class LibroDAO {
    public static void insertarLibro(LibroVO libro, Connection connection) {
        try {
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
                throw new SQLException("Problemas insertando libro!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    // Pre: El autor ha de estar
    public static void insertarAutorLibro(LibroVO libro, AutorVO autorVO, Connection connection) {
        try {

                     /* Create "preparedStatement". */
                String queryString = "INSERT INTO ESCRITO_POR " +
                        "(AUTOR,LIBRO) " +
                        "VALUES (?,?)";

                PreparedStatement preparedStatement =
                        connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
                preparedStatement.setString(1, autorVO.getNombreCompleto());
                preparedStatement.setString(2, libro.getIsbn());


            /* Execute query. */
                int insertedRows = preparedStatement.executeUpdate();

                if (insertedRows != 1) {
                    throw new SQLException("Problemas insertando autor de libros!!!!");
                }



        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static void insertarGeneroLibro(LibroVO libro, GeneroVO generoVO, Connection connection) {
        try {
            if (libro.getGeneros() != null) {

                    String queryString = "INSERT INTO PERTENECE_AL_GENERO " +
                            "(LIBRO, GENERO) " +
                            "VALUES (?,?)";

                    PreparedStatement preparedStatement =
                            connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
                    preparedStatement.setString(1, libro.getIsbn());
                    preparedStatement.setString(2, generoVO.getNombre());


            /* Execute query. */
                    int insertedRows = preparedStatement.executeUpdate();

                    if (insertedRows != 1) {
                        throw new SQLException("Problemas insertando generos de libros!!!!");
                    }
                }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }


    public static void insertarComentarioLibro(ComentarioVO comentarioVO, Connection connection) {
        if (comentarioVO.getLibro() != null && comentarioVO.getUsuario() != null && comentarioVO.getFecha() != null && comentarioVO.getComentario() != null) {
               try {
                   String queryString = "INSERT INTO COMENTA " +
                           "(USUARIO, LIBRO, FECHA, COMENTARIO) " +
                           "VALUES (?,?,?,?)";

                   PreparedStatement preparedStatement =
                           connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
                   preparedStatement.setString(1, comentarioVO.getUsuario().getNombreDeUsuario());
                   preparedStatement.setString(2, comentarioVO.getLibro().getIsbn());
                   preparedStatement.setDate(3, new java.sql.Date(comentarioVO.getFecha().getTimeInMillis()));
                   preparedStatement.setString(4, comentarioVO.getComentario());

            /* Execute query. */
                   int insertedRows = preparedStatement.executeUpdate();

                   if (insertedRows != 1) {
                       throw new SQLException("Problemas insertando comentario de libros!!!!");
                   }
               }  catch (Exception e) {
                   e.printStackTrace(System.err);
               }

        }

    }


    public static void actualizarLibro(LibroVO libro, Connection connection) {
        try {
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
                throw new SQLException("Problemas actualizando libro!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }


    public static void actualizarComentarioLibro(ComentarioVO comentarioVO, Connection connection) {
        if (comentarioVO.getLibro() != null && comentarioVO.getUsuario() != null && comentarioVO.getFecha() != null && comentarioVO.getComentario() != null) {
            try {
                String queryString = "UPDATE COMENTA " +
                        "SET COMENTARIO=?" +
                        "WHERE USUARIO=? AND LIBRO=? AND FECHA=?";

                PreparedStatement preparedStatement =
                        connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
                preparedStatement.setString(1, comentarioVO.getComentario());
                preparedStatement.setString(2, comentarioVO.getUsuario().getNombreDeUsuario());
                preparedStatement.setString(3, comentarioVO.getLibro().getIsbn());
                preparedStatement.setDate(4, new java.sql.Date(comentarioVO.getFecha().getTimeInMillis()));

            /* Execute query. */
                int insertedRows = preparedStatement.executeUpdate();

                if (insertedRows != 1) {
                    throw new SQLException("Problemas actualizando comentario de libros!!!!");
                }
            }  catch (Exception e) {
                e.printStackTrace(System.err);
            }

        }
    }




    public static void eliminarLibro(LibroVO libro, Connection connection) {
        try {
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
                throw new SQLException("Problemas eliminando libro!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }



    public static LibroVO encontrarDatosLibro(String isbn, Connection connection) {
        LibroVO libroVO = null;
        try {
            /* Create "preparedStatement". */
            String queryString = "SELECT EDITORIAL, TITULO, PAIS_DE_PUBLICACION, PRECIO, NUMERO_PAGINAS, NUMERO_DE_EDICION, IDIOMA, DESCRICION, DESCRICION_CORTA, TITULO_ORIGINAL, FECHA_DE_PUBLICACION " +
                    "FROM LIBRO WHERE  ISBN = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, isbn);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.first()) {
                throw new SQLException("Libro no encontrado!!!!");
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

            if (fecha_de_publicacion_date != null) {
                fecha_de_publicacion.setTime(fecha_de_publicacion_date);
            }


            libroVO = new LibroVO(isbn, editorial, titulo, pais_de_publicacion, precio, numero_paginas, numero_edicion, idioma,
                    descripcion, descripcion_corta, titulo_original, fecha_de_publicacion);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return libroVO;
    }

    public static List<LibroVO> encontrarDatosLibro(Connection connection) {
        List<LibroVO> resultado = new ArrayList<>();
        try {
            LibroVO libroVO = null;
            /* Create "preparedStatement". */
            String queryString = "SELECT ISBN, EDITORIAL, TITULO, PAIS_DE_PUBLICACION, PRECIO, NUMERO_PAGINAS, NUMERO_DE_EDICION, IDIOMA, DESCRICION, DESCRICION_CORTA, TITULO_ORIGINAL, FECHA_DE_PUBLICACION " +
                    "FROM sistInfBD.libro ";
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

                if (fecha_de_publicacion_date != null) {
                    fecha_de_publicacion.setTime(fecha_de_publicacion_date);
                }


                libroVO = new LibroVO(isbn, editorial, titulo, pais_de_publicacion, precio, numero_paginas, numero_edicion, idioma,
                        descripcion, descripcion_corta, titulo_original, fecha_de_publicacion);

                resultado.add(libroVO);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Aquí estoy en el DAO y da Error al listar libros");

        }
        return resultado;
    }

}
