package testCore;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.CSVReader;



public class Opencsv {
	public static void main(String[] args) throws IOException, CsvValidationException {
		
		HashMap<String,String> Mots = new HashMap<String,String>();
		List<List<String>> records = new ArrayList<List<String>>();
		List<String> line = new ArrayList<String>();
		 String[] values = null;
		    String keys = null;
		    String value = null;
		    String key = null;
		try (CSVReader csvReader = new CSVReader(new FileReader("ABU.csv"));) { //D:\Desktop\alao_java\ABU.csv
		   
				while ((values = csvReader.readNext()) != null) {
					line = Arrays.asList(values);
				    keys = line.get(0);
				    String arr[] = keys.split("\\s+");
				    key=arr[0];
				    value=arr[1];
				    Mots.put(key, value);
				    line = null;
			
		}
				System.out.println(Mots.get("ont"));
		}
	}
}
	   

	

