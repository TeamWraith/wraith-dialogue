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
	
	public void buildNodeFrame(String name, boolean isEnd, String parent, String[] actors,int sceneRow,int sceneNr, String speech, GUIListener guiListener) {
		nodeFrame = new FrameNode(name, isEnd, parent, actors, sceneRow, sceneNr, speech);
		guiListener.enableNodeFrameListeners();
	}
	
	public void buildNodeFrame(String name, GUIListener guiListener) {
		nodeFrame = new FrameNode(name);
		guiListener.enableNodeFrameListeners();
	}

	public FrameNode getNodeFrame() { return nodeFrame; }
	
	public FrameTree getMainFrame() { return mainFrame; }

	public MenuBarTree getMainMenuBar() { return mainMenuBar; }
	
}
