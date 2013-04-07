package net.teamwraith.npctalk;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.BadLocationException;

import net.teamwraith.npctalk.gui.GUIListener;
import net.teamwraith.npctalk.gui.SpeechNode;

public class Formatter {
	
	private GUIListener guiListener;
	private List<String> speechContent = new ArrayList<String>(); 
	private SpeechNode currentNode;

	public Formatter(GUIListener guiListener) {
		this.guiListener = guiListener;
	}
	
	public void runLines(String fileName) {
		File file = new File(fileName +".wd");
		
		for (int i=0; i < guiListener.getGUI().getMainFrame().getNodeCount(); i++) {
			currentNode = guiListener.getGUI().getMainFrame().getNodes()[i];
		}
		addResponse();
		if (!isSpeechEmpty()) {
			addActor();
			addSpeech();
		}
		addReturn();
		Files.writeRawFile(
			speechContent.toArray(new String[speechContent.size()]), file
		);
		speechContent = null;
	}

	private void addResponse() {
		String line = null;
		if (currentNode.isRoot()) {
			line = "Dialogue: " + currentNode.toString()+"\r\n";
		}
		else {
			line = "\t[" + currentNode.getCurrentChoiceNode() + " - " + currentRespondNr() + "] " + 
			currentNode.getParent().toString()  + " {\r\n";	//TODO make it right via using scenename later.
		}
		
		speechContent.add(line);
	}

	private void addActor() {
		String line = null;
		if (currentNode.getActor().isEmpty()) {
			line = "\tUNNAMED:";
		} else {
			line = "\t" + currentNode.getActor().toUpperCase() + ":";
		}
		speechContent.add(line);
	}

	private void addSpeech() {
				speechContent.add(currentNode.getFormattedSpeech());
	}
	
	private void addReturn() {
		
		for (int i = 0; i < guiListener.getGUI().getMainFrame().getNodeCount();i++) {
			
		}
		String line = null;
		if (currentNode.isLeaf()) {line = "RETURN [" + currentNode.getCurrentChoiceNode() + "]";}
		else {line = "RETURN [" + (currentNode.getCurrentChoiceNode()+1) + "]";}
		speechContent.add(line);
	}
	
	private boolean isSpeechEmpty() {
		return currentNode.getSpeech().isEmpty();
	}
	
	private int currentRespondNr() {
		return currentNode.getParent().getIndex(currentNode) + 1;
	}
	
}