package com.bookstore.modelo.VO;

import java.util.Calendar;

public class CompraVO {
    private UsuarioVO usuario;
    private LibroVO libro;
    private Calendar fecha;
    private Double precio;

    public CompraVO(UsuarioVO usuario, LibroVO libro, Calendar fecha, Double precio) {
        this.usuario = usuario;
        this.libro = libro;
        this.fecha = fecha;
        this.precio = precio;
    }

    public CompraVO(UsuarioVO usuario, LibroVO libro) {
        this.usuario = usuario;
        this.libro = libro;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}