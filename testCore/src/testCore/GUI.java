//package testCore;
//
//import com.opencsv.exceptions.CsvValidationException;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class GUI implements ActionListener {
//
//    public static void showGUI() throws IOException, CsvValidationException {
//
//        JFrame frame = new JFrame("Conjugaison");
//
//        frame.setSize(350, 200);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JPanel panel = new JPanel();
//
//        frame.add(panel);
//
//        placeComponents(panel);
//
//        frame.setVisible(true);
//    }
//
//    private static void placeComponents(JPanel panel) throws IOException, CsvValidationException {
//
//        panel.setLayout(null);
//
//        JLabel userLabel = new JLabel("Texte");
//
//        userLabel.setBounds(10,20,80,25);
//        panel.add(userLabel);
//
//        JTextArea userText = new JTextArea();
//        userText.setLineWrap(true);
//        userText.setText(null);
//        userText.setBounds(100,20,165,25);
//        ImportCorpus corpus = new ImportCorpus();
//        ArrayList<String> lines = corpus.Corpus("articles.txt");
//        for (String text : lines ) {
////            Scanner sc = new Scanner(System.in);
////            String commence = sc.nextLine();
////            System.out.println("Chargement en cours...");
////            System.out.println("-------------------------");
////            ParseFr parseFr = new ParseFr(text);
////            List<String> result =parseFr.getParsetext();
//            userText.append("1");
////            userText.append(result.get(1));
////                System.out.println(result.get(1));
////            String reponse = result.get(0);
////            ArrayList<String> verbes = new ArrayList<String>();
////            String[] strings=reponse.split(";");
////            for (String str : strings) {
////                verbes.add(str);
////            }
////            System.out.println("Votre réponse : ");
////            checkAnswer(verbes);
////            System.out.println("Tapez 1 pour continuer, 0 pour quitter : ");
//        }
//        panel.add(userText);
//
//
//        JLabel reponseLabel = new JLabel("Réponse");
//        reponseLabel.setBounds(10,50,80,25);
//        panel.add(reponseLabel);
//
//        JTextField reponseText = new JTextField(20);
//        reponseText.setBounds(100,20,165,25);
//        panel.add(reponseText);
//
//        JButton validateButton = new JButton("Valider");
//        validateButton .setBounds(10, 80, 80, 25);
//        validateButton.addActionListener(new GUI());
//        panel.add(validateButton);
//
//        JLabel success = new JLabel("");
//        success.setBounds(10, 110, 300, 25);
//        panel.add(success);
//    }
//
//    public static void checkAnswer(@org.jetbrains.annotations.NotNull ArrayList<String> verbes) {
//        ArrayList<String> userAnswers = new ArrayList<String>();
//        int finalScore = 0;
//        int score = 0;
//        for (int i = 1; i <= verbes.size(); i++) {
//            System.out.print(i + " : ");
//            Scanner input = new Scanner(System.in);
//            String answer = input.nextLine();
//            userAnswers.add(answer);
//        }
//        System.out.println("------------------ Résultat ------------------");
//        int cpt = 0;
//        for (int j = 0; j < verbes.size(); j++) {
//            cpt++;
//            if (verbes.get(j).equals(userAnswers.get(j))) {
//                score++;
//                System.out.println(cpt + " : Vrai !");
//            } else {
//                System.out.println(cpt + " : Faux ! La bonne réponse est : " + verbes.get(j));
//            }
//        }
//        System.out.println("Vous avez obtenu : "+score+" / "+verbes.size());
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//}
