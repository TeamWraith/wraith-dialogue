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

@SuppressWarnings("serial")
public class FrameNode extends JFrame {

	//panels within nodeFrame
	private JPanel infoPanel = new JPanel();
	private JScrollPane speechPanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);;
		
	//infoPanel - content
	private JCheckBox endCheck;
	private JLabel responseLab;
	private JLabel actorLab;
	private JLabel sceneLab;
	private JTextField responseField;
	private JTextField actorField;
		
	//speechPanel - textArea
	private JTextArea speechField;
		
	//save node button
	private JButton saveButton;

	//name of the node that is being edited
	private String nodeName;
	
	private SpeechNode currentNode;
		
	//Used for getting a proper position within the screen.
	final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	final int displayWidth = gd.getDisplayMode().getWidth();
	final int displayHeight = gd.getDisplayMode().getHeight();

	public FrameNode(GUIListener guiListener) {
		currentNode = guiListener.getGUI().getMainFrame().getCurrentNode();
		nodeName = currentNode.toString();
		int responseNr;
		
		setTitle("Node Edit - "+nodeName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(displayWidth/2 - 256, displayHeight/2 - 320, 660, 360);
		setMinimumSize(new Dimension(660, 120));
		
		// infoPanel - content
		endCheck = new JCheckBox("Dialogue end.", currentNode.isEnd()); //TODO Make this disable/enable childs for its node.
		
		// declare parent
		responseLab = new JLabel("Response: ");
		if (currentNode.getResponse() == null) {
			responseField = new JTextField(15);
		} else {
			responseField = new JTextField(currentNode.getResponse(), 15);
		}
		
		if (currentNode.isRoot()) {
			responseNr = 0;
			responseField.setEditable(false);
			responseField.setToolTipText("Players are not supposed to invoke a dialogue with words.");
		} else {
			responseNr = currentNode.getParent().getIndex(currentNode) + 1;
			responseField.setToolTipText("The player's response invoking this part of the dialogue.");
		}
		
		actorLab = new JLabel("Actor: ");
		if (currentNode.getActor() == null) {
			actorField = new JTextField(15);
		} else {
			actorField = new JTextField(currentNode.getActor(), 15);
		}
		sceneLab = new JLabel("Scene: " + currentNode.getCurrentChoiceNode() +" - "+ responseNr); 
		
		saveButton = new JButton("Save Node");
		
		//speechPanel - textArea
		if (currentNode.getSpeech() == null) {
			speechField = new JTextArea();
		} else {
			speechField = new JTextArea(currentNode.getSpeech());
		}
		
		endCheck.setToolTipText("If checked this node will end the dialogue (And disable branching from this node).");
		actorField.setToolTipText("The actor that answers to the player's response.");
		sceneLab.setToolTipText("The current scene; dialogue branch nr. - response nr.");
		speechField.setToolTipText("What the actor will say if the player invoke this part of the dialogue with his respose.");
		
		//Adds panels to node nodeFrame
		add(infoPanel, BorderLayout.PAGE_START);
		add(speechPanel, BorderLayout.CENTER);
		add(saveButton, BorderLayout.PAGE_END);
		
		//Adds content to infoPanel
		infoPanel.add(endCheck, BorderLayout.NORTH);
		
		infoPanel.add(responseLab, BorderLayout.NORTH);
		infoPanel.add(responseField, BorderLayout.NORTH);
		
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

	public JTextField getResponseField() {
		return responseField;
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
