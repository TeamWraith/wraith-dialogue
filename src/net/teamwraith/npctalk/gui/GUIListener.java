package net.teamwraith.npctalk.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.BadLocationException;

import net.teamwraith.npctalk.Formatter;

/**
 * This is what <i>interacts</i> with the GUI;
 * in other words, BuildGUI determines the layout
 * and so on, while <code>GUIListener</code> 
 * determines how the user will interact with it.
 * 
 * @author Stektpotet
 * @author EternalFacepalm
 * 
 * TODO Make event methods or possibly even own classes
 */
public class GUIListener {
	
	private String speech;
	private GUIBuild gui;
	private Formatter formatter;
	
	public GUIListener() {
		gui = new GUIBuild();
		formatter = new Formatter(getGUIListener());
		
		gui.getMainFrame().getTree().addKeyListener(new KeyAdapter() { //TODO check possibilities for double-clicking once more
			public void keyPressed(KeyEvent e) {
				// React ENTER when it's pressed and an item is selected in the tree.
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					gui.buildNodeFrame(getGUIListener());
				}
				else if (e.getKeyCode() == KeyEvent.VK_N && e.isControlDown()) {
					gui.getMainFrame().addNode();
				}
			}
		});
	
	
		/**
		 * Shortcut for creating a new dialogue, TODO Have a look into KeyEventDispatchers & KeyboardFocusManager.
		 */
		gui.getMainFrame().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				gui.getMainFrame().setFocusable(true);
				if (e.getKeyCode() == KeyEvent.VK_T && e.isControlDown()) {
					gui.getMainFrame().newTree();
					enableTreeListeners();
				}
			}
		});
		
		/**
		 * Listeners for the enabled-by-default items in the MenuBar for TreeFrame.
		 */
		gui.getMainMenuBar().getNewDialogue().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.getMainFrame().newTree();
				enableTreeListeners();
			}
		});
		
		gui.getMainMenuBar().getExit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.getMainFrame().dispose();
			}
		});
		
		gui.getMainMenuBar().getAbout().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrameAbout();
			}
		});
	}

	/**
	 * Listeners for the items that require a tree in the TreeFrame.
	 */
	public void enableTreeListeners() {
		gui.getMainMenuBar().getNewNode().setEnabled(true);
		gui.getMainMenuBar().getSaveDialogue().setEnabled(true);
		gui.getMainFrame().getNewNodeBtn().setEnabled(true);
		
		gui.getMainMenuBar().getNewNode().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.getMainFrame().addNode();
			}
		});
		
		gui.getMainMenuBar().getSaveDialogue().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formatter.runLines(gui.getMainFrame().getTitle().toString().substring(18));
			}
		});
		
		gui.getMainFrame().getNewNodeBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.getMainFrame().addNode();
			}
		});
	}
	
	/**
	 * Listeners for the content in NodeFrames.
	 */
	public void enableNodeFrameListeners() {
		gui.getNodeFrame().getSpeechField().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
					setSpeech(gui.getNodeFrame().getSpeechField().getText());
					gui.getNodeFrame().getCurrentNode().assignInfo(
							gui.getNodeFrame().getEndCheck().isSelected(),
							gui.getNodeFrame().getResponseField().getText(),
							gui.getNodeFrame().getActorField().getText(),
							gui.getNodeFrame().getSpeechField().getText(),
							formattedSpeech());
					gui.getNodeFrame().dispose();

				}
			}
		});
		
		gui.getNodeFrame().getSaveButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSpeech(gui.getNodeFrame().getSpeechField().getText());
				gui.getNodeFrame().getCurrentNode().assignInfo(
						gui.getNodeFrame().getEndCheck().isSelected(),
						gui.getNodeFrame().getResponseField().getText(),
						gui.getNodeFrame().getActorField().getText(),
						gui.getNodeFrame().getSpeechField().getText(),
						formattedSpeech());
				gui.getNodeFrame().dispose();
			}
		});
	}
	
	
	public void setSpeech(String speech) {
		this.speech = speech;
	}
	
	public String getSpeech() {
		return speech;
	}
	
	public GUIListener getGUIListener() { return this; }

	public GUIBuild getGUI() { return gui; }
	
	public String formattedSpeech() {
		List<String> speechContent = new ArrayList<String>(); 
		for (int i = 0; i < gui.getNodeFrame().getSpeechField().getLineCount(); i++) {
			try {
				int start = gui.getNodeFrame().getSpeechField().getLineStartOffset(i);
				int end = gui.getNodeFrame().getSpeechField().getLineEndOffset(i);
				speechContent.add(getGUIListener().getSpeech().substring(start, end));
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		String[] speechArray = speechContent.toArray(new String[speechContent.size()]);
		String formattedSpeech = "\t\t";
		for(int i = 0; i < speechArray.length; i++) {
			formattedSpeech = formattedSpeech + speechArray[i] + "\r\n";
		}
		return formattedSpeech;
	}
	
}