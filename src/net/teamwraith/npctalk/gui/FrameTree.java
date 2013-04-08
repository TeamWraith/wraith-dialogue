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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolTip;
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
		
		// Possibly we could make the edited object in the tree update from this, as well as the node editor frame.
		tree.setEditable(true); 
		contentPane.add(treePanel);
		contentPane.add(newNodeBtn, BorderLayout.PAGE_END);
		treePanel.setViewportView(tree);
		setVisible(true);
		clearAll();
	}

	/**
	 * For modifying the root node's name.
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
	 * Method for clearing the tree.
	 */
	public void clearNodes() {
	    rootNode.removeAllChildren();
	  }

	public void clearAll() {
		clearNodes();
		tree.setVisible(false);
	}
	
	//TODO add something that tells the user "you need to put in a name for the dialogue" if the input is empty
	public void newTree() { 
		String input = JOptionPane.showInputDialog(null, "Enter new dialogue name: ", "Wraith Dialogue - Choose dialogue name", 1);
		if (input == null) {
			return;
		} else if (input.isEmpty()) {
			newTree(); 
			return;
		}
		setTitle("Wraith Dialogue - " + input);
		newNodeSuffix = 1;
		clearAll();
		// TODO This line should be able to be shortened.
		setRoot("0 - 0");
		tree.setModel(new DefaultTreeModel(rootNode));
		tree.setSelectionRow(0);
	}
	
	//TODO Make nodenames more suitable, maybe have [scenenr. - responsenr.] there instead?
	public void addNode() {
		SpeechNode child;
		String title = getCurrentNode().getCurrentChoiceNode()+1 + " - ";
		
		// This is a fucking mess and I am sorry.
		if (getCurrentNode().isRoot()) {
			title += getCurrentNode().getChildCount() + 1;
		} else {
			title += "yeah I don't know dammit.";
		}
		
		if (getCurrentNode().isLeaf()) {
			child = new SpeechNode(getCurrentChoice()+1, title);
		} else {
			child = new SpeechNode(getCurrentChoice(), title);
		}
		
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
		TreePath indexPath = tree.getSelectionPath();
		if (indexPath == null)
			return rootNode;
		return (SpeechNode) indexPath.getLastPathComponent();
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
	
