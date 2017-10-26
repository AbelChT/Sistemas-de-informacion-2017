package modelo.datos.DAO;

import modelo.datos.VO.GeneroVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {
    public void insertarGenero (GeneroVO genero, Connection connection) {
        try{
            /* Create "preparedStatement". */
            String queryString = "INSERT INTO GENERO " +
                    "(NOMBRE) " +
                    "VALUES (?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
            preparedStatement.setString(1, genero.getNombre() );


            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) {
                throw new SQLException( "Problemas insertando genero!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void eliminarGenero(GeneroVO genero, Connection connection){
        try{
            /* Create "preparedStatement". */
            String queryString = "DELETE genero " +
                    "WHERE nombre = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);
            /* Fill "preparedStatement". */
            preparedStatement.setString(1, genero.getNombre());

            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) {
                throw new SQLException( "Problemas eliminando genero!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public List<GeneroVO>  encontrarGeneros (Connection connection){
        List<GeneroVO> resultado = new ArrayList<>();
        try{
            GeneroVO generoVO = null;
            /* Create "preparedStatement". */
            String queryString = "SELECT NOMBRE " +
                    "FROM genero";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	/* Execute query. */
                String nombre = resultSet.getString(1);

                System.out.println("Leyendo genero "+ nombre);
                generoVO = new GeneroVO (nombre);
                resultado.add(generoVO);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Aquí estoy en el DAO y da Error al listar generos");

        }
        return resultado;
    }

    public boolean existeGenero(String nombre, Connection connection){
        /* TODO */
    }
}
