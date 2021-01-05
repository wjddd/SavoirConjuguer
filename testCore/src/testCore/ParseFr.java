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
    	
    	HashMap<String,String> Mots = new HashMap<String,String>(); //hashmap pour stocker les mots conjugués et les lemmes
		List<String> records = new ArrayList<String>(); // liste pour garder des mots originaux, sert à comparer avec les réponses de l'utilisateur et calculer le score
		List<String> line = new ArrayList<String>();
		 String[] values = null;
		    String keys = null;
		    String value = null;
		    String key = null;

		try (CSVReader csvReader = new CSVReader(new FileReader("ABU.csv"));) {
            while ((values = csvReader.readNext()) != null) {
                line = Arrays.asList(values); // lire chaque ligne comme une liste
                keys = line.get(0);
                String arr[] = keys.split("\\s+"); // diviser les éléments par l'espace
                key=arr[0]; // mettre les mots conjugés comme key
                value=arr[1]; // mettre les lemmes comme value
                Mots.put(key, value);
                line = null; //vider la liste line
			
		    }
		}
    	
        String ph = ""; // texte à afficher
        int cpt = 1; // nombre de verbes à conjuguer
        int totalV = 1; // nombre de verbes
        for (CoreMap sentence : sentences) {
            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class); // pos tag
//				String tok = token.get(CoreAnnotations.TextAnnotation.class);
                String tok = token.word(); // token
                if (pos.equals("V")) {
                    totalV++;
                    if (totalV%3==0 && cpt <= 20 ) {
                        String lemma = Mots.get(tok); //récupérer les lemmes
                        records.add(tok); // conserver les verbes qu'on va remplacer dans la liste records
                        tok = "________";
                        ph = ph + "(" + cpt + ") " + tok + " ("+ lemma + ") ";
                        cpt++;
                    } else {
                        ph = ph + tok + " ";
                    }
                } else {
                    ph = ph + tok + " ";
                }
            }
        }
        
        String listString = ""; // afin de retourner la liste records, on le transforme en String

        for (String s : records) {
            listString += s + ";";
        }

        List<String> result = new ArrayList<String>(); // afin de retourner deux éléments, on crée une liste
        result.add(listString);
        result.add(ph);
        return result;
    }
}
