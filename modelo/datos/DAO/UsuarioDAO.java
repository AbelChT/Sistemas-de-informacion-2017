package modelo.datos.DAO;

import modelo.datos.VO.*;

import java.sql.*;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class UsuarioDAO {
    public void insertarUsuario (UsuarioVO usuario, Connection connection) {
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

    public void insertarCompraLibro(CompraVO compraVO, Connection connection){
        /* TODO */
    }

    public void insertarVisitaLibro(VisitaVO compraVO, Connection connection){
        /* TODO */
    }

    public void actualizarUsuario (UsuarioVO usuario, Connection connection) {
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

    public void actualizarCompraLibro(CompraVO compraVO, Connection connection){
        /* TODO */
    }

    public void actualizarVisitaLibro(VisitaVO compraVO, Connection connection){
        /* TODO */
    }

    public void eliminarUsuario(UsuarioVO usuario, Connection connection){
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

    public void eliminarCompraLibro(CompraVO compraVO, Connection connection){
        /* TODO */
    }

    public void eliminarVisitaLibro(VisitaVO compraVO, Connection connection){
        /* TODO */
    }

    public UsuarioVO encontrarDatosUsuario (String nombre_user, Connection connection){
        UsuarioVO usuarioVO = null;
        try{
            /* Create "preparedStatement". */
            String queryString = "SELECT PASSWORD, NOMBRE, APELLIDOS, FECHA_DE_NACIMIENTO, EMAIL, DIRECCION, NUMERO_DE_TELEFONO, NUMERO_DE_CUENTA_BANCARIA " +
                    "FROM USUARIO WHERE  NOMBRE_DE_USUARIO = ?";
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

            usuarioVO = new UsuarioVO (nombre_user,password,nombre,apellidos,fecha_de_nacimiento,email,direccion,numero_de_telefono,numero_de_cuenta);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return usuarioVO;
    }

    public List<UsuarioVO>  encontrarDatosUsuario (Connection connection){
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



    public List<ComentarioVO> encontrarComentariosRealizados (String isbn, Connection connection){
        /* TODO */
    }

    public List<ValoracionVO> encontrarValoracionesRealizadas (String isbn, Connection connection){
        /* TODO */
    }

    public List<CompraVO> encontrarComprasRealizadas (String isbn, Connection connection){
        /* TODO */
    }

    public List<VisitaVO> encontrarVisitasRealizadas (String isbn, Connection connection){
        /* TODO */
    }

    public boolean existeUsuario(String nombreusuario, Connection connection){
        /* TODO */
    }


}
