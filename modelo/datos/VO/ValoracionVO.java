package modelo.datos.VO;

import java.util.Calendar;

public class ValoracionVO {
    private UsuarioVO usuario;
    private LibroVO libro;
    private Calendar fecha;
    private Integer puntuacion;

    public ValoracionVO(UsuarioVO usuario, LibroVO libro, Calendar fecha, Integer puntuacion) {
        this.usuario = usuario;
        this.libro = libro;
        this.fecha = fecha;
        this.puntuacion = puntuacion;
    }

    public UsuarioVO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    public LibroVO getLibro() {
        return libro;
    }

    public void setLibro(LibroVO libro) {
        this.libro = libro;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }
}