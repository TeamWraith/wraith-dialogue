package net.teamwraith.npctalk.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.BadLocationException;

import net.teamwraith.npctalk.Files;
import net.teamwraith.npctalk.Formatter;

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
	private Formatter formatter;
	
	
	public GUIListener() {
		gui = new GUIBuild();
		formatter = new Formatter(this);
	/**
	 * Listeners for the TreeFrame content.
	 */
/**
 * Comented out because of lack of usage, but, as doubleclick-listeners might be useful, we'll keep it for a while. 
 * 
 * 	gui.getMainFrame().getTree().addMouseListener(new MouseAdapter(){
		public void mouseClicked(MouseEvent evt) {
			JTree tree = (JTree)evt.getSource();
			if (evt.getClickCount() == 2) {

				// React to the node selection.
				gui.buildNodeFrame(gui.getMainFrame().currentNode().toString(),getGUIListener());
				}
			}
        }); 
**/
	gui.getMainFrame().getTree().addKeyListener(new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			// React ENTER when it's pressed and an item is selected in the tree.
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				String name = gui.getMainFrame().currentNode().toString();
				boolean isEnd = false;
				String parent;
				if (gui.getMainFrame().currentNode().isRoot())
					parent = gui.getMainFrame().currentNode().toString();
				else
					parent = gui.getMainFrame().currentNode().getParent().toString();
				String[] actors = null;
				int sceneRow = gui.getMainFrame().currentNode().getLevel();				//TODO Make accurate in case of several on the same level
				int sceneNr = gui.getMainFrame().currentNode().getSiblingCount();		//TODO Make each node have separate number instead of the total count of siblings
				String speech = null;
				gui.buildNodeFrame(name, isEnd, parent, actors, sceneRow, sceneNr, speech, getGUIListener());
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
				enableTreeStuff();
			}
		}
	});
	
	
	/**
	 * Listeners for the enabled-by-default items in the MenuBar for TreeFrame.
	 */
	gui.getMainMenuBar().getNewDialogue().addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			gui.getMainFrame().newTree();
			enableTreeStuff();
			}
		});
	
	gui.getMainMenuBar().getExit().addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			gui.getMainFrame().dispose();
			}
		});
	
	}

	/**
	 * Listeners for the greyed-out items for TreeFrame.
	 */
	public void enableTreeStuff() {
		
		gui.getMainMenuBar().getNewNode().setEnabled(true);
		gui.getMainFrame().getNewNodeBtn().setEnabled(true);
		
		gui.getMainMenuBar().getNewNode().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.getMainFrame().addNode();
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
					formatter.runLines(gui.getNodeFrame().getNodeName());
					System.out.println(speech);
					gui.getNodeFrame().dispose();
				}
			}
		});
		
		gui.getNodeFrame().getSaveButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setSpeech(gui.getNodeFrame().getSpeechField().getText());
				formatter.runLines(gui.getNodeFrame().getNodeName());
				System.out.println(speech);
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
}
