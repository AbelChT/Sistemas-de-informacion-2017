package com.bookstore.modelo.VO;

import java.util.List;

public class AutorVO {
    private String nombre_completo;
    private String pais_de_nacimiento;
    private String descripcion;
    private List<LibroVO> libros_escritos;

    public String getNombreCompleto() {
        return nombre_completo;
    }

    public void setNombreCompleto(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getPaisDeNacimiento() {
        return pais_de_nacimiento;
    }

    public void setPaisDeNacimiento(String pais_de_nacimiento) {
        this.pais_de_nacimiento = pais_de_nacimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<LibroVO> getLibrosEscritos() {
        return libros_escritos;
    }

    public void setLibrosEscritos(List<LibroVO> libros_escritos) {
        this.libros_escritos = libros_escritos;
    }

    public AutorVO(String nombre_completo, String pais_de_nacimiento, String descripcion) {
        this.nombre_completo = nombre_completo;
        this.pais_de_nacimiento = pais_de_nacimiento;
        this.descripcion = descripcion;
    }
}