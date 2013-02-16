package net.teamwraith.npctalk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Files {
	
	public static void writeRawFile (String str, String fileName) {
		writeRawFile(str, new File(fileName + ".txt"));
	}
	
	public static void writeRawFile (String str, File file) {
		BufferedWriter writer;
		
		
		
		
		
		
		
		
		try {
			writer = new BufferedWriter(new FileWriter(file));
			
			file.createNewFile();
			writer.append(str + "\n");
			//FOR DEBUGGING
			//System.out.println("wrote in file: " + file.getAbsolutePath());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
