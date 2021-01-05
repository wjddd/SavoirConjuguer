package testCore;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.CSVReader;

public class ParseFr {
    List<CoreMap> sentences;

    public ParseFr(String text){
        CoreNLPSetup coreNLPSetup = CoreNLPSetup.getInstance();
        StanfordCoreNLP pipeline = coreNLPSetup.getPipeline();
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);
        sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
    }
    
 

    public List<String> getParsetext() throws FileNotFoundException, IOException, CsvValidationException {
    	
    	HashMap<String,String> Mots = new HashMap<String,String>();
		List<String> records = new ArrayList<String>();
		List<String> line = new ArrayList<String>();
		 String[] values = null;
		    String keys = null;
		    String value = null;
		    String key = null;

		try (CSVReader csvReader = new CSVReader(new FileReader("ABU.csv"));) { //"D:\\Desktop\\alao_java\\ABU.csv"
            while ((values = csvReader.readNext()) != null) {
                line = Arrays.asList(values);
                keys = line.get(0);
                String arr[] = keys.split("\\s+");
                key=arr[0];
                value=arr[1];
                Mots.put(key, value);
                line = null;
			
		    }
		}
    	
        String ph = ""; // texte à afficher
        int cpt = 1; // nombre de verbes à conjuguer
        int total = 0; // nombre de tokens
        for (CoreMap sentence : sentences) {
            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class); // pos tag
//				String tok = token.get(CoreAnnotations.TextAnnotation.class);
                String tok = token.word(); // token
                total++;
                if (pos.equals("V") && total%3==0 && cpt <= 20 ) {
                	String lemma = Mots.get(tok);
                	records.add(tok);
                    tok = "________";
                    ph = ph + "(" + cpt + ") " + tok + " ("+ lemma + ") ";
                    cpt++;
                } else {
                    ph = ph + tok + " ";
                }
            }
        }
        
        String listString = "";

        for (String s : records) {
            listString += s + ";";
        }

        List<String> result = new ArrayList<String>();
        result.add(listString);
        result.add(ph);
        return result;
    }
}
