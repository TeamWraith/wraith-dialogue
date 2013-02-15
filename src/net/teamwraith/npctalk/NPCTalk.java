package net.teamwraith.npctalk;

import java.io.IOException;
import java.util.Scanner;

public class NPCTalk {



	public static void main(String[] args) {

		AppGUI App = new AppGUI();
		
		String x = "DERP";
		Files files = new Files();
		infoReadify readyInfo = new infoReadify();
		InfoReadable readableInfo = new InfoReadable();

		readyInfo.pull(readableInfo);
		try {
			files.fileCreate(x);
		} catch (IOException e) {
			e.printStackTrace();
		}
		App.AppGUI();
		System.out.println(App.getSpeech());
		
	}
}
