package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class batch {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
//		
//		ProcessBuilder processBuilder = new ProcessBuilder();
//		processBuilder.command("cmd", "/c", "echo", "batch.bat").directory(new File("./utils"));
//		Process process = processBuilder.start();
		
		
		String command = "cmd /c start ./utils/batch.bat";
		 
		try {
		    Process process = Runtime.getRuntime().exec(command);
		 
		    BufferedReader reader = new BufferedReader(
		            new InputStreamReader(process.getInputStream()));
		    String line;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		       
		    }
		 
		    reader.close();
		 
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

}
