package net.teamwraith.npctalk.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;



/**
 * 
 * @author stektpotet 
 *	Created for decency, as the earlier tests were failiures. Also it's simpler to implement a pure, 
 *	fully working tree from another class, than creating it directly into the GUI-building class.
 */

public class FrameTree extends JFrame {

	private JPanel contentPane;
	
	private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("ROOT");
	private TreeModel treeModel = new DefaultTreeModel(rootNode);
	
	private JTree tree = new JTree(treeModel);
	private List<DefaultMutableTreeNode> nodeList;
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
		
		nodeList = new ArrayList<DefaultMutableTreeNode>();
		
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
	public void setRoot(DefaultMutableTreeNode rootNode) {
		this.rootNode = rootNode;
		tree.setVisible(true);
	}
	
	public void setRoot(String rootNode) {
		this.rootNode = new DefaultMutableTreeNode(rootNode);
		tree.setVisible(true);
	}
	

	
	/**
	 *  Methods for clearing the tree, evantually also to start a new one.
	 */
	public void clearNodes() {
	    rootNode.removeAllChildren();
	  }

	public void clearAll() {
		clearNodes();
		tree.setVisible(false);
	}

	public void newTree() {
		clearAll();
		setRoot("New Node" + newNodeSuffix++);
		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode(rootNode)));
		tree.setSelectionInterval(0, 0);
	}
	
	public void addNode() {
		DefaultMutableTreeNode child = new DefaultMutableTreeNode("New Node" + newNodeSuffix++);
		currentNode().add(child);
		tree.updateUI();
		tree.setSelectionPath(tree.getSelectionPath().pathByAddingChild(child));
	}

	public JTree getTree() {
		return tree;
	}
	
	public JButton getNewNodeBtn() {
		return newNodeBtn;
	}

	public DefaultMutableTreeNode currentNode() {
		DefaultMutableTreeNode index = null;
		TreePath indexPath = tree.getSelectionPath();
		if (indexPath == null)
			index = rootNode;
		else
			index = (DefaultMutableTreeNode) indexPath.getLastPathComponent();
		return index;
	}
}
