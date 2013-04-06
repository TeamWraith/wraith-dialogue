package net.teamwraith.npctalk.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FrameAbout extends JFrame {

	public FrameAbout() {
		setTitle("About WraithDialogue");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel info = new JLabel();
		// Read this from a file later on. Because seriously.
		info.setText(
			"<html><body style = 'width:100%'>" +
			"WraithDialogue is an entirely free program written in Java that allows writers to easily create nonlinear conversations. It'll also allow for saved characters, meaning that you'll be able to have a repository of all the story's characters.\n" +
			"The program is currently being developed for the project \"Lands of Loriana\"; it'll later be modified for public use.</body></html>"
		);
		add(info);
		
		// Set the window's location
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(300, 200);
		setLocation(
			(dimension.width - getWidth()) / 2, (dimension.height - getHeight()) / 2
		);
		
		setVisible(true);
	}
	
}
