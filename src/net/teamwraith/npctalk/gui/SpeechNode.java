package net.teamwraith.npctalk.gui;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class SpeechNode extends DefaultMutableTreeNode {

	private boolean end = false;
	private String actor;
	private String response;
	private String speech;
	
	private int currentChoiceNode;
	
	
	public SpeechNode(int currentChoice) {
		currentChoiceNode = currentChoice;
		System.out.println(currentChoiceNode);
	}
	public SpeechNode(int currentChoice ,Object userObject) {
		super(userObject);
		currentChoiceNode = currentChoice;
		System.out.println(currentChoiceNode);
	}
	public SpeechNode(int currentChoice, Object userObject, boolean allowsChildren) {
		super(userObject, allowsChildren);
		currentChoiceNode = currentChoice;
		System.out.println(currentChoiceNode);
	}

	/**
	 * TODO Check if remove method messes up the currentChoiceNode count
	 */
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
}
