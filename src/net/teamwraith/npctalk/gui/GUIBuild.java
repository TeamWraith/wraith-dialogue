package net.teamwraith.npctalk.gui;


/**
 * Builds the GUI.
 */
public class GUIBuild {
	
	//main window
	private FrameTree mainFrame;
	private FrameNode nodeFrame;
	
	private MenuBarTree mainMenuBar = new MenuBarTree();

	public GUIBuild(){
		
		mainFrame = new FrameTree();

		mainFrame.setJMenuBar(mainMenuBar);

	}
	
	public void buildNodeFrame(GUIListener guiListener) {
		nodeFrame = new FrameNode();
		guiListener.enableNodeFrameListeners();
	}

	public FrameNode getNodeFrame() { return nodeFrame; }
	
	public FrameTree getMainFrame() { return mainFrame; }

	public MenuBarTree getMainMenuBar() { return mainMenuBar; }
	
}
