package sistemasinformacion.practica3;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


public class Apartado21 {

    public static void main(String[] args) throws IOException {
        Collection<String> ficheros = new ArrayList<String>();
        ficheros.add("./ficheros/uno.txt");
        ficheros.add("./ficheros/dos.txt");
        ficheros.add("./ficheros/tres.txt");
        ficheros.add("./ficheros/cuatro.txt");

        IndexadorYBuscador.buscar(ficheros,"tomcat", new StandardAnalyzer(Version.LUCENE_40));
        IndexadorYBuscador.buscar(ficheros,"tomcat OR lucene", new StandardAnalyzer(Version.LUCENE_40));
        IndexadorYBuscador.buscar(ficheros,"\"Lucene\"", new StandardAnalyzer(Version.LUCENE_40));
        IndexadorYBuscador.buscar(ficheros,"of", new StandardAnalyzer(Version.LUCENE_40));
        IndexadorYBuscador.buscar(ficheros,"tomcat*", new StandardAnalyzer(Version.LUCENE_40));
        IndexadorYBuscador.buscar(ficheros,"jakarta", new StandardAnalyzer(Version.LUCENE_40));
        IndexadorYBuscador.buscar(ficheros,"Lucene ‐apache", new StandardAnalyzer(Version.LUCENE_40));

        IndexadorYBuscador.buscar(ficheros,"tomcat", new SimpleAnalyzer(Version.LUCENE_40));
        IndexadorYBuscador.buscar(ficheros,"tomcat OR lucene", new SimpleAnalyzer(Version.LUCENE_40));
        IndexadorYBuscador.buscar(ficheros,"\"Lucene\"", new SimpleAnalyzer(Version.LUCENE_40));
        IndexadorYBuscador.buscar(ficheros,"of", new SimpleAnalyzer(Version.LUCENE_40));
        IndexadorYBuscador.buscar(ficheros,"tomcat*", new SimpleAnalyzer(Version.LUCENE_40));
        IndexadorYBuscador.buscar(ficheros,"jakarta", new SimpleAnalyzer(Version.LUCENE_40));
        IndexadorYBuscador.buscar(ficheros,"Lucene ‐apache", new SimpleAnalyzer(Version.LUCENE_40));
    }
}
