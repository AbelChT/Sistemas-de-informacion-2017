package com.bookstore.modelo;

import com.bookstore.modelo.DAO.GeneroDAO;
import com.bookstore.modelo.DAO.LibroDAO;
import com.bookstore.modelo.DAO.UsuarioDAO;
import com.bookstore.modelo.GestorDeConexiones.GestorDeConexionesBD;
import com.bookstore.modelo.VO.*;
import javafx.util.Pair;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.bookstore.modelo.DAO.UsuarioDAO.*;
import static org.apache.lucene.util.Version.LUCENE_40;


public class TiendaFacade {

    public static String INDEX_DIR = "../indices/";

    // Correcto
    public static boolean authenticate(String user, String pass) throws SQLException {

        Connection connection = null;
        boolean resultado = false;
        try {
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            resultado = UsuarioDAO.comprobarPassUsuario(user, Integer.toString(pass.hashCode()), connection);
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally {
            connection.close();
        }
        return resultado;


    }

    public enum CategoriaLibrosListar {MAS_VENDIDOS, MAS_VISITADOS, NUEVOS}

    public enum RangosTiempoLibrosListar {ESTA_SEMANA, ESTE_MES}

    // TODO:La primera componente son los resultados y la segunda el numero de conjuntos de esos resultados que se podrían devolver
    public static List<LibroVO> listarLibrosRecomendados(UsuarioVO usuario, Integer num_libros) throws SQLException {
        Connection connection = null;
        List<LibroVO> listado = null;
        try {
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            listado = LibroDAO.encontrarDatosLibrosRecomendados(usuario.getNombreDeUsuario(), 10,connection );
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally {
            connection.close();
        }
        return listado;

    }

    // Correcto
    public static List<LibroVO> listarLibros(CategoriaLibrosListar c, RangosTiempoLibrosListar r, Integer num_libros) throws SQLException {

        Connection connection = null;
        List<LibroVO> listado = null;
        try {
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            Date fecha = null;
            if (r != null) {
                switch (r) {
                    case ESTE_MES:
                        LocalDate previous_month = LocalDate.now().minusMonths(1);
                        fecha = Date.valueOf(previous_month);
                        break;
                    case ESTA_SEMANA:
                        LocalDate previous_week = LocalDate.now().minusWeeks(1);
                        fecha = Date.valueOf(previous_week);
                }
            }

            switch (c) {
                case NUEVOS:
                    listado = LibroDAO.encontrarDatosLibrosNuevos(num_libros, connection);
                    break;
                case MAS_VENDIDOS:
                    listado = LibroDAO.encontrarDatosLibrosMasVendidos(fecha, num_libros, connection);
                    break;
                case MAS_VISITADOS:
                    listado = LibroDAO.encontrarDatosLibrosMasVisitados(fecha, num_libros, connection);
            }
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally {
            connection.close();
        }
        return listado;
    }

    // Correcto
    public static Pair<List<LibroVO>, Integer> listarLibros(GeneroVO genero, Integer cursor, Integer num_libros) throws SQLException {

        Connection connection = null;
        List<LibroVO> listado = null;
        int numlibros = 0;
        try {
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            listado = LibroDAO.encontrarDatosLibros(genero, cursor - 1, num_libros, connection);
            numlibros = LibroDAO.sizeEncontrarDatosLibros(genero, connection);
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally {
            connection.close();
        }
        return new Pair<>(listado, numlibros);

    }

    // Correcto
    public static Pair<List<LibroVO>, Integer> listarLibros(String nombre_libro, Integer cursor, Integer num_libros) throws SQLException {

        Connection connection = null;
        List<LibroVO> listado = null;
        Integer numlibros = 0;
        try {
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            listado = LibroDAO.encontrarDatosLibros(nombre_libro, cursor - 1, num_libros, connection);
            numlibros = LibroDAO.sizeEncontrarDatosLibros(nombre_libro, connection);
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally {
            connection.close();
        }
        return new Pair<>(listado, numlibros);
    }

    // Correcto
    public static List<GeneroVO> listarGeneros() throws SQLException {
        Connection connection = null;
        List<GeneroVO> generos = null;
        try {
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            generos = GeneroDAO.encontrarGeneros(connection);
            System.out.println("En la fachada despues de encontrar en a llamar al DAO");
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally {
            connection.close();
        }
        return generos;
    }

    //TODO
    public static LibroVO listarLibro(String ISBN) throws SQLException {
        Connection connection = null;
        LibroVO libro = null;
        try {
            connection = GestorDeConexionesBD.getConnection();

            libro = LibroDAO.encontrarDatosLibro(ISBN, connection);
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }
        return libro;

    }


    public static List<ComentarioVO> listarComentarios(String ISBN)  throws SQLException {
        Connection connection = null;
        List<ComentarioVO> lista = null;
        try {
            connection = GestorDeConexionesBD.getConnection();

            lista = LibroDAO.encontrarComentariosLibro(ISBN,connection);
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }
        return lista;

    }

    public static void addCompra(String usuario, String ISBN) throws SQLException{
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();

            LibroDAO.insertarCompra(usuario ,ISBN,connection);
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }

    }

    public static void addVisita(String usuario, String ISBN) throws SQLException{
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();

            LibroDAO.insertarVisita(usuario ,ISBN,connection);
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }

    }

    public static void addComentario(String usuario, String comentario, String ISBN) throws SQLException{

        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();

            LibroDAO.insertarComentario(usuario ,ISBN, comentario, connection);
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }

    }

    public static List<AutorVO> listarAutores(String ISBN) throws SQLException {
        Connection connection = null;
        List<AutorVO> lista = null;
        try {
            connection = GestorDeConexionesBD.getConnection();

            lista = LibroDAO.encontrarAutoresLibro(ISBN,connection);
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            connection.close();
        }
        return lista;
    }


    public static UsuarioVO leerUsuarioFacade(String nombre) throws SQLException {
        Connection connection = null;
        UsuarioVO user = null;
        try {
            System.out.println("En la fachada voy a pedir una conexión");
            connection = GestorDeConexionesBD.getConnection();
            System.out.println("En la fachada voy a llamar al DAO");
            user = UsuarioDAO.encontrarDatosUsuario(nombre, connection);
            System.out.println("En la fachada despues de encontrar en  a llamar al DAO");

        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally {
            connection.close();
        }
        return user;
    }
    public static List<ComentarioVO> encontrarComentariosRealizadosFacade(String nombreDeUsuario) {
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return encontrarComentariosRealizados(nombreDeUsuario, connection);

    }

    public static List<CompraVO> encontrarComprasRealizadasFachada(String nombre) {
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return encontrarComprasRealizadas(nombre, connection);
    }

    public static void insertarCompraLibroFachada(CompraVO compra) {
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertarCompraLibro(compra, connection);
    }

    public static void actualizarUsuarioFacade(UsuarioVO user) {
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        actualizarUsuario(user, connection);
    }

    //****************************************************  registro
    public static boolean existeEmailFacade(String email){
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existeEmail(email, connection);
    }

    public static void insertarUsuarioFacade(UsuarioVO user){
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertarUsuario(user, connection);
    }

    //************************************************** comentario
    public static boolean poseeLibro(String usuario, String ISBN){

        Connection connection = null;
        boolean resultado = false;
        try {
            connection = GestorDeConexionesBD.getConnection();

            resultado = LibroDAO.haCompradoLibro(usuario,ISBN,connection);


            connection.close();
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        }
        return resultado;
    }

    public static Pair<Integer,Integer> obtenerMediaValoraciones(String ISBN){

        Connection connection = null;
        Pair<Integer,Integer> media = new Pair<>(0,0);
        try {
            connection = GestorDeConexionesBD.getConnection();

            media = LibroDAO.encontrarMediaValoracionesLibro(ISBN,connection);
            connection.close();
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{
            //connection.close();
        }
        return media;
    }

    public static void addValoracion(String usuario,String valoracion,String libro){
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();

            LibroDAO.insertarValoracion(usuario ,libro, Integer.parseInt(valoracion), connection);
            connection.close();
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{

        }

    }
    public static boolean haValorado(String usuario,String isbn){
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();

           boolean result = (LibroDAO.haValorado(usuario ,isbn, connection) != -1);
            connection.close();

            return  result;
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{

        }
        return false;
    }
    public static Integer valoracionDada(String usuario,String isbn){
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();

            Integer result =LibroDAO.haValorado(usuario ,isbn, connection);
            connection.close();

            return  result;
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        } finally{

        }
        return 0;
    }

    /////////////////////////////    lucene     ///////////////////////////////////

    public static void indexar_libros(){
        Connection connection = null;
        try {
            connection = GestorDeConexionesBD.getConnection();
            String queryString = "SELECT ISBN ,EDITORIAL, TITULO, PAIS_DE_PUBLICACION, PRECIO, NUMERO_PAGINAS, NUMERO_DE_EDICION, IDIOMA, DESCRICION, DESCRICION_CORTA, TITULO_ORIGINAL, FECHA_DE_PUBLICACION " +
                    "FROM libro ";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryString);

            /* Fill "preparedStatement". */
            //preparedStatement.setString(1, nombre_completo_autor);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            StandardAnalyzer analyzer = new StandardAnalyzer(LUCENE_40);
            IndexWriterConfig config = new IndexWriterConfig(LUCENE_40, analyzer);
            IndexWriter writer = new IndexWriter(FSDirectory.open(new java.io.File(INDEX_DIR)), config);
            writer.deleteAll();
            writer.close();
            while (resultSet.next()) {
                String isbn  = resultSet.getString(1);

                String editorial = resultSet.getString(2);
                String titulo = resultSet.getString(3);
                String pais_de_publicacion = resultSet.getString(4);
                Double precio = resultSet.getDouble(5);
                Integer numero_paginas = resultSet.getInt(6);
                Integer numero_edicion = resultSet.getInt(7);
                String idioma = resultSet.getString(8);
                String descripcion = resultSet.getString(9);
                String descripcion_corta = resultSet.getString(10);
                String titulo_original = resultSet.getString(11);
                Date fecha_de_publicacion_date = resultSet.getDate(12);

                Document document = new Document();
                document.add(new Field("isbn", isbn, Field.Store.YES, Field.Index.NOT_ANALYZED));
                document.add(new Field("precio", String.valueOf(precio.intValue()), Field.Store.YES, Field.Index.NOT_ANALYZED));
                document.add(new Field("titulo", titulo, Field.Store.YES, Field.Index.ANALYZED));
                document.add(new Field("pais", pais_de_publicacion, Field.Store.YES, Field.Index.ANALYZED));
                document.add(new Field("idioma", idioma, Field.Store.YES, Field.Index.ANALYZED));
                document.add(new Field("descripcion", descripcion, Field.Store.YES, Field.Index.ANALYZED));

                writer = new IndexWriter(FSDirectory.open(new java.io.File(INDEX_DIR)), config);
                writer.addDocument(document);
                System.out.println("--------------------- indexado libro");
                writer.close();
            }


            connection.close();
        } catch (Exception e) {
            System.out.println("Excepción en el método de la fachada y no lanza hacia arriba");
            e.printStackTrace(System.err);
        }
    }

    private static ArrayList<String> buscarQueryEnIndice(int paginas, int hitsPorPagina, String queryAsString) {

        DirectoryReader directoryReader = null;
        try {
            directoryReader = DirectoryReader.open(FSDirectory.open(new java.io.File(INDEX_DIR)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        IndexSearcher buscador = new IndexSearcher(directoryReader);

        StandardAnalyzer analyzer = new StandardAnalyzer(LUCENE_40);
        QueryParser queryParser = new QueryParser(LUCENE_40, "contenido", analyzer);
        Query query =null;

        try {
            query = queryParser.parse(queryAsString);
        } catch (org.apache.lucene.queryparser.classic.ParseException e) {
            e.printStackTrace();
        }
        TopDocs resultado = null;
        try {
            resultado = buscador.search(query, paginas * hitsPorPagina);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScoreDoc[] hits = resultado.scoreDocs;
        ArrayList<String> isbnResultado = new ArrayList<>();

            System.out.println("Found " + hits.length + " hits.");
            for(int i=0;i<hits.length;++i) {
                int docId = hits[i].doc;
                Document doc = null;
                try {
                    doc = buscador.doc(docId);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println((i + 1) + ". " + doc.get("path") + "\t" + hits[i].score + "\t" + doc.get("isbn"));
                isbnResultado.add(doc.get("isbn"));
            }
        return isbnResultado;
    }

    private static ArrayList<String> buscarQueries(int paginas, int hitsPorPagina, Collection queries) throws IOException {
        Iterator<String> iterador = queries.iterator();
        while (iterador.hasNext()){
            String queryAsString = (String) iterador.next();
            return buscarQueryEnIndice( paginas, hitsPorPagina, queryAsString);
        }
        return new ArrayList<String>();
    }

    public static List<LibroVO> buscarLibrosLucene(String busqueda) {
        Collection<String> queries = new ArrayList<String>();
        ArrayList<String> isbnResultado = new ArrayList<>();
        if (busqueda.contains("isbn")) {
            busqueda = busqueda.replace("isbn", "");
            queries.add("isbn:" + busqueda);
        } else if (busqueda.contains("titulo")) {
            busqueda = busqueda.replace("titulo", "");
            queries.add("titulo:" + busqueda);
        } else if (busqueda.contains("precio")) {
            busqueda = busqueda.replace("precio", "");
            queries.add("precio:" + busqueda);
        } else if (busqueda.contains("descripcion")) {
            busqueda = busqueda.replace("descripcion", "");
            queries.add("descripcion:" + busqueda);
        } else if (busqueda.contains("titulo")) {
            busqueda = busqueda.replace("titulo", "");
            queries.add("titulo:" + busqueda);
        } else if (busqueda.contains("idioma")) {
            busqueda = busqueda.replace("idioma", "");
            queries.add("idioma:" + busqueda);
        } else if (busqueda.contains("pais")) {
            busqueda = busqueda.replace("pais", "");
            queries.add("pais:" + busqueda);
        } else {
            // por defecto busca titulo
            queries.add("titulo:" + busqueda);
        }
        List<LibroVO> libros = new ArrayList<>();
        try {
            isbnResultado = buscarQueries(1, 6, queries);
            for(String isbn : isbnResultado){
                    libros.add(listarLibro(isbn));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libros;
    }

}
