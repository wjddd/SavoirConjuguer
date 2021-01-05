package testCore;

import edu.stanford.nlp.ling.tokensregex.ComplexNodePattern;
import org.apache.log4j.BasicConfigurator;

import com.opencsv.exceptions.CsvValidationException;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, CsvValidationException, IOException {
		BasicConfigurator.configure();
		ImportCorpus corpus = new ImportCorpus(); // importer le corpus
		ArrayList<String> lines = corpus.Corpus("articles.txt"); // liste lines qui contient le contenu du corpus
		HashMap<Integer, String> Historique = new HashMap<Integer, String>();
		int cptText = 0;
		System.out.println("Tapez 1 pour commencer : ");
		for (String text : lines ) {
			cptText++;
			Scanner sc = new Scanner(System.in);
			String commence = sc.nextLine();
			if (commence.equals("1")) {
				System.out.println("");
				System.out.println("Chargement en cours...\n");
				System.out.println("------------ Exercice "+ cptText + " ------------\n");
				ParseFr parseFr = new ParseFr(text);
				List<String> result =parseFr.getParsetext(); // getParsetext retourne une liste contenant deux éléments
				System.out.println(result.get(1)); // afficher la texte à trous
				String reponse = result.get(0); // pour calculer le score, on a conserver les verbes remplacés, les verbes sont retourner sous la forme String
				// transformer String en ArrayList
				ArrayList<String> verbes = new ArrayList<String>();
				String[] strings = reponse.split(";");
				for (String str : strings) {
					verbes.add(str);
				}
				System.out.println("Votre réponse : ");
				String phScore = checkAnswer(verbes); // vérifier les réponses de l'utilisateur et afficher le résultat
				Historique.put(cptText, phScore);
				System.out.println("Tapez 1 pour continuer, 0 pour quitter : "); // passer à l'exercice suivant ou s'arretêt
			} else {
				System.out.println("------------ Scores ------------\n");
				for (Integer i : Historique.keySet()) {
					System.out.println("Exercice " + i + " : " + Historique.get(i));
				}
				System.out.println("");
				System.out.println("------------ Merci pour votre participation ------------");
				System.exit(0); // arrêter ce programme
			}
		}
	}


	public static String checkAnswer(@NotNull ArrayList<String> verbes) {
		ArrayList<String> userAnswers = new ArrayList<String>();

		int finalScore = 0;
		int score = 0;
		for (int i = 1; i <= verbes.size(); i++) {
			System.out.print(i + " : ");
			Scanner input = new Scanner(System.in);
			String answer = input.nextLine();
			userAnswers.add(answer);
		}
		System.out.println("------------------ Résultat ------------------");
		int cpt = 0;
		for (int j = 0; j < verbes.size(); j++) {
			cpt++;
			if (verbes.get(j).equals(userAnswers.get(j))) {
				score++;
				System.out.println(cpt + " : Vrai !");
			} else {
				System.out.println(cpt + " : Faux ! La bonne réponse est : " + verbes.get(j));
			}
		}
		System.out.println("");
		System.out.println("Vous avez obtenu : " + score+" / " + verbes.size() + "\n");
		String phScore = score+" / " + verbes.size();
		return phScore;
	}
}