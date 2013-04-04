package net.teamwraith.npctalk.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FrameNode extends JFrame {

	//panels within nodeFrame
		private JPanel infoPanel = new JPanel();;
		private JScrollPane speechPanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);;
		
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

	//name of the node that is beeing edited
		private String nodeName;
		

		
	//Used for getting a proper position within the screen.
	final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	final int displayWidth = gd.getDisplayMode().getWidth();
	final int displayHeight = gd.getDisplayMode().getHeight();

	public FrameNode(String name){
		loadNode(name, false, null, null, 1, 1, null);
	}
	
	public FrameNode(String name, boolean isEnd, String parent, String[] actors,int sceneRow,int sceneNr, String speech) {
		loadNode(name, isEnd, parent, actors, sceneRow, sceneNr, speech);
	}
	
	public void loadNode(String name, boolean isEnd, String parent, String[] actors,int sceneRow,int sceneNr, String speech){
		
		nodeName = name;
		setTitle("Node Edit - "+nodeName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(displayWidth/2 - 256, displayHeight/2 - 320, 640, 360);
		setMinimumSize(new Dimension(640, 120));
		
		//infoPanel - content
			branchCheck = new JCheckBox("is end", isEnd);
			
			parentLab = new JLabel("Parent: ");
			parentField = new JTextField(parent, 15);
			parentField.setEditable(false);
			
			actorLab = new JLabel("Actors: ");
			
			if (actors == null) {actorField = new JTextField(15);}
			else {actorField = new JTextField(actors.toString(), 15);} //TODO separate actors with commas.
			
			sceneLab = new JLabel("Scene: " + sceneRow +" - "+ sceneNr); //Later, have parameters or a method giving in the scenenumber
			
			saveButton = new JButton("Save Node");
		
		//speechPanel - textArea
			speechField = new JTextArea(speech);
			
		//Adds panels to node nodeFrame
			add(infoPanel, BorderLayout.PAGE_START);
			add(speechPanel, BorderLayout.CENTER);
			add(saveButton, BorderLayout.PAGE_END);
		
		//Adds content to infoPanel
			infoPanel.add(branchCheck, BorderLayout.NORTH);
			
			infoPanel.add(parentLab, BorderLayout.NORTH);
			infoPanel.add(parentField, BorderLayout.NORTH);
			
			infoPanel.add(actorLab, BorderLayout.NORTH);
			infoPanel.add(actorField, BorderLayout.NORTH);
			
			infoPanel.add(sceneLab, BorderLayout.NORTH);
		
		//Adds textArea to speechPanel
			speechPanel.setViewportView(getSpeechField());
			
			setVisible(true);	
	}

	public JTextField getActorField() {
		return actorField;
	}

	public JTextArea getSpeechField() {
		return speechField;
	}

	public JButton getSaveButton(){
		return saveButton;
	}
	
	public String getNodeName() {
		return nodeName;
	}
	

}
