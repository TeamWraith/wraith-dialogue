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
	
	//Used for getting a proper position within the screen.
	final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	final int displayWidth = gd.getDisplayMode().getWidth();
	final int displayHeight = gd.getDisplayMode().getHeight();

	public FrameNode() {
		
		setTitle("Node Edit");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(displayWidth/2 - 256, displayHeight/2 - 320, 640, 360);
		setMinimumSize(new Dimension(640, 120));
		
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

	public JTextArea getSpeechField() {
		return speechField;
	}

	public JButton getSaveButton(){
		return saveButton;
	}


}
