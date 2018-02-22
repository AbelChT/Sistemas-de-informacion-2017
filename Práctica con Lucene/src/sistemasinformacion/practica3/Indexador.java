package sistemasinformacion.practica3;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.*;
import org.apache.lucene.util.Version;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;

public class Indexador {

    Directory directory;


    public Indexador(Collection<String> ficherosAIndexar, Analyzer analizador, Directory directory_indice) throws IOException {

        directory = crearIndiceEnUnDirectorio(ficherosAIndexar, analizador, directory_indice); // new RAMDirectory()
    }

    private void anhadirFichero(IndexWriter indice, String path)
            throws IOException {
        InputStream inputStream = new FileInputStream(path);
        BufferedReader inputStreamReader = new BufferedReader(
                new InputStreamReader(inputStream,"UTF-8"));

        Document doc = new Document();
        doc.add(new TextField("contenido", inputStreamReader));
        doc.add(new StringField("path", path, Field.Store.YES));
        indice.addDocument(doc);
    }

    private Directory crearIndiceEnUnDirectorio(Collection<String> ficherosAIndexar, Analyzer analizador, Directory directorioAlmacenarIndice) throws IOException{
        IndexWriter indice = null;

        IndexWriterConfig configuracionIndice = new IndexWriterConfig(Version.LUCENE_40, analizador);

        indice = new IndexWriter(directorioAlmacenarIndice, configuracionIndice);

        Collection <String> paths = ficherosAIndexar;
        Iterator<String> iterador = paths.iterator();
        while (iterador.hasNext()){
            String path = (String) iterador.next();
            anhadirFichero(indice, path);
        }
        indice.close();
        return directorioAlmacenarIndice;
    }

    public Directory getDirectory() {
        return directory;
    }
}
