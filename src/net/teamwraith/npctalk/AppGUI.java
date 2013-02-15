package net.teamwraith.npctalk;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AppGUI {
	
	private String speech;
	private BuildGUI gui;
	public void AppGUI(){
		gui = new BuildGUI();
		
		
		gui.getSpeechField().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((e.isControlDown()&& e.getKeyCode() == KeyEvent.VK_S)) {
					setSpeech(gui.getSpeechField().getText());
					
					System.out.println(getSpeech());
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
