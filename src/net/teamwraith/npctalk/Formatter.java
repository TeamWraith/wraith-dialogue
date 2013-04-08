package net.teamwraith.npctalk;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.teamwraith.npctalk.gui.GUIListener;
import net.teamwraith.npctalk.gui.SpeechNode;

public class Formatter {
	
	private GUIListener guiListener;
	private List<String> speechContent = new ArrayList<String>(); 
	private SpeechNode currentNode;
	private int lastChoiceNode = 0;
	private String dialogueName = null;

	/**
	 * The formatter class will format the current dialogue's into one
	 * readable document. 
	 * 
	 * @param guiListener - gets the current GUIListener so that the 
	 * formatter from there can read the info needed. This should always
	 * be the GUIListener the program is using at the moment.
	 */
	public Formatter(GUIListener guiListener) {
		this.guiListener = guiListener;
	}
	
	/**
	 * Will run the lines within the dialogue through
	 * the formatting process.
	 * 
	 * @param fileName - Sets the output-file's filename.
	 */
	public void runLines(String fileName) {
		File file = new File(fileName +".wd");
		dialogueName = fileName;
		for (int i=0; i < guiListener.getGUI().getMainFrame().getNodeCount(); i++) {
			currentNode = guiListener.getGUI().getMainFrame().getNodes()[i];
			
			
			if (lastChoiceNode < currentNode.getCurrentChoiceNode()) {
				addChoices();
			}
			addResponse();
			if (!(currentNode.getSpeech() == null)) {
				addActor();
				addSpeech();
			}
			if (currentNode.isEnd()) {
				addEnd();
			}
			addReturn();
			lastChoiceNode = currentNode.getCurrentChoiceNode();
		}
		Files.writeRawFile(
			speechContent.toArray(new String[speechContent.size()]), file
		);
		speechContent = null;
	}
	/**
	 * Adds "choice split-up" in the dialogue.
	 */
	private void addChoices() {
		String line = "CHOICES [" + (lastChoiceNode+1) + "]";
		speechContent.add(line);
	}
	
	/**
	 * Adds the scene declaration and the player's response 
	 * (to the choice) in the dialogue.
	 */
	private void addResponse() {
		String line = null;
		if (currentNode.isRoot()) {
			line = "Dialogue: " + dialogueName +"\r\n";
		}
		else {
			line = "\t[" + currentNode.getCurrentChoiceNode() + " - " + (currentNode.getParent().getIndex(currentNode)+1) + "] " + 
			currentNode.getResponse()  + " {\r\n";
		}
		speechContent.add(line);
	}
	/**
	 * Adds the actor's, whom the player is talking to, name (Uppercased).
	 */
	private void addActor() {
		String line = null;
		if (currentNode.getActor() == null) {
			line = "\tUNNAMED:";
		} else {
			line = "\t" + currentNode.getActor().toUpperCase() + ":";
		}
		speechContent.add(line);
	}
	/**
	 * Adds the actor's speech to the dialogue.
	 */
	private void addSpeech() {
				speechContent.add(currentNode.getFormattedSpeech());
	}
	/**
	 * Adds the return value that declears which "choice split-up" 
	 * the player will end up in after his last choice.
	 */
	private void addReturn() {
		
		for (int i = 0; i < guiListener.getGUI().getMainFrame().getNodeCount();i++) {
			
		}
		String line = null;
		if (currentNode.isLeaf()) {line = "RETURN [" + currentNode.getCurrentChoiceNode() + "]";}
		else {line = "RETURN [" + (currentNode.getCurrentChoiceNode()+1) + "]"+ "\r\n}";}
		speechContent.add(line);
	}
	
	/**
	 * Used for dialogue nodes that ends the dialogue.
	 */
	private void addEnd() {
		speechContent.add("END\r\n");
	}

}