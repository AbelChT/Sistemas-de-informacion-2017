package modelo.datos.DAO;

import modelo.datos.VO.AutorVO;
import modelo.datos.VO.LibroVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    public void insertarAutor (AutorVO autor, Connection connection) {
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

    public void actualizarAutor (AutorVO autor, Connection connection) {
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

    public void eliminarAutor(AutorVO autor, Connection connection){
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

    public AutorVO encontrarDatosAutor (String nombre_completo_autor, Connection connection){
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
                throw new SQLException( "Usuario no encontrado!!!!");
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

    public List<AutorVO>  encontrarDatosAutor (Connection connection){
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

                System.out.println("Leyendo usuario "+ nombre_completo);
                autorVO = new AutorVO (nombre_completo, pais_de_nacimiento,
                        descripcion);
                resultado.add(autorVO);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Aquí estoy en el DAO y da Error al listar usuariao");

        }
        return resultado;
    }

    public List<LibroVO> encontrarLibrosAutor (String nombre_completo_autor, Connection connection){
        /* TODO */
    }

}
