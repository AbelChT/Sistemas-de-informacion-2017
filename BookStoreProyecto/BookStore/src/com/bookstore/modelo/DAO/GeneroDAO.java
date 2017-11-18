package com.bookstore.modelo.DAO;


import com.bookstore.modelo.VO.GeneroVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {
    public static List<GeneroVO> encontrarGeneros(Connection connection) {
        List<GeneroVO> resultado = new ArrayList<>();
        try {
            GeneroVO generoVO = null;
         /* Create "preparedStatement". */
            String queryString = "SELECT NOMBRE " +
                    "FROM sistInfBD.genero";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

         /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
           /* Execute query. */
                String nombre = resultSet.getString(1);

                System.out.println("Leyendo genero " + nombre);
                generoVO = new GeneroVO(nombre);
                resultado.add(generoVO);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Aquí estoy en el DAO y da Error al listar generos");

        }
        return resultado;
    }
}
