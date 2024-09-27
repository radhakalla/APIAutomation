package testutility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PayLoadReader {
	
	
	public static String getPayloadFromFile(String fileName) {
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
		+ File.separator + "resources" + File.separator + "payloads" + File.separator+fileName;
		BufferedReader reader;
		StringBuilder responseStrBuilder = null;
		try {
		reader = new BufferedReader(new FileReader(filePath));
		String line = "";
		responseStrBuilder = new StringBuilder();
		while ((line = reader.readLine()) != null) {
		responseStrBuilder.append(line);
		}
		} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return responseStrBuilder.toString();
		}

}
