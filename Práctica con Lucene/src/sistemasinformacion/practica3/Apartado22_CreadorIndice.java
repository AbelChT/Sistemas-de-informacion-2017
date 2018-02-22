package sistemasinformacion.practica3;

import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.util.Version;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Apartado22_CreadorIndice {
    public static List<String> listarDirectorio(String directorio) {
        List<String> resultado = new ArrayList<>();

        File actual = new File(directorio);
        String[] result = actual.list();
        if(result!=null){
            for ( String i: result){
                String ruta = directorio + "/" + i;
                if ((new File(ruta).isDirectory())){
                    resultado.addAll(listarDirectorio(ruta));
                }
                else {
                    resultado.add(ruta);
                }
            }
        }
        return resultado;
    }

    public static Indexador crearIndiceSobreDirectorio(String directorio) throws IOException{
        Collection<String> ficheros = listarDirectorio(directorio);
        return new Indexador(ficheros, new SpanishAnalyzer(Version.LUCENE_40), new MMapDirectory(new File("./indice_disco")));
    }

    public static void main(String[]args) throws IOException {
        System.out.println("Introduce la ruta relativa del directorio a indexar:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String linea  = br.readLine();
        File folder = new File(linea);

        while (!folder.isDirectory()){
            System.out.println("No se ha introducido la ruta de un directorio");
            linea  = br.readLine();
            folder = new File(linea);
        }

        Indexador creado = crearIndiceSobreDirectorio(linea);

    }
}
