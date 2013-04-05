package net.teamwraith.npctalk.gui;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class SpeechNode extends DefaultMutableTreeNode {

	private int currentChoiceNode;
	
	public SpeechNode(int currentChoice) {
		currentChoiceNode = currentChoice;
	}
	public SpeechNode(int currentChoice ,Object userObject) {
		super(userObject);
		currentChoiceNode = currentChoice;
	}
	public SpeechNode(int currentChoice, Object userObject, boolean allowsChildren) {
		super(userObject, allowsChildren);
		currentChoiceNode = currentChoice;
	}
	
	public void remove(int childIndex) {
		super.remove(childIndex);
	}
	
	public void remove(MutableTreeNode aChild) {
		super.remove(aChild);
	}
	
	public int getCurrentChoiceNode() {
		return currentChoiceNode;
	}
}
