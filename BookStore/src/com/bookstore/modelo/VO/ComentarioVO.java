package com.bookstore.modelo.VO;

import java.util.Calendar;

public class ComentarioVO {
    private UsuarioVO usuario;
    private LibroVO libro;
    private Calendar fecha;
    private String comentario;

    public ComentarioVO(UsuarioVO usuario, LibroVO libro, Calendar fecha, String comentario) {
        this.usuario = usuario;
        this.libro = libro;
        this.fecha = fecha;
        this.comentario = comentario;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}