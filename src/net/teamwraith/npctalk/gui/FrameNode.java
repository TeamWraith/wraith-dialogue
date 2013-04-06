package net.teamwraith.npctalk.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

public class FrameNode extends JFrame {

	//panels within nodeFrame
	private JPanel infoPanel = new JPanel();;
	private JScrollPane speechPanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);;
		
	//infoPanel - content
	private JCheckBox endCheck;
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
		
	//
	private SpeechNode currentNode;
		
	
	
		
	//Used for getting a proper position within the screen.
	final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	final int displayWidth = gd.getDisplayMode().getWidth();
	final int displayHeight = gd.getDisplayMode().getHeight();

	public FrameNode(GUIListener guiListener) {		//TODO Make SpeechNode contain these next all the info under
		currentNode = guiListener.getGUI().getMainFrame().getCurrentNode();
		
		int i;
		if (currentNode.getParent() == null) {i = 0;}
		else {i = currentNode.getParent().getIndex(currentNode) + 1;}
		
		loadNode( currentNode.toString()
				, currentNode.isEnd()
				, currentNode.getResponse()
				, currentNode.getActor()
				, currentNode.getCurrentChoiceNode()
				, i
				, currentNode.getSpeech());
	}
	
	public void loadNode(String name, boolean isEnd, String response, String actor,int currentChoice,int responseNr, String speech){
		
		nodeName = name;
		setTitle("Node Edit - "+nodeName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(displayWidth/2 - 256, displayHeight/2 - 320, 640, 360);
		setMinimumSize(new Dimension(640, 120));
		
		//infoPanel - content
		
		endCheck = new JCheckBox("is end", isEnd);
			
		parentLab = new JLabel("Parent: ");
		if (response == null) {
				
			if (currentNode.getParent() == null) 
			{parentField = new JTextField(currentNode.toString(),15);}
			else 
			{parentField = new JTextField(currentNode.getParent().toString(),15);}
			
		}
		else {parentField = new JTextField(response, 15);}
		
		actorLab = new JLabel("Actors: ");
		if (actor == null) {actorField = new JTextField(15);}
		else {actorField = new JTextField(actor, 15);}
		
		sceneLab = new JLabel("Scene: " + currentChoice +" - "+ responseNr); //Later, have parameters or a method giving in the scenenumber
		
		saveButton = new JButton("Save Node");
		
		//speechPanel - textArea
		if (speech == null) {speechField = new JTextArea();}
		else {speechField = new JTextArea(speech);}
			
		//Adds panels to node nodeFrame
		add(infoPanel, BorderLayout.PAGE_START);
		add(speechPanel, BorderLayout.CENTER);
		add(saveButton, BorderLayout.PAGE_END);
		
		//Adds content to infoPanel
		infoPanel.add(endCheck, BorderLayout.NORTH);
		
		infoPanel.add(parentLab, BorderLayout.NORTH);
		infoPanel.add(parentField, BorderLayout.NORTH);
		
		infoPanel.add(actorLab, BorderLayout.NORTH);
		infoPanel.add(actorField, BorderLayout.NORTH);
		
		infoPanel.add(sceneLab, BorderLayout.NORTH);
		
		//Adds textArea to speechPanel
		speechPanel.setViewportView(getSpeechField());
		setVisible(true);	

	}

	public JCheckBox getEndCheck() {
		return endCheck;
	}

	public JTextField getParentField() {
		return parentField;
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
	
	public SpeechNode getCurrentNode() {
		return currentNode;
	}
	
	

}
