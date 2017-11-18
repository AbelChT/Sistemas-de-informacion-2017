package com.bookstore.modelo.VO;

import java.util.Calendar;
import java.util.List;

public class LibroVO {
    private String isbn;
    private String editorial;
    private String titulo;
    private String pais_de_publicacion;
    private double precio;
    private Integer numero_paginas;
    private Integer numero_de_edicion;
    private String idioma;
    private String descricion;
    private String descricion_corta;
    private String titulo_original;
    private Calendar fecha_de_publicacion;

    private List<AutorVO> autores;
    private List<GeneroVO> generos;

    private String path_imagen;


    // Informacion generada por los usuarios sobre el libro
    private List<ValoracionVO> puntuaciones;
    private List<ComentarioVO> comentarios;

    public LibroVO(String isbn, String editorial, String titulo, String pais_de_publicacion, double precio, Integer numero_paginas, Integer numero_de_edicion, String idioma, String descricion, String descricion_corta, String titulo_original, Calendar fecha_de_publicacion, String path_imagen) {
          this.isbn = isbn;
          this.editorial = editorial;
          this.titulo = titulo;
          this.pais_de_publicacion = pais_de_publicacion;
          this.precio = precio;
          this.numero_paginas = numero_paginas;
          this.numero_de_edicion = numero_de_edicion;
          this.idioma = idioma;
          this.descricion = descricion;
          this.descricion_corta = descricion_corta;
          this.titulo_original = titulo_original;
          this.fecha_de_publicacion = fecha_de_publicacion;
          this.path_imagen = path_imagen;
      }

      public LibroVO(String isbn, String editorial, String titulo, String pais_de_publicacion, double precio, Integer numero_paginas, Integer numero_de_edicion, String idioma, String descricion, String descricion_corta, String titulo_original, Calendar fecha_de_publicacion, String path_imagen, List<AutorVO> autores, List<GeneroVO> generos) {
          this.isbn = isbn;
          this.editorial = editorial;
          this.titulo = titulo;
          this.pais_de_publicacion = pais_de_publicacion;
          this.precio = precio;
          this.numero_paginas = numero_paginas;
          this.numero_de_edicion = numero_de_edicion;
          this.idioma = idioma;
          this.descricion = descricion;
          this.descricion_corta = descricion_corta;
          this.titulo_original = titulo_original;
          this.fecha_de_publicacion = fecha_de_publicacion;
          this.autores = autores;
          this.generos = generos;
          this.path_imagen = path_imagen;
      }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPaisDePublicacion() {
        return pais_de_publicacion;
    }

    public void setPaisDePublicacion(String pais_de_publicacion) {
        this.pais_de_publicacion = pais_de_publicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Integer getNumeroPaginas() {
        return numero_paginas;
    }

    public void setNumeroPaginas(Integer numero_paginas) {
        this.numero_paginas = numero_paginas;
    }

    public Integer getNumeroDeEdicion() {
        return numero_de_edicion;
    }

    public void setNumeroDeEdicion(Integer numero_de_edicion) {
        this.numero_de_edicion = numero_de_edicion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getDescricion() {
        return descricion;
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }

    public String getDescricionCorta() {
        return descricion_corta;
    }

    public void setDescricionCorta(String descricion_corta) {
        this.descricion_corta = descricion_corta;
    }

    public String getTituloOriginal() {
        return titulo_original;
    }

    public void setTituloOriginal(String titulo_original) {
        this.titulo_original = titulo_original;
    }

    public Calendar getFechaDePublicacion() {
        return fecha_de_publicacion;
    }

    public void setFechaDePublicacion(Calendar fecha_de_publicacion) {
        this.fecha_de_publicacion = fecha_de_publicacion;
    }

    public List<AutorVO> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorVO> autores) {
        this.autores = autores;
    }

    public List<GeneroVO> getGeneros() {
        return generos;
    }

    public void setGeneros(List<GeneroVO> generos) {
        this.generos = generos;
    }


    public List<ValoracionVO> getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(List<ValoracionVO> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    public List<ComentarioVO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioVO> comentarios) {
        this.comentarios = comentarios;
    }

    public String getPathImagen() {
        return "/images/" + path_imagen;
    }

    public void setPathImagen(String path_imagen) {
        this.path_imagen = path_imagen;
    }
}
