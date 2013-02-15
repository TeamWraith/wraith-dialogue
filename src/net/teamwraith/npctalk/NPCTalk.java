package net.teamwraith.npctalk;

import java.io.IOException;
import java.util.Scanner;

import net.teamwraith.npctalk.gui.GUIListener;

public class NPCTalk {

	public static void main(String[] args) {
		GUIListener app = new GUIListener();
		
		String x = "DERP";
		Character test = new Character();
		
		System.out.println(app.getSpeech());
	}
	
}
