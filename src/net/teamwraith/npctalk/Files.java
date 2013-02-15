package net.teamwraith.npctalk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

public class Files {
	private FileWriter file;
	private BufferedWriter writer;
	
	public void fileCreate(String fileName) throws IOException{
		
		File existingFile = new File(fileName + ".txt");
		
		
		if (existingFile.exists()){
			System.out.println("Already Exists in " + existingFile.getAbsolutePath());
			return;
		}
		else{
			try {
				file = new FileWriter(fileName + ".txt");
				System.out.println(existingFile.getName() + " Created!");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}	
	}

	public void fileWrite(int line, String content){
		writer = new BufferedWriter(file);
	}
	public void fileClose(){
		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fileParagraph(){
		try {
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fileBrancher(){
		
	}
}
