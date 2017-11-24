package com.bookstore.modelo.GestorDeConexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorDeConexionesBD {

	private final static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private final static String DRIVER_URL = "jdbc:mysql://192.168.56.2:3306/sistInfBD";
	private final static String USER = "usuario";
	private final static String PASSWORD = "clave";
		
	static {			
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.err);
		}	
	}

	private GestorDeConexionesBD(){
	}
	
	public final static Connection getConnection()
		throws SQLException {
			return DriverManager.getConnection(DRIVER_URL, USER, PASSWORD);
	}
}
