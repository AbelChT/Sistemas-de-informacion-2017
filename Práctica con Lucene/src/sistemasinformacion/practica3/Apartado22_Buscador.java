package sistemasinformacion.practica3;

import org.apache.lucene.analysis.es.SpanishAnalyzer;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.util.Version;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

public class Apartado22_Buscador {

    public static void buscarQueries(Collection<String> queries ) throws IOException {
        Directory directory = new MMapDirectory(new File("./indice_disco"));
        Buscador buscador = new Buscador(directory, new SpanishAnalyzer(Version.LUCENE_40));
       buscador.buscar(queries,1, 100);
    }
    public static void main(String[] args) throws IOException {

        System.out.println("Introduce una querie:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String linea  = br.readLine();

        Collection <String> queries = new ArrayList<String>();
        queries.add(linea);

        buscarQueries(queries);

    }
}
