package net.teamwraith.npctalk.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

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
			// React to the node selection.
			gui.buildNodeFrame(gui.getMainFrame().currentNode().toString(),getGUIListener());
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
					runLines(new File(gui.getNodeFrame().getNodeName()+".wd"));
					System.out.println(speech);
					gui.getNodeFrame().dispose();
				}
			}
		});
		
		gui.getNodeFrame().getSaveButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setSpeech(gui.getNodeFrame().getSpeechField().getText());
				runLines(new File(gui.getNodeFrame().getNodeName()+".wd"));
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
	
	public void runLines(File file) {
		List<String> speechContent = new ArrayList<String>(); 
		String line = null;
		
		for (int i = 0; i < gui.getNodeFrame().getSpeechField().getLineCount(); i++) {
			try {
				int start = gui.getNodeFrame().getSpeechField().getLineStartOffset(i);
				int end = gui.getNodeFrame().getSpeechField().getLineEndOffset(i);
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
	public GUIListener getGUIListener() { return this; }
}
