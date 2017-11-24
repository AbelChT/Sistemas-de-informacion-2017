package com.bookstore.modelo.VO;

import java.util.Calendar;

public class VisitaVO {
    private UsuarioVO usuario;
    private LibroVO libro;
    private Calendar fecha;

    public VisitaVO(UsuarioVO usuario, LibroVO libro, Calendar fecha) {
        this.usuario = usuario;
        this.libro = libro;
        this.fecha = fecha;
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
}