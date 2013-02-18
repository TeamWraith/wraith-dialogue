package net.teamwraith.npctalk.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;


import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Builds the GUI.
 */
public class GUIBuild {
	
	//node window
	private JFrame window;
	
	//panels within the node window
	private JPanel infoPanel;
	private JScrollPane speechPanel;
	
	//infoPanel - content
	private JCheckBox branchCheck;
	private JLabel parentLab;
	private JLabel actorLab;
	private JLabel sceneLab;
	private JTextField parentField;
	private JTextField actorField;
	
	//speechPanel - textArea
	private JTextArea speechField;
	
	private JTree tree;
	private String[] data = { "one", "two", "three", "four" };
	
	public GUIBuild(){
		window = new JFrame("Wraith Dialogue");
		tree = new JTree(data);
		
		window.add(tree, BorderLayout.CENTER);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(840, 560);
		window.setVisible(true);		
	}
	
	public JTree getTree() {
		return tree;
	}

	public void setTree(JTree tree) {
		this.tree = tree;
	}

	public void buildNodeFrame() {
		//node window
		window = new JFrame("Node Edit");

		//panels within the node window
		infoPanel = new JPanel();
		speechPanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//infoPanel - content
		branchCheck = new JCheckBox("is brancher");
		
		parentLab = new JLabel("Parent: ");
		parentField = new JTextField("ParentSpeech", 15);
		
		actorLab = new JLabel("Actor: ");
		actorField = new JTextField("Actor", 15);
		
		sceneLab = new JLabel("Scene: " + "derp.01"); //Later, have a method giving in the sceneinfo
		
		//speechPanel - textArea
		speechField = new JTextArea();
		
		//Adds panels to node window
		window.add(infoPanel, BorderLayout.PAGE_START);
		window.add(speechPanel, BorderLayout.CENTER);
		
		//Adds content to infoPanel
		infoPanel.add(branchCheck, BorderLayout.NORTH);
		
		infoPanel.add(parentLab, BorderLayout.NORTH);
		infoPanel.add(parentField, BorderLayout.NORTH);
		
		infoPanel.add(actorLab, BorderLayout.NORTH);
		infoPanel.add(actorField, BorderLayout.NORTH);
		
		infoPanel.add(sceneLab, BorderLayout.NORTH);
		
		//Adds textArea to speechPanel
		speechPanel.setViewportView(speechField);

		window.setMinimumSize(new Dimension(640, 120));
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setSize(640, 360);
		window.setVisible(true);	
	}
	
	public boolean isNodeEditFocused() {
		return window.getTitle() == "Node Edit" && window.isFocused();
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
		return actorField;
	}

	public void setNameField(JTextField nameField) {
		this.actorField = nameField;
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
