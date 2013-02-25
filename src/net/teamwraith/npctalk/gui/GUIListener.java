package net.teamwraith.npctalk.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.tree.DefaultMutableTreeNode;

import net.teamwraith.npctalk.Files;

/**
 * This is what <i>interacts</i> with the GUI;
 * in other words, BuildGUI determines the layout
 * and so on, while <code>GUIListener</code> 
 * determines how the user will interact with it.
 * 
 * @author Stektpotet
 * @author EternalFacepalm
 */
public class GUIListener {
	
	private String speech;
	private GUIBuild gui;
	
	public GUIListener() {
		gui = new GUIBuild();
		
	/**	
		gui.getTree().addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
		        DefaultMutableTreeNode node = (DefaultMutableTreeNode) 
		        	gui.getTree().getLastSelectedPathComponent();

		        /* if nothing is selected */ 
	/**	        if (node == null) return;

		        /* retrieve the node that was selected */ 
	/**			Object nodeInfo = node.getUserObject();
		        /* React to the node selection. */
	/**	        System.out.println("Changed index to: " + nodeInfo);
		        gui.buildNodeFrame();
		        nodeFrameListeners();
			}
		}); */


		}

	public void nodeFrameListeners(){
		
		gui.getSpeechField().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
					
					setSpeech(gui.getSpeechField().getText());
					runLines(new File("TEST01.txt"));
					System.out.println(speech);
					gui.getnodeFrame().dispose();
				}
			}
		});
		gui.getsaveButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setSpeech(gui.getSpeechField().getText());
				runLines(new File("TEST01.txt"));
				System.out.println(speech);
				gui.getnodeFrame().dispose();
			}
		});
	}
	
	
	public void setSpeech(String speech) {
		this.speech = speech;
	}
	
	public String getSpeech() {
		return speech;
	}
	
	public void runLines(File file) {
		
		List<String> speechContent = new ArrayList<String>(); 
		String line = null;
		
		for (int i=0; i < gui.getSpeechField().getLineCount(); i++) {
			try {
				int start = gui.getSpeechField().getLineStartOffset(i);
				int end = gui.getSpeechField().getLineEndOffset(i);
				line = getSpeech().substring(start, end);
				speechContent.add(line);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		
		Files.writeRawFile(
			speechContent.toArray(new String[speechContent.size()]), file
		);
	}
}
