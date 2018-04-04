package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextLoader {

	public static String load(String filename) throws FileNotFoundException {
		Scanner fin =new Scanner(new File(filename));
		
		StringBuilder sb = new StringBuilder();
		while(fin.hasNextLine()) {
			sb.append(fin.nextLine().trim());
		}
		fin.close();
		return sb.toString();
				
	}
}
