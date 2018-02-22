package sistemasinformacion.practica3;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.util.Collection;

public class Buscador {
    Directory a_buscar;
    Analyzer analizador;

    public Buscador(Directory indice, Analyzer analizador) {
        a_buscar = indice;
        this.analizador = analizador;
    }

    public void buscar(Collection<String> queries, int paginas, int hitsPorPagina) throws IOException {

        for (String queryAsString: queries){
            buscarQueryEnIndice(a_buscar, paginas, hitsPorPagina, queryAsString);
        }
    }

    private void buscarQueryEnIndice(Directory directorioDelIndice, int paginas, int hitsPorPagina, String queryAsString)
            throws IOException{

        DirectoryReader directoryReader = DirectoryReader.open(directorioDelIndice);
        IndexSearcher buscador = new IndexSearcher(directoryReader);

        QueryParser queryParser = new QueryParser(Version.LUCENE_40, "contenido", analizador);
        Query query = null;
        try{
            query = queryParser.parse(queryAsString);
            TopDocs resultado = buscador.search(query, paginas * hitsPorPagina);
            ScoreDoc[] hits = resultado.scoreDocs;

            System.out.println("Found " + hits.length + " hits.");
            for(int i=0;i<hits.length;++i) {
                int docId = hits[i].doc;
                Document doc = buscador.doc(docId);
                System.out.println((i + 1) + ". " + doc.get("path") + "\t" + hits[i].score);
            }
        }catch (ParseException e){
            throw new IOException(e);
        }
    }
}
