package testCore;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ImportCorpus {

    public ArrayList Corpus(String n) {
        String line = "";
        ArrayList<String> lines = new ArrayList<String>();
        try {
            File filename = new File(n);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            line = br.readLine();
            while (line != null) {
                line = br.readLine();
//                System.out.println(line);
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines; // liste lines contient le contenu du corpus
    }
}
