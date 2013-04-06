package net.teamwraith.npctalk;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.BadLocationException;

import net.teamwraith.npctalk.gui.GUIListener;
import net.teamwraith.npctalk.gui.SpeechNode;

public class Formatter {
	private GUIListener guiListener;
	private List<String> speechContent; 

	
	public Formatter(GUIListener guiListener) {
		this.guiListener = guiListener;
	}
	
	public void runLines(String fileName) {
		File file = new File(fileName +".wd");
		speechContent = new ArrayList<String>();
		
		AddRespond();
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

	private void AddRespond() {
		SpeechNode currentNode = guiListener.getGUI().getMainFrame().getCurrentNode();
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
		if (guiListener.getGUI().getNodeFrame().getActorField().getText().isEmpty())
			line = "\tUNNAMED:";
		else
			line = "\t" + guiListener.getGUI().getNodeFrame().getActorField().getText().toUpperCase() + ":";
		speechContent.add(line);
	}

	private void addSpeech() {
		String line = null;
		for (int i = 0; i < guiListener.getGUI().getNodeFrame().getSpeechField().getLineCount(); i++) {
			try {
				int start = guiListener.getGUI().getNodeFrame().getSpeechField().getLineStartOffset(i);
				int end = guiListener.getGUI().getNodeFrame().getSpeechField().getLineEndOffset(i);
				line = "\t\t" + guiListener.getSpeech().substring(start, end);
				speechContent.add(line);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void addReturn() {
		SpeechNode currentNode = guiListener.getGUI().getMainFrame().getCurrentNode();
		String line = null;
		if (currentNode.isLeaf()){
			line = "RETURN [" + (currentNode.getLevel()+1) + "]";
		}
		else {
			line = "RETURN [" + (currentNode.getLevel()) + "]\r\n}"; //TODO make it set the right returnValue
		}
		speechContent.add(line);
	}
	
	private boolean isSpeechEmpty() {
		if (guiListener.getGUI().getNodeFrame().getSpeechField().getText().isEmpty())
			return true;
		else 
			return false;
	}
	
	private int currentRespondNr() {
		SpeechNode currentNode = guiListener.getGUI().getMainFrame().getCurrentNode();
		int i;
		i = currentNode.getParent().getIndex(currentNode) + 1;
		return i;
	}
	
}