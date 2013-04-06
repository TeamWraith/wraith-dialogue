package net.teamwraith.npctalk.gui;

import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class MenuBarTree extends JMenuBar {
	
	private JMenu file = new JMenu("File");
	private JMenu help = new JMenu("Help");
	
	// File menu
	private JMenuItem newDialogue =  new JMenuItem("New Dialogue"); 
	private JMenuItem newNode = new JMenuItem("New Node");
	
	private JMenuItem refresh =  new JMenuItem("Refresh");
	private JMenuItem saveDialogue = new JMenuItem("Save Dialogue");
	
	private JMenuItem exit =  new JMenuItem("Exit"); 
	
	// Help menu
	private JMenuItem about = new JMenuItem("About WraithDialogue");
	
	public MenuBarTree() {
		
		newNode.setEnabled(false);
				
		add(file);
		file.add(newDialogue);
		file.add(newNode);
		file.add(new MenuSeparator());
		file.add(refresh);
		file.add(saveDialogue);
		file.add(new MenuSeparator());
		file.add(exit);
		add(help);
		help.add(about);
	}
	
	public JMenuItem getNewDialogue() { return newDialogue; }

	public JMenuItem getNewNode() { return newNode; }
	
	public JMenuItem getRefresh() { return refresh; }
	
	public JMenuItem getExit() { return exit; }
	
	public JMenuItem getAbout() { return about; }
	
	/* separates menu, duh */
	public class MenuSeparator extends JSeparator {
		
		public MenuSeparator() {
			setForeground(Color.LIGHT_GRAY);
		}
		
	}

}


