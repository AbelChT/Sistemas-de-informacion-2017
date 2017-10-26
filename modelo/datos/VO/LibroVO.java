package modelo.datos.VO;

import java.util.Calendar;
import java.util.List;

public class LibroVO {
	private Long isbn;
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

	private OfertaVO oferta;

	// Informacion generada por los usuarios sobre el libro
	private List<PuntuacionVO> puntuaciones;
	private List<ComentarioVO> comentarios;
	
}