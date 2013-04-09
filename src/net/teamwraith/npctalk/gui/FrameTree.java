package net.teamwraith.npctalk.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

@SuppressWarnings("serial")
public class FrameTree extends JFrame {

	private JPanel contentPane = new JPanel();
	private JPanel buttonPane = new JPanel();
	
	private SpeechNode rootNode = new SpeechNode(0);
	private TreeModel treeModel = new DefaultTreeModel(rootNode);
	
	private JTree tree = new JTree();
	private JButton newNodeBtn;
	private JButton removeNodeBtn;
	
	private JButton btn02;
	private JButton btn01;
	
	
	final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	final int displayWidth = gd.getDisplayMode().getWidth();
	final int displayHeight = gd.getDisplayMode().getHeight();
	
	public FrameTree() {
		setTitle("Wraith Dialogue");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(displayWidth/2 - 256, displayHeight/2 - 320, 512, 640);
		setMinimumSize(new Dimension(340, 320));
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
		
		JScrollPane treePanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		tree = new JTree();
		
		newNodeBtn = new JButton("New Node");
		newNodeBtn.setEnabled(false);
		removeNodeBtn = new JButton("Delete Node");
		removeNodeBtn.setEnabled(false);
		
		btn01 = new JButton("For later use");
		btn02 = new JButton("For later use");
		
		tree.setModel(treeModel);
		
		// Possibly we could make the edited object in the tree update from this, as well as the node editor frame.
		tree.setEditable(false); 
		contentPane.add(treePanel);
		contentPane.add(buttonPane, BorderLayout.EAST );
		
		
		gl_buttonPane.setHorizontalGroup(
			gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addComponent(newNodeBtn, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
				.addComponent(removeNodeBtn, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
				.addComponent(btn02, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
				.addComponent(btn01, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE));
		
		gl_buttonPane.setVerticalGroup(
			gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addComponent(newNodeBtn)
					.addComponent(removeNodeBtn)
					.addComponent(btn02)
					.addComponent(btn01)));
		
		buttonPane.setLayout(gl_buttonPane);
		
		treePanel.setViewportView(tree);
		setVisible(true);
		clearAll();
	}

	/**
	 * For modifying the root node's name.
	 */
	public void setRoot(SpeechNode rootNode) {
		this.rootNode.setUserObject(rootNode);
		tree.setVisible(true);
	}
	
	public void setRoot(String rootNode) {
		this.rootNode.setUserObject(rootNode);
		tree.setVisible(true);
		tree.updateUI();
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
		
		clearAll();
		// TODO This line should be able to be shortened.
		setRoot("Init");
		tree.setSelectionRow(0);
	}
	
	//TODO Make nodenames more suitable, maybe have [scenenr. - responsenr.] there instead?
	public void addNode() {
		SpeechNode child;
		if (getCurrentNode().isLeaf()) 
		{child = new SpeechNode(getCurrentChoice()+1, "REFRESH FRAME");}
		else 
		{child = new SpeechNode(getCurrentChoice(), "REFRESH FRAME");}
		
		getCurrentNode().add(child);
		child.setUserObject("[" + child.getCurrentChoiceNode() + " - " + (child.getParent().getIndex(child)+1) + "]"); //TODO work on getCurrentChoiceNode for this.
		tree.updateUI();
		tree.expandRow(tree.getMinSelectionRow());
	}
	
	public void removeNode() {
		((SpeechNode) getCurrentNode().getParent()).remove(getCurrentNode());
		tree.updateUI();
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
	
	public JButton getRemoveNodeBtn() {
		return removeNodeBtn;
	}

	public SpeechNode getCurrentNode() {
		TreePath indexPath = tree.getSelectionPath();
		if (indexPath == null)
			return rootNode;
		return (SpeechNode) indexPath.getLastPathComponent();
	}
	
	public SpeechNode getParentNode() {
		return (SpeechNode) getCurrentNode().getParent();
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
	
