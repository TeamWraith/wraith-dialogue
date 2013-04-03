package net.teamwraith.npctalk.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarTree extends JMenuBar {
	private JMenu file = new JMenu("File");
	private JMenuItem newDialogue =  new JMenuItem("New dialogue"); 
	private JMenuItem exit =  new JMenuItem("Exit"); 
	
	public MenuBarTree() {
		add(file);
		file.add(newDialogue);
		file.add(exit);
	}
	
	
	public JMenu getFile() {
		return file;
	}

	public JMenuItem getNewDialogue() {
		return newDialogue;
	}

	public JMenuItem getExit() {
		return exit;
	}


}


