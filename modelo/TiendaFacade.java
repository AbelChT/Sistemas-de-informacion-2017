package modelo;

import modelo.datos.DAO.UsuarioDAO;
import modelo.datos.GestorDeConexiones.GestorDeConexionesBD;
import modelo.datos.VO.UsuarioVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

public class TiendaFacade {
    public static void crearUsuario(UsuarioVO usuario)throws SQLException {
        Connection connection = null;
        try{
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            UsuarioDAO.insertarUsuario(usuario,connection);
            connection.close();
            System.out.println("En la fachada despues del DAO");
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }

    }
    public static List<UsuarioVO> listarClientes() throws SQLException {
        Connection connection = null;
        List<UsuarioVO> listado = null;
        try{
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            listado = UsuarioDAO.encontrarDatosUsuario(connection);
            System.out.println("En la fachada despues de encontrar en  a llamar al DAO");

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }
        return listado;

    }

    public static void main (String[] args) {
        UsuarioVO usuario = new UsuarioVO("abel1","maria2", "Lopez2", "M", new GregorianCalendar(12,12,12), "dos","fdsfsdfsdf",56433453L,"df");
        try{
            crearUsuario(usuario);
            for (UsuarioVO i : listarClientes()){
                System.out.println(i.getNombreDeUsuario());
            }
        }catch (Exception e){
            e.printStackTrace(System.err);
        }
    }
}