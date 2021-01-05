package testCore;

import org.apache.log4j.BasicConfigurator;

import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, CsvValidationException, IOException {
		BasicConfigurator.configure();
		ImportCorpus corpus = new ImportCorpus(); // importer le corpus
		ArrayList<String> lines = corpus.Corpus("articles.txt"); // liste lines qui contient le contenu du corpus
//		System.out.println(lines);
		System.out.println("Tapez 1 pour commencer : ");
		for (String text : lines ) {
			Scanner sc = new Scanner(System.in);
			String commence = sc.nextLine();
			if (commence.equals("1")) {
				System.out.println("Chargement en cours...");
				System.out.println("-------------------------");
				ParseFr parseFr = new ParseFr(text);
				List<String> result =parseFr.getParsetext();
				System.out.println(result.get(1));
				String reponse = result.get(0);
			ArrayList<String> verbes = new ArrayList<String>();
			String[] strings=reponse.split(";");
			for (String str : strings) {
				verbes.add(str);
			}
			System.out.println("Votre réponse : ");
			checkAnswer(verbes);
				System.out.println("Tapez 1 pour continuer, 0 pour quitter : ");
			} else {
				System.out.println("Merci pour votre participation.");
				System.exit(0); // arrêter ce programme
			}
		}
	}


	public static void checkAnswer(@org.jetbrains.annotations.NotNull ArrayList<String> verbes) {
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
		System.out.println("Vous avez obtenu : "+score+" / "+verbes.size());
	}
}