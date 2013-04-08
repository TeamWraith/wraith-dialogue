package net.teamwraith.npctalk.gui;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

@SuppressWarnings("serial")
public class SpeechNode extends DefaultMutableTreeNode {

	private boolean end = false;
	private String actor = null;
	private String response = null;
	private String speech = null;
	private String formattedSpeech = null;
	
	private int currentChoiceNode = 0;
	
	
	public SpeechNode(int currentChoice) {
		currentChoiceNode = currentChoice;
	}
	public SpeechNode(int currentChoice, Object userObject) {
		super(userObject);
		currentChoiceNode = currentChoice;
	}
	public SpeechNode(int currentChoice, Object userObject, boolean allowsChildren) {
		super(userObject, allowsChildren);
		currentChoiceNode = currentChoice;
	}

	//TODO Check if remove method messes up the currentChoiceNode count
	public void remove(int childIndex) { 
		if (!this.getChildAt(childIndex).isLeaf()) {
			currentChoiceNode--;
		}
		super.remove(childIndex);
	}
	
	public void remove(MutableTreeNode aChild) {
		if (!aChild.isLeaf()) {
			currentChoiceNode--;
		}
		super.remove(aChild);
	}
	
	public void assignInfo(boolean isEnd, String response, String actor, String speech, String formattedSpeech) {
		setEnd(isEnd);
		setResponse(response);
		setActor(actor);
		setSpeech(speech);
		setFormattedSpeech(formattedSpeech);
	}
	
	public int getCurrentChoiceNode() {
		return currentChoiceNode;
	}
	public void setCurrentChoiceNode(int currentChoiceNode) {
		this.currentChoiceNode = currentChoiceNode;
	}
	public boolean isEnd() {
		return end;
	}
	public void setEnd(boolean end) {
		this.end = end;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}

	public String getSpeech() {
		return speech;
	}
	public void setSpeech(String speech) {
		this.speech = speech;
	}
	
	public String getFormattedSpeech() {
		return formattedSpeech;
	}

	public void setFormattedSpeech(String formattedSpeech) {
		this.formattedSpeech = formattedSpeech;
	}

}
