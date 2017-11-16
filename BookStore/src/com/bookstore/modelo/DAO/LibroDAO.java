package com.bookstore.modelo.DAO;


import com.bookstore.modelo.VO.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class LibroDAO {
    // Correcto
    public static List<LibroVO> encontrarDatosLibros(String pattern, Integer inicio, Integer num_libros, Connection connection) {
        List<LibroVO> resultado = new ArrayList<>();
        try {
            LibroVO libroVO = null;
        /* Create "preparedStatement". */
            String queryString = "SELECT ISBN, EDITORIAL, TITULO, PAIS_DE_PUBLICACION, PRECIO, NUMERO_PAGINAS, NUMERO_DE_EDICION, IDIOMA, DESCRICION, DESCRICION_CORTA, TITULO_ORIGINAL, FECHA_DE_PUBLICACION, IMAGEN " +
                    "FROM sistInfBD.libro " +
                    "WHERE TITULO LIKE '%" + pattern + "%' OR ISBN LIKE '%" + pattern + "%' " +
                    "LIMIT " + Integer.toString(inicio) + ", " + Integer.toString(num_libros) + "; ";


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

                String imagen = resultSet.getString(13);


                libroVO = new LibroVO(isbn, editorial, titulo, pais_de_publicacion, precio, numero_paginas, numero_edicion, idioma,
                        descripcion, descripcion_corta, titulo_original, fecha_de_publicacion, imagen);

                resultado.add(libroVO);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Aquí estoy en el DAO y da Error al listar libros");

        }
        return resultado;
    }

    // Correcto
    public static Integer sizeEncontrarDatosLibros(String pattern, Connection connection) {
        List<LibroVO> resultado = new ArrayList<>();
        int cuenta = 0;
        try {
            LibroVO libroVO = null;
        /* Create "preparedStatement". */
            String queryString = "SELECT count(*) AS CUENTA " +
                    "FROM sistInfBD.libro " +
                    "WHERE TITULO LIKE '%" + pattern + "%' OR ISBN LIKE '%" + pattern + "%';";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

        /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cuenta = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Aquí estoy en el DAO y da Error al listar libros");

        }
        return cuenta;
    }

    // Correcto
    public static List<LibroVO> encontrarDatosLibros(GeneroVO genero, Integer inicio, Integer num_libros, Connection connection) {
        List<LibroVO> resultado = new ArrayList<>();
        try {
            LibroVO libroVO = null;
        /* Create "preparedStatement". */
            String queryString = "SELECT ISBN, EDITORIAL, TITULO, PAIS_DE_PUBLICACION, PRECIO, NUMERO_PAGINAS, NUMERO_DE_EDICION, IDIOMA, DESCRICION, DESCRICION_CORTA, TITULO_ORIGINAL, FECHA_DE_PUBLICACION, IMAGEN " +
                    "FROM sistInfBD.libro a " +
                    "INNER JOIN sistInfBD.pertenece_al_genero b ON a.isbn = b.libro " +
                    "WHERE b.genero = ? " +
                    "LIMIT " + Integer.toString(inicio) + ", " + Integer.toString(num_libros) + "; ";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            preparedStatement.setString(1, genero.getNombre());

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

                String imagen = resultSet.getString(13);

                libroVO = new LibroVO(isbn, editorial, titulo, pais_de_publicacion, precio, numero_paginas, numero_edicion, idioma,
                        descripcion, descripcion_corta, titulo_original, fecha_de_publicacion, imagen);

                resultado.add(libroVO);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Aquí estoy en el DAO y da Error al listar libros");

        }
        return resultado;
    }


    // Correcto
    public static Integer sizeEncontrarDatosLibros(GeneroVO genero, Connection connection) {
        int cuenta = 0;
        try {
            String queryString = "SELECT ISBN, EDITORIAL, TITULO, PAIS_DE_PUBLICACION, PRECIO, NUMERO_PAGINAS, NUMERO_DE_EDICION, IDIOMA, DESCRICION, DESCRICION_CORTA, TITULO_ORIGINAL, FECHA_DE_PUBLICACION " +
                    "FROM sistInfBD.libro a " +
                    "INNER JOIN sistInfBD.pertenece_al_genero b ON a.isbn = b.libro " +
                    "WHERE b.genero = ? ;";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            preparedStatement.setString(1, genero.getNombre());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cuenta = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Aquí estoy en el DAO y da Error al listar libros");

        }
        return cuenta;
    }

    // Correcto
    public static List<LibroVO> encontrarDatosLibrosMasVendidos(Date fecha_limite, Integer num_libros, Connection connection) {
        List<LibroVO> resultado = new ArrayList<>();
        try {
            LibroVO libroVO = null;
        /* Create "preparedStatement". */
            String queryString = "SELECT b.ISBN, b.EDITORIAL, b.TITULO, b.PAIS_DE_PUBLICACION, b.PRECIO, b.NUMERO_PAGINAS, b.NUMERO_DE_EDICION, b.IDIOMA," +
                    "  b.DESCRICION, b.DESCRICION_CORTA, b.TITULO_ORIGINAL, b.FECHA_DE_PUBLICACION, b.IMAGEN " +
                    "FROM (" +
                    "       SELECT " +
                    "         libro    AS LIBRO_C, " +
                    "         count(*) AS NUM_COMPRAS " +
                    "       FROM sistInfBD.compra " +
                    "       GROUP BY libro " +
                    "     ) a " +
                    "  INNER JOIN sistInfBD.libro b ON a.LIBRO_C = b.isbn ";
            if (fecha_limite != null) {
                queryString = queryString + "WHERE b.fecha_de_publicacion >= ? ";
            }
            queryString = queryString +
                    "ORDER BY a.NUM_COMPRAS " +
                    "LIMIT 0, ?;";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            if (fecha_limite != null) {
                preparedStatement.setDate(1, fecha_limite);
                preparedStatement.setInt(2, num_libros);
            } else {

                preparedStatement.setInt(1, num_libros);
            }

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

                String imagen = resultSet.getString(13);


                libroVO = new LibroVO(isbn, editorial, titulo, pais_de_publicacion, precio, numero_paginas, numero_edicion, idioma,
                        descripcion, descripcion_corta, titulo_original, fecha_de_publicacion, imagen);

                resultado.add(libroVO);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Aquí estoy en el DAO y da Error al listar libros");

        }
        return resultado;
    }

    // Correcto
    public static List<LibroVO> encontrarDatosLibrosMasVisitados(Date fecha_limite, Integer num_libros, Connection connection) {
        List<LibroVO> resultado = new ArrayList<>();
        try {
            LibroVO libroVO = null;
        /* Create "preparedStatement". */
            String queryString = "SELECT b.ISBN, b.EDITORIAL, b.TITULO, b.PAIS_DE_PUBLICACION, b.PRECIO, b.NUMERO_PAGINAS, b.NUMERO_DE_EDICION, b.IDIOMA," +
                    "  b.DESCRICION, b.DESCRICION_CORTA, b.TITULO_ORIGINAL, b.FECHA_DE_PUBLICACION, b.IMAGEN " +
                    "FROM (" +
                    "       SELECT " +
                    "         libro    AS LIBRO_C, " +
                    "         count(*) AS NUM_VISITAS " +
                    "       FROM sistInfBD.visita " +
                    "       GROUP BY libro " +
                    "     ) a " +
                    "  INNER JOIN sistInfBD.libro b ON a.LIBRO_C = b.isbn ";
            if (fecha_limite != null) {
                queryString = queryString + "WHERE b.fecha_de_publicacion >= ? ";
            }
            queryString = queryString +
                    "ORDER BY a.NUM_VISITAS " +
                    "LIMIT 0, ?;";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            if (fecha_limite != null) {
                preparedStatement.setDate(1, fecha_limite);
                preparedStatement.setInt(2, num_libros);
            } else {

                preparedStatement.setInt(1, num_libros);
            }

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

                String imagen = resultSet.getString(13);


                libroVO = new LibroVO(isbn, editorial, titulo, pais_de_publicacion, precio, numero_paginas, numero_edicion, idioma,
                        descripcion, descripcion_corta, titulo_original, fecha_de_publicacion, imagen);

                resultado.add(libroVO);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Aquí estoy en el DAO y da Error al listar libros");

        }
        return resultado;
    }

    // Correcto
    public static List<LibroVO> encontrarDatosLibrosNuevos(Integer num_libros, Connection connection) {
        List<LibroVO> resultado = new ArrayList<>();
        try {
            LibroVO libroVO = null;
        /* Create "preparedStatement". */
            String queryString = "SELECT b.ISBN, b.EDITORIAL, b.TITULO, b.PAIS_DE_PUBLICACION, b.PRECIO, b.NUMERO_PAGINAS, b.NUMERO_DE_EDICION, b.IDIOMA,  b.DESCRICION, b.DESCRICION_CORTA, b.TITULO_ORIGINAL, b.FECHA_DE_PUBLICACION, b.IMAGEN " +
                    "FROM libro b " +
                    "ORDER BY b.fecha_de_publicacion DESC " +
                    "LIMIT 0, ?;";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            preparedStatement.setInt(1, num_libros);


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

                String imagen = resultSet.getString(13);


                libroVO = new LibroVO(isbn, editorial, titulo, pais_de_publicacion, precio, numero_paginas, numero_edicion, idioma,
                        descripcion, descripcion_corta, titulo_original, fecha_de_publicacion, imagen);

                resultado.add(libroVO);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Aquí estoy en el DAO y da Error al listar libros");

        }
        return resultado;
    }
}
