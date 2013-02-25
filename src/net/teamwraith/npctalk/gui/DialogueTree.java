package net.teamwraith.npctalk.gui;

import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;


public class DialogueTree {

	
	private DefaultMutableTreeNode rootNode;
	private DefaultTreeModel treeModel;
	private JTree tree;

	public DialogueTree(){
		rootNode = new DefaultMutableTreeNode("Root Node");
		treeModel = new DefaultTreeModel(rootNode);
		treeModel.addTreeModelListener(new MyTreeModelListener());

		tree = new JTree(treeModel);
		tree.setEditable(true);
		tree.getSelectionModel().setSelectionMode
			(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);
	}
	

	class MyTreeModelListener implements TreeModelListener {
	    public void treeNodesChanged(TreeModelEvent e) {
	        DefaultMutableTreeNode node;
	        node = (DefaultMutableTreeNode)
	                 (e.getTreePath().getLastPathComponent());

	        /**
	         * If the event lists children, then the changed
	         * node is the child of the node we have already
	         * gotten.  Otherwise, the changed node and the
	         * specified node are the same.
	         **/
	        try {
	            int index = e.getChildIndices()[0];
	            node = (DefaultMutableTreeNode)
	                   (node.getChildAt(index));
	        } catch (NullPointerException exc) {}

	        System.out.println("The user has finished editing the node.");
	        System.out.println("New value: " + node.getUserObject());
	    }

	    public void treeNodesInserted(TreeModelEvent e) {
	    }
	    public void treeNodesRemoved(TreeModelEvent e) {
	    }
	    public void treeStructureChanged(TreeModelEvent e) {
	    }   
	}
	
}


