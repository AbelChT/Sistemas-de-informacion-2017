package sistemasinformacion.practica3;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.Field;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.analysis.core.SimpleAnalyzer;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;


import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class IndexadorYBuscador{

	private Collection <String> ficherosAIndexar = new ArrayList<String>();
	private Collection <String> queries = new ArrayList <String>();
    private Analyzer analizador;
	
	
	public IndexadorYBuscador(Collection<String> ficherosAIndexar, Collection<String> queries, Analyzer analyzer){
		this.ficherosAIndexar = ficherosAIndexar;
		this.queries = queries;
		this.analizador = analyzer;
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
	
	private Directory crearIndiceEnUnDirectorio() throws IOException{
		IndexWriter indice = null;
		Directory directorioAlmacenarIndice = new RAMDirectory();

		IndexWriterConfig configuracionIndice = new IndexWriterConfig(Version.LUCENE_40, analizador);

		indice = new IndexWriter(directorioAlmacenarIndice, configuracionIndice);
		
		Collection <String> paths = this.ficherosAIndexar;
		Iterator <String> iterador = paths.iterator();
		while (iterador.hasNext()){
			String path = (String) iterador.next();
			anhadirFichero(indice, path);			
		}
		indice.close();
		return directorioAlmacenarIndice;
	}
	
	private void buscarQueryEnIndice(Directory directorioDelIndice, int paginas, int hitsPorPagina, String queryAsString)
	throws IOException{

		DirectoryReader directoryReader = DirectoryReader.open(directorioDelIndice);
		IndexSearcher buscador = new IndexSearcher(directoryReader);
		
		QueryParser queryParser = new QueryParser(Version.LUCENE_40, "contenido", analizador); 
		Query query =null;
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
	
	private void buscarQueries(Directory directorioDelIndice, int paginas, int hitsPorPagina)
	throws IOException{
		Collection <String> queries = this.queries;
		Iterator <String> iterador = queries.iterator();
		while (iterador.hasNext()){
			String queryAsString = (String) iterador.next();
			buscarQueryEnIndice(directorioDelIndice, paginas, hitsPorPagina, queryAsString);			
		}

	}
	public static void buscar( Collection <String> ficheros, String termino, Analyzer analyzer) throws IOException{
		System.out.println("\n Termino a buscar: " + termino);

		Collection <String> queries = new ArrayList <String>();
		queries.add(termino);
		IndexadorYBuscador ejemplo = new IndexadorYBuscador(ficheros, queries, analyzer);
		Directory directorioDelIndiceCreado = ejemplo.crearIndiceEnUnDirectorio();

		ejemplo.buscarQueries(directorioDelIndiceCreado, 1, ficheros.size());
	}
}
