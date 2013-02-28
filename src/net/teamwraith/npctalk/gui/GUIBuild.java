package net.teamwraith.npctalk.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Builds the GUI.
 */
public class GUIBuild {
	
	//main window
	private JFrame mainFrame;
	
	private JMenuBar mainFrameMenuBar;
	
	private JMenu file = new JMenu("File");
	
	private JMenuItem newDialogue =  new JMenuItem("New dialogue"); 
	private JMenuItem exit =  new JMenuItem("Exit"); 

	private List<String> treeData = new ArrayList<String>();
	private String rootName;
	
	private DialogueTree tree = new DialogueTree();
	
	
	//node window
	private JFrame nodeFrame;
	
	//panels within nodeFrame
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
	
	
	//save node button
	private JButton saveButton;
	
	public GUIBuild(){
		
		mainFrame = new JFrame("Wraith Dialogue");
		
		mainFrameMenuBar = new JMenuBar();
		
		tree.createAndShowTree(mainFrame);
		
		mainFrame.setJMenuBar(mainFrameMenuBar);
		mainFrameMenuBar.add(file);
		file.add(newDialogue);
		file.add(exit);

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(840, 560);
		mainFrame.setVisible(true);
		
		
		newDialogue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildNodeFrame();
			}
		});
		
	}
	
	
	
	public void buildNodeFrame() {
		//node window
		nodeFrame = new JFrame("Node Edit");

		//panels within the node nodeFrame
		infoPanel = new JPanel();
		speechPanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//infoPanel - content
		branchCheck = new JCheckBox("is brancher");
		
		parentLab = new JLabel("Parent: ");
		parentField = new JTextField("ParentSpeech", 15);
		
		actorLab = new JLabel("Actor: ");
		actorField = new JTextField("Actor", 15);
		
		sceneLab = new JLabel("Scene: " + "derp.01"); //Later, have parameters or a method giving in the scenenumber
		
		saveButton = new JButton("Save Node");
		//speechPanel - textArea
		speechField = new JTextArea();
		
		//Adds panels to node nodeFrame
		nodeFrame.add(infoPanel, BorderLayout.PAGE_START);
		nodeFrame.add(speechPanel, BorderLayout.CENTER);
		nodeFrame.add(saveButton, BorderLayout.PAGE_END);
		
		//Adds content to infoPanel
		infoPanel.add(branchCheck, BorderLayout.NORTH);
		
		infoPanel.add(parentLab, BorderLayout.NORTH);
		infoPanel.add(parentField, BorderLayout.NORTH);
		
		infoPanel.add(actorLab, BorderLayout.NORTH);
		infoPanel.add(actorField, BorderLayout.NORTH);
		
		infoPanel.add(sceneLab, BorderLayout.NORTH);
		
		//Adds textArea to speechPanel
		speechPanel.setViewportView(speechField);

		nodeFrame.setMinimumSize(new Dimension(640, 120));
		nodeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		nodeFrame.setSize(640, 360);
		nodeFrame.setVisible(true);	
	}
	
	
	public DialogueTree getDialogueTree(){
		return tree;
	}
	
	public JButton getsaveButton() {
		return saveButton;
	}

	public void setsaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}
	
	public JFrame getnodeFrame() {
		return nodeFrame;
	}

	public void setnodeFrame(JFrame nodeFrame) {
		this.nodeFrame = nodeFrame;
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

	public boolean isNodeEditFocused() {
		return nodeFrame.isFocused();
	}
	
	
}
