package net.teamwraith.npctalk.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarTree extends JMenuBar {
	
	private JMenu file = new JMenu("File");
	private JMenu help = new JMenu("Help");
	
	// File menu
	private JMenuItem newDialogue =  new JMenuItem("New dialogue"); 
	private JMenuItem newNode = new JMenuItem("New Node");
	private JMenuItem exit =  new JMenuItem("Exit"); 
	
	// Help menu
	private JMenuItem about = new JMenuItem("About...");
	
	public MenuBarTree() {
		
		newNode.setEnabled(false);
		
		add(file);
		file.add(newDialogue);
		file.add(newNode);
		file.add(exit);
		add(help);
		help.add(about);
	}
	
	public JMenuItem getNewDialogue() { return newDialogue; }

	public JMenuItem getNewNode() { return newNode; }
	
	public JMenuItem getExit() { return exit; }
	
	public JMenuItem getAbout() { return about; }
	
	

}


