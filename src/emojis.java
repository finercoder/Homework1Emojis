import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class emojis {

	public static HashMap<String, String> emojiDict = new HashMap<String,String>();

	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);

		// read input and output file names
		String input = reader.next();
		String output = reader.next();
		
		reader.close();

		// Create input stream from input file
		FileInputStream fstream = new FileInputStream(input);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		// Add emojis to the dictionary
		emojiDict.put(":)","🙂");
		emojiDict.put(":(","☹");
		// Add additonal emojis here in the format as in the above
		
		String[] lines = new String[2];
		int length = 0;
		String strLine;
		
		for(int i = 0; (strLine = br.readLine()) != null; i++) {
			if(i == lines.length-1){
				String[] newlines = new String[lines.length*2];
				newlines = lines.clone();
			}
			lines[i] = strLine;
		}
				
		for(int i = 0; i < lines.length; i++) {
			String[] words = lines[i].split(" ");
			for(int j = 0; j <words.length; j++) {
				// replace legacy emojis with unicode emojis
				if(emojiDict.containsKey(words[j])) {
					words[j] = emojiDict.get(words[j]);
				}
			}
			// combine words back into the line
			lines[i] = String.join(" ", words);
		}
		br.close();
		
		PrintWriter writer = new PrintWriter(output,"UTF-16");
		
		for(String s: lines) {
			writer.println(s);
		}
		
		writer.close();
	}
	
}
