package com.bookstore.modelo.DAO;

import java.sql.*;


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
}
