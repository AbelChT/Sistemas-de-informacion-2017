package modelo.datos.VO;

import java.util.Calendar;
import java.util.List;

public class UsuarioVO{
	// Datos cuenta
	private String nombre_de_usuario;
	private String encrypted_password;
	// Datos personales
	private String nombre;
	private String apellidos;
	private Calendar fecha_de_nacimiento;
	private String email;
	private String direccion_postal;
	private Long numero_de_telefono;
	private String numero_de_cuenta_bancaria;
	private List<Visita> libros_visitados;
	private List<Compra> libros_comprados;

	public class Visita {
		private LibroVO libro;
		private Calendar fecha;
	}

	public class Compra {
		private LibroVO libro;
	    private Calendar fecha;
	    private double precio;
	}
}