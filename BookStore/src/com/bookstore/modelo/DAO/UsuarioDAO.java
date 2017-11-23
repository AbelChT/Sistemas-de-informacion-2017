package com.bookstore.modelo.DAO;

import com.bookstore.modelo.VO.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class UsuarioDAO {
    public static boolean comprobarPassUsuario(String username, String pass, Connection connection) {

        boolean resultado = false;

        try {
          /* Create "preparedStatement". */
            String queryString = "SELECT * " +
                    "FROM sistInfBD.usuario WHERE  NOMBRE_DE_USUARIO = ? AND PASSWORD = ? ";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

          /* Fill "preparedStatement". */
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pass);

          /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            resultado = resultSet.first();

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return resultado;

    }

    public static UsuarioVO encontrarDatosUsuario (String nombre_user, Connection connection){
        UsuarioVO usuarioVO = null;
        try{
            /* Create "preparedStatement". */
            String queryString = "SELECT PASSWORD, NOMBRE, APELLIDOS, FECHA_DE_NACIMIENTO, EMAIL, DIRECCION, NUMERO_DE_TELEFONO, NUMERO_DE_CUENTA_BANCARIA " +
                    "FROM usuario WHERE  NOMBRE_DE_USUARIO = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, nombre_user);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.first()) {
                throw new SQLException( "Usuario no encontrado!!!!");
            }

            /* Execute query. */
            String password = resultSet.getString(1);
            String nombre = resultSet.getString(2);
            String apellidos = resultSet.getString(3);

            String email = resultSet.getString(5);
            String direccion = resultSet.getString(6);
            Long numero_de_telefono = resultSet.getLong(7);
            String numero_de_cuenta = resultSet.getString(8);

            Date fecha_de_nacimiento_date = resultSet.getDate(4);
            Calendar fecha_de_nacimiento;
            fecha_de_nacimiento = new GregorianCalendar();

            if(fecha_de_nacimiento_date!=null){
                fecha_de_nacimiento.setTime(fecha_de_nacimiento_date);
            }

            usuarioVO = new UsuarioVO(nombre_user,password,nombre,apellidos,fecha_de_nacimiento,email,direccion,numero_de_telefono,numero_de_cuenta);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return usuarioVO;
    }



    public static void insertarUsuario (UsuarioVO usuario, Connection connection) {
        try{
            /* Create "preparedStatement". */
            String queryString = "INSERT INTO USUARIO " +
                    "(NOMBRE_DE_USUARIO, PASSWORD, NOMBRE, APELLIDOS, FECHA_DE_NACIMIENTO, EMAIL, DIRECCION, NUMERO_DE_TELEFONO, NUMERO_DE_CUENTA_BANCARIA) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, usuario.getNombreDeUsuario());
            preparedStatement.setString(2, usuario.getEncryptedPassword());
            preparedStatement.setString(3, usuario.getNombre());
            preparedStatement.setString(4, usuario.getApellidos());
            preparedStatement.setDate(5, new java.sql.Date(usuario.getFechaDeNacimiento().getTimeInMillis()));
            preparedStatement.setString(6, usuario.getEmail());
            preparedStatement.setString(7, usuario.getDireccionPostal());
            preparedStatement.setLong(8, usuario.getNumeroDeTelefono());
            preparedStatement.setString(9, usuario.getNumeroDeCuentaBancaria());


            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) {
                throw new SQLException( "Problemas insertando usuario!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static void insertarCompraLibro(CompraVO compraVO, Connection connection){
        if (compraVO.getLibro() != null && compraVO.getUsuario() != null && compraVO.getFecha() != null && compraVO.getPrecio() != null) {
            try {
                String queryString = "INSERT INTO COMPRA " +
                        "(USUARIO, LIBRO, FECHA,PRECIO) " +
                        "VALUES (?,?,?,?)";

                PreparedStatement preparedStatement =
                        connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
                preparedStatement.setString(1, compraVO.getUsuario().getNombreDeUsuario());
                preparedStatement.setString(2, compraVO.getLibro().getIsbn());
                preparedStatement.setDate(3, new java.sql.Date(compraVO.getFecha().getTimeInMillis()));
                preparedStatement.setDouble(4, compraVO.getPrecio());

            /* Execute query. */
                int insertedRows = preparedStatement.executeUpdate();

                if (insertedRows != 1) {
                    throw new SQLException("Problemas insertando compra de libros!!!!");
                }
            }  catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }

    public static void insertarVisitaLibro(VisitaVO visitaVO, Connection connection){
        if (visitaVO.getLibro() != null && visitaVO.getUsuario() != null && visitaVO.getFecha() != null) {
            try {
                String queryString = "INSERT INTO VISITA" +
                        "(USUARIO, LIBRO, FECHA) " +
                        "VALUES (?,?,?)";

                PreparedStatement preparedStatement =
                        connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
                preparedStatement.setString(1, visitaVO.getUsuario().getNombreDeUsuario());
                preparedStatement.setString(2, visitaVO.getLibro().getIsbn());
                preparedStatement.setDate(3, new java.sql.Date(visitaVO.getFecha().getTimeInMillis()));


            /* Execute query. */
                int insertedRows = preparedStatement.executeUpdate();

                if (insertedRows != 1) {
                    throw new SQLException("Problemas insertando visita de libros!!!!");
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }

    public static void actualizarUsuario (UsuarioVO usuario, Connection connection) {
        try{
            /* Create "preparedStatement". */
            String queryString = "UPDATE USUARIO " +
                    "SET PASSWORD = ?, NOMBRE = ?, APELLIDOS = ?, FECHA_DE_NACIMIENTO = ?, EMAIL = ?, DIRECCION = ?, NUMERO_DE_TELEFONO = ?, NUMERO_DE_CUENTA_BANCARIA = ?" +
                    "WHERE NOMBRE_DE_USUARIO = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);
            /* Fill "preparedStatement". */
            preparedStatement.setString(1, usuario.getEncryptedPassword());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellidos());
            preparedStatement.setDate(4, new java.sql.Date(usuario.getFechaDeNacimiento().getTimeInMillis()));
            preparedStatement.setString(5, usuario.getEmail());
            preparedStatement.setString(6, usuario.getDireccionPostal());
            preparedStatement.setLong(7, usuario.getNumeroDeTelefono());
            preparedStatement.setString(8, usuario.getNumeroDeCuentaBancaria());
            preparedStatement.setString(9, usuario.getNombreDeUsuario());

            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) {
                throw new SQLException( "Problemas actualizando usuario!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static void actualizarCompraLibro(CompraVO compraVO, Connection connection){
        if (compraVO.getLibro() != null && compraVO.getUsuario() != null && compraVO.getFecha() != null && compraVO.getPrecio() != null) {
            try {
                String queryString = "UPDATE COMPRA " +
                        "SET FECHA = ?, PRECIO = ? " +
                        "WHERE USUARIO = ? AND LIBRO = ?";

                PreparedStatement preparedStatement =
                        connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
                preparedStatement.setString(1, compraVO.getUsuario().getNombreDeUsuario());
                preparedStatement.setString(2, compraVO.getLibro().getIsbn());
                preparedStatement.setDate(3, new java.sql.Date(compraVO.getFecha().getTimeInMillis()));
                preparedStatement.setDouble(4, compraVO.getPrecio());

            /* Execute query. */
                int insertedRows = preparedStatement.executeUpdate();

                if (insertedRows != 1) {
                    throw new SQLException("Problemas insertando compra de libros!!!!");
                }
            }  catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }

    public static void actualizarVisitaLibro(VisitaVO visitaVO, Connection connection){
        if (visitaVO.getLibro() != null && visitaVO.getUsuario() != null && visitaVO.getFecha() != null) {
            try {
                String queryString = "UPDATE VISITA " +
                        "SET FECHA = ? " +
                        "WHERE USUARIO = ? AND LIBRO = ?";

                PreparedStatement preparedStatement =
                        connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
                preparedStatement.setString(1, visitaVO.getUsuario().getNombreDeUsuario());
                preparedStatement.setString(2, visitaVO.getLibro().getIsbn());
                preparedStatement.setDate(3, new java.sql.Date(visitaVO.getFecha().getTimeInMillis()));


            /* Execute query. */
                int insertedRows = preparedStatement.executeUpdate();

                if (insertedRows != 1) {
                    throw new SQLException("Problemas insertando visita de libros!!!!");
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }

    public static void eliminarUsuario(UsuarioVO usuario, Connection connection){
        try{
            /* Create "preparedStatement". */
            String queryString = "DELETE FROM USUARIO " +
                    "WHERE NOMBRE_DE_USUARIO = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);
            /* Fill "preparedStatement". */
            preparedStatement.setString(1, usuario.getNombreDeUsuario());

            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) {
                throw new SQLException( "Problemas eliminando USUARIO!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static void eliminarCompraLibro(CompraVO compraVO, Connection connection){
        if (compraVO.getLibro() != null && compraVO.getUsuario() != null) {
            try{
            /* Create "preparedStatement". */
                String queryString = "DELETE FROM COMPRA " +
                        "WHERE USUARIO = ? AND LIBRO = ?";
                PreparedStatement preparedStatement =
                        connection.prepareStatement(queryString);
            /* Fill "preparedStatement". */
                preparedStatement.setString(1, compraVO.getUsuario().getNombreDeUsuario());
                preparedStatement.setString(2, compraVO.getLibro().getIsbn());

            /* Execute query. */
                int insertedRows = preparedStatement.executeUpdate();

                if (insertedRows != 1) {
                    throw new SQLException( "Problemas eliminando CompraLibro!!!!");
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }

    public static void eliminarVisitaLibro(VisitaVO visitaVO, Connection connection){
        if (visitaVO.getLibro() != null && visitaVO.getUsuario() != null) {
            try{
            /* Create "preparedStatement". */
                String queryString = "DELETE FROM VISITA " +
                        "WHERE USUARIO = ? AND LIBRO = ?";
                PreparedStatement preparedStatement =
                        connection.prepareStatement(queryString);
            /* Fill "preparedStatement". */
                preparedStatement.setString(1, visitaVO.getUsuario().getNombreDeUsuario());
                preparedStatement.setString(2, visitaVO.getLibro().getIsbn());

            /* Execute query. */
                int insertedRows = preparedStatement.executeUpdate();

                if (insertedRows != 1) {
                    throw new SQLException( "Problemas eliminando VisitaLibro!!!!");
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }


    public static List<UsuarioVO> encontrarDatosUsuario (Connection connection){
        List<UsuarioVO> resultado = new ArrayList<>();
        try{
            UsuarioVO usuarioVO = null;
            /* Create "preparedStatement". */
            String queryString = "SELECT NOMBRE_DE_USUARIO,PASSWORD, NOMBRE, APELLIDOS, FECHA_DE_NACIMIENTO, EMAIL, DIRECCION, NUMERO_DE_TELEFONO, NUMERO_DE_CUENTA_BANCARIA " +
                    "FROM USUARIO";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	/* Execute query. */
                String nombre_user = resultSet.getString(1);
                String password = resultSet.getString(2);
                String nombre = resultSet.getString(3);
                String apellidos = resultSet.getString(4);

                String email = resultSet.getString(6);
                String direccion = resultSet.getString(7);
                Long numero_de_telefono = resultSet.getLong(8);
                String numero_de_cuenta = resultSet.getString(9);

                Date fecha_de_nacimiento_date = resultSet.getDate(5);
                Calendar fecha_de_nacimiento;
                fecha_de_nacimiento = new GregorianCalendar();

                if(fecha_de_nacimiento_date!=null){
                    fecha_de_nacimiento.setTime(fecha_de_nacimiento_date);
                }

                usuarioVO = new UsuarioVO (nombre_user,password,nombre,apellidos,fecha_de_nacimiento,email,direccion,numero_de_telefono,numero_de_cuenta);

                resultado.add(usuarioVO);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Aquí estoy en el DAO y da Error al listar usuariao");

        }
        return resultado;
    }



    public static List<ComentarioVO> encontrarComentariosRealizados (String nombre_usuario, Connection connection){

        List<ComentarioVO> list = new ArrayList<>();
        try{
            /* Create "preparedStatement". */
            UsuarioVO usuarioVO = encontrarDatosUsuario(nombre_usuario,connection);
            String queryString = "SELECT COMENTARIO,  LIBRO, FECHA " +
                    "FROM COMENTA WHERE  USUARIO = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, nombre_usuario);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	/* Execute query. */
                String comentario  = resultSet.getString(1);
                String libro = resultSet.getString(2);
                LibroVO libroVO = LibroDAO.encontrarDatosLibro(libro,connection);
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(resultSet.getDate(3));

                ComentarioVO comentarioVO = new ComentarioVO (usuarioVO, libroVO, calendar, comentario);
                list.add(comentarioVO);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return list;
    }

    public static List<ValoracionVO> encontrarValoracionesRealizadas (String nombre_usuario, Connection connection){
        List<ValoracionVO> list = new ArrayList<>();
        try{
            /* Create "preparedStatement". */
            UsuarioVO usuarioVO = encontrarDatosUsuario(nombre_usuario,connection);
            String queryString = "SELECT PUNTUACION,  LIBRO " +
                    "FROM PUNTUA WHERE  USUARIO = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, nombre_usuario);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	/* Execute query. */
                Integer puntuacion  = resultSet.getInt(1);
                String libro = resultSet.getString(2);
                LibroVO libroVO = LibroDAO.encontrarDatosLibro(libro,connection);

                ValoracionVO valoracionVO = new ValoracionVO (usuarioVO, libroVO, puntuacion);
                list.add(valoracionVO);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return list;
    }

    public static List<CompraVO> encontrarComprasRealizadas (String nombre_usuario, Connection connection){
        List<CompraVO> list = new ArrayList<>();
        try{
            /* Create "preparedStatement". */
            UsuarioVO usuarioVO = encontrarDatosUsuario(nombre_usuario,connection);
            String queryString = "SELECT PRECIO,  LIBRO, FECHA " +
                    "FROM COMPRA WHERE  USUARIO = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, nombre_usuario);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	/* Execute query. */
                Double precio  = resultSet.getDouble(1);
                String libro = resultSet.getString(2);
                LibroVO libroVO = LibroDAO.encontrarDatosLibro(libro,connection);
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(resultSet.getDate(3));

                CompraVO compraVO = new CompraVO (usuarioVO, libroVO, calendar, precio);
                list.add(compraVO);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return list;
    }

    public static List<VisitaVO> encontrarVisitasRealizadas (String nombre_usuario, Connection connection){
        List<VisitaVO> list = new ArrayList<>();
        try{
            /* Create "preparedStatement". */
            UsuarioVO usuarioVO = encontrarDatosUsuario(nombre_usuario,connection);
            String queryString = "SELECT LIBRO, FECHA " +
                    "FROM VISITA WHERE  USUARIO = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, nombre_usuario);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	/* Execute query. */
                String libro = resultSet.getString(1);
                LibroVO libroVO = LibroDAO.encontrarDatosLibro(libro,connection);
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(resultSet.getDate(2));

                VisitaVO visitaVO = new VisitaVO (usuarioVO, libroVO, calendar);
                list.add(visitaVO);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return list;
    }

    public static boolean existeUsuario(String nombreusuario, Connection connection){
        return encontrarDatosUsuario(nombreusuario,connection) != null;
    }

}
