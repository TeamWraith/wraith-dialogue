package net.teamwraith.npctalk.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This is what <i>interacts</i> with the GUI;
 * in other words, BuildGUI determines the layout
 * and so on, while <code>GUIListener</code> 
 * determines how the user will interact with it.
 * 
 * @author Stektpotet
 * @author EternalFacepalm
 */
public class GUIListener {
	
	private String speech;
	private GUIBuild gui;
	
	public GUIListener() {
		gui = new GUIBuild();
		
		gui.getSpeechField().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
					speech = gui.getSpeechField().getText();
					
					System.out.println(speech);
				}
			}
		});
	}
	
	public void setSpeech(String speech){
		this.speech = speech;
	}
	
	public String getSpeech(){
		return speech;
	}
	
}
