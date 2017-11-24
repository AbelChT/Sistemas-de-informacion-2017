package com.bookstore.controlador;

import com.bookstore.controlador.CommonConstants;
import com.bookstore.modelo.VO.UsuarioVO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.Connection;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;

import static com.bookstore.modelo.TiendaFacade.actualizarUsuarioFacade;
import static com.bookstore.modelo.TiendaFacade.leerUsuarioFacade;

/**
 * Servlet implementation class CrearUsuarioServlet
 */
public class EditarInformacionPersonal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public EditarInformacionPersonal() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		boolean errores = false;
		HashMap tablaErrores = new HashMap();
		System.out.println("He llegado aquí con el usuario logi");
		String errorNombre = "";
		String nombre = request.getParameter(CommonConstants.fullNameProfileParameterName);
		System.out.println("----------------------------NOMBRE:"+nombre+":");

		if(nombre != null) {
			if (nombre.trim().equals(new String(""))) {
				System.out.println("ERROR NOMBRE");
				errorNombre = "El nombre no puede ser vacio";
				tablaErrores.put(CommonConstants.nameProfileParameterName, errorNombre);
				errores = true;
			}
		}
		//-----------------------------
		String errorApellidos = "";
		String apellidos = request.getParameter(CommonConstants.surnameProfileParameterName);
		if(apellidos != null) {
			if (apellidos.trim().equals(new String(""))) {
				errorApellidos = "El apellido no puede ser vacio";
				tablaErrores.put(CommonConstants.surnameProfileParameterName, errorApellidos);
				errores = true;
			}
		}
		//-----------------------------
		String errorLocalidad = "";
		String localidad = request.getParameter(CommonConstants.dirProfileParameterName);
		if(localidad != null) {
			if (localidad.trim().equals(new String(""))) {
				errorLocalidad = "La localidad no puede ser vacia";
				tablaErrores.put(CommonConstants.dirProfileParameterName, errorLocalidad);
				errores = true;
			}
		}
		//-----------------------------
		System.out.println("Despues de leer parametros");
		if (errores==false){
			try{
				System.out.println("Voy a llamar a la fachada y métdo Actualziar Usuario de ");
				UsuarioVO user = leerUsuarioFacade("abel1");
				user.setNombre(nombre);
				user.setApellidos(apellidos);
				user.setDireccionPostal(localidad);
				actualizarUsuarioFacade (user);
				RequestDispatcher dispacher = request.getRequestDispatcher("EditarInformacionPersonal.jsp");
				dispacher.forward(request, response);
			}catch (Exception e){
				e.printStackTrace(System.err);
				System.out.println("Problemas con el VN");
				response.sendRedirect("errorInterno.html");
			}

		}else{
			request.setAttribute("errores", tablaErrores);
			RequestDispatcher dispacher = request.getRequestDispatcher("EditarInformacionPersonal.jsp");
			System.out.println("Antes de hacer el forward los valores....");
			System.out.println("En el servlet"+"  "  + nombre +"  " + apellidos +"  ");
			request.setAttribute("nombreAux", "  "+nombre);
			request.setAttribute("apellidosAux", "  "+apellidos);
			dispacher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}