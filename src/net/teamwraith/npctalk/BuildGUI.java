package net.teamwraith.npctalk;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BuildGUI {
	
	private BuildGUI gui = this;
	
	private JFrame window;
	private JTextArea speechField;
	private JTextField nameField;
	private JPanel infoPanel;
	private JScrollPane speechPanel;
	
	public BuildGUI(){
	
	window = new JFrame("Talk Creator");
	infoPanel = new JPanel();
	nameField = new JTextField("Unnamed" /*** + NUMBER OF UNNAMED PERSON? ***/ , 20);
	speechField = new JTextArea(25, 60);
	speechPanel = new JScrollPane(speechField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	speechPanel.setSize(640, 360);
	infoPanel.add(nameField, BorderLayout.CENTER);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.add(infoPanel, BorderLayout.PAGE_START);
	window.add(speechField, BorderLayout.AFTER_LAST_LINE);
	window.pack();
	window.setVisible(true);
	}

	
	
	
	
	
	
	
	
	
	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}

	public JTextArea getSpeechField() {
		return speechField;
	}

	public void setSpeechField(JTextArea speechField) {
		this.speechField = speechField;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public void setNameField(JTextField nameField) {
		this.nameField = nameField;
	}

	public JPanel getInfoPanel() {
		return infoPanel;
	}

	public void setInfoPanel(JPanel infoPanel) {
		this.infoPanel = infoPanel;
	}

	public JScrollPane getSpeechPanel() {
		return speechPanel;
	}

	public void setSpeechPanel(JScrollPane speechPanel) {
		this.speechPanel = speechPanel;
	}
	
	
	
}
