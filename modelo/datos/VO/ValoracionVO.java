package modelo.datos.VO;

import java.util.Calendar;

public class ValoracionVO {
    private UsuarioVO usuario;
    private LibroVO libro;
    private Integer puntuacion;

    public ValoracionVO(UsuarioVO usuario, LibroVO libro, Integer puntuacion) {
        this.usuario = usuario;
        this.libro = libro;
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

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }
}