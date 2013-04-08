package net.teamwraith.npctalk.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

@SuppressWarnings("serial")
public class FrameTree extends JFrame {

	private JPanel contentPane;
	
	private SpeechNode rootNode = new SpeechNode(0);
	private TreeModel treeModel;
	
	private JTree tree = new JTree(treeModel);
	private JButton newNodeBtn;
	
	private int newNodeSuffix = 1;

	
	
	final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	final int displayWidth = gd.getDisplayMode().getWidth();
	final int displayHeight = gd.getDisplayMode().getHeight();
	
	public FrameTree() {
		
		setTitle("Wraith Dialogue");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(displayWidth/2 - 256, displayHeight/2 - 320, 512, 640);
		setMinimumSize(new Dimension(256, 320));
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		JScrollPane treePanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		tree = new JTree();
		
		newNodeBtn = new JButton("New Node");
		newNodeBtn.setEnabled(false);

		tree.setModel(treeModel);
			
		tree.setEditable(true); // Possibly we could make the edited object in the tree update from this, as well as the node editor frame.
		contentPane.add(treePanel);
		contentPane.add(newNodeBtn, BorderLayout.PAGE_END);
		treePanel.setViewportView(tree);
		setVisible(true);
		clearAll();
	}

	/**
	 * For modifying the root nodes name.
	 */
	public void setRoot(SpeechNode rootNode) {
		this.rootNode = rootNode;
		tree.setVisible(true);
	}
	
	public void setRoot(String rootNode) {
		this.rootNode = new SpeechNode(0, rootNode);
		tree.setVisible(true);
	}
	
	/**
	 *  Methods for clearing the tree.
	 */
	public void clearNodes() {
	    rootNode.removeAllChildren();
	  }

	public void clearAll() {
		clearNodes();
		tree.setVisible(false);
	}

	public void newTree() { //TODO add pop up for what the dialogue shall be named.
		newNodeSuffix = 1;
		clearAll();
		setRoot("New Node" + newNodeSuffix++);
		tree.setModel(new DefaultTreeModel(rootNode));
		tree.setSelectionRow(0);
	}
	
	//TODO Make nodenames more suitable, maybe have [scenenr. - responsenr.] there instead?
	public void addNode() {
		SpeechNode child;
		if (getCurrentNode().isLeaf())
			child = new SpeechNode(getCurrentChoice()+1, "New Node" + newNodeSuffix++);
		else
			child = new SpeechNode(getCurrentChoice(), "New Node" + newNodeSuffix++);
		
		getCurrentNode().add(child);
		tree.updateUI();
		tree.expandRow(tree.getMinSelectionRow());
	}

	public SpeechNode getRootNode() {
		return rootNode;
	}

	public JTree getTree() {
		return tree;
	}
	
	public JButton getNewNodeBtn() {
		return newNodeBtn;
	}

	public SpeechNode getCurrentNode() {
		SpeechNode index = null;
		TreePath indexPath = tree.getSelectionPath();
		if (indexPath == null)
			index = rootNode;
		else
			index = (SpeechNode) indexPath.getLastPathComponent();
		return index;
	}
	
	public int getNodeCount() {
		return getNodeCount(tree.getModel(), tree.getModel().getRoot(), 1);
	}
	
	public int getNodeCount(TreeModel model, Object object, int count) {
		int nodeCount = count;
		for (int i = 0; i < model.getChildCount(object); i++) {
			nodeCount += getNodeCount(model, model.getChild(object, i),count);
		}
		return nodeCount;
	}
	
	public int getBranchCount() {
		return getNodeCount(tree.getModel(), tree.getModel().getRoot(), 1) - getRootNode().getLeafCount();
	}
	
	
	public SpeechNode[] getNodes() {
		List<SpeechNode> nodes = new ArrayList<SpeechNode>();
		for (
			Enumeration<?> e = ((SpeechNode) tree.getModel().getRoot()).preorderEnumeration(); 
			e.hasMoreElements();
		) {
			nodes.add((SpeechNode) e.nextElement());
		}
		return nodes.toArray(new SpeechNode[nodes.size()]);
	}
	
	private int getCurrentChoice() {
		if (getCurrentNode() == null) {
			return 0;
		}
		return getBranchCount();
	}
	

}
	
