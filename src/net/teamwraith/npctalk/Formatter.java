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

	public Formatter(GUIListener guiListener) {
		this.guiListener = guiListener;
	}
	
	public void runLines(String fileName) {
		File file = new File(fileName +".wd");
		
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

	private void addChoices() {
		String line = "CHOICES [" + (lastChoiceNode+1) + "]";
		speechContent.add(line);
	}

	private void addResponse() {
		String line = null;
		if (currentNode.isRoot()) {
			line = "Dialogue: " + "THIS TODO"+"\r\n";
		}
		else {
			line = "\t[" + currentNode.getCurrentChoiceNode() + " - " + currentRespondNr() + "] " + 
			currentNode.getResponse()  + " {\r\n";
		}
		speechContent.add(line);
	}

	private void addActor() {
		String line = null;
		if (currentNode.getActor() == null) {
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
		else {line = "RETURN [" + (currentNode.getCurrentChoiceNode()+1) + "]"+ "\r\n}";}
		speechContent.add(line);
	}
	
	private void addEnd() {
		speechContent.add("END\r\n");
	}
	private int currentRespondNr() {
		return currentNode.getParent().getIndex(currentNode) + 1;
	}
	
}