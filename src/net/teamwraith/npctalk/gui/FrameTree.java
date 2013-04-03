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

import org.eclipse.wb.swing.FocusTraversalOnArray;



/**
 * 
 * @author stektpotet 
 *	Created for decency, as the earlier tests were failiures. Also it's simpler to implement a pure, 
 *	fully working tree from another class, than creating it directly into the GUI-building class.
 */

public class FrameTree extends JFrame {

	private JPanel contentPane;
	private JTree tree;
	private DefaultMutableTreeNode rootNode;
	private List<DefaultMutableTreeNode> nodeList;
	private JButton TEST;
	
	
	final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	final int displayWidth = gd.getDisplayMode().getWidth();
	final int displayHeight = gd.getDisplayMode().getHeight();
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTest frame = new FrameTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
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
		
		TEST = new JButton("TEST");
		
		TEST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNodes();
			}
		});
		
		/**
		 * A possible way of changing to a new root, as it will (eventually) be called by the 'new Dialogue' method, which will also call 'clearAll()'.
		 */
		setRoot();
		
		/**
		 * Simple test with nodes. TODO make nodes dynamic.
		 */
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode(rootNode) {
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2;
					node_1 = new DefaultMutableTreeNode("colors");
						node_1.add(new DefaultMutableTreeNode("blue"));
						node_1.add(new DefaultMutableTreeNode("violet"));
						node_1.add(new DefaultMutableTreeNode("red"));
						node_1.add(new DefaultMutableTreeNode("yellow"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("sports");
						node_1.add(new DefaultMutableTreeNode("basketball"));
						node_1.add(new DefaultMutableTreeNode("soccer"));
						node_1.add(new DefaultMutableTreeNode("football"));
						node_1.add(new DefaultMutableTreeNode("hockey"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("food");
						node_1.add(new DefaultMutableTreeNode("hot dogs"));
						node_1.add(new DefaultMutableTreeNode("pizza"));
						node_1.add(new DefaultMutableTreeNode("raviolibutter"));
						node_1.add(new DefaultMutableTreeNode("bananas"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("DERP");
						node_2 = new DefaultMutableTreeNode("DERPY");
							node_2.add(new DefaultMutableTreeNode("YOMAN"));
							node_2.add(new DefaultMutableTreeNode("This"));
							node_2.add(new DefaultMutableTreeNode("Is"));
							node_2.add(new DefaultMutableTreeNode("Weird"));
							node_2.add(new DefaultMutableTreeNode("Like"));
							node_2.add(new DefaultMutableTreeNode("#YOLO"));
							node_2.add(new DefaultMutableTreeNode("And"));
							node_2.add(new DefaultMutableTreeNode("Stuff"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("HAI");
							node_2.add(new DefaultMutableTreeNode("DERP"));
						node_1.add(node_2);
					add(node_1);
				}
			}
		));
		tree.setEditable(true); // Possibly we could make the edited object in the tree update from this, as well as the node editor frame.
		contentPane.add(treePanel);
		contentPane.add(TEST, BorderLayout.PAGE_END);
		treePanel.setViewportView(tree);
		
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane})); // TODO understand this line of code.
		setVisible(true);
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
	
	public void setRoot(){
		setRoot(JOptionPane.showInputDialog(null, "New root name: ", "New Root", 1));
	}
	
	/**
	 *  Methods for clearing the tree, evantually also to start a new one.
	 */
	public void clearNodes() {
	    rootNode.removeAllChildren();
	  }

	public void clearAll(){
		clearNodes();
		tree.setVisible(false);
	}

	public void newTree(){
		clearAll();
		setRoot();
		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode(rootNode)));

	}
	
	
	
	public void addNodes(){
		for(int i = 0; i < nodeList.toArray().length; i++){
			nodeList.toArray()[i] = new DefaultMutableTreeNode(new SpeechNode());
			rootNode.add((DefaultMutableTreeNode) nodeList.toArray()[i]);
		}
	}
	

	
	class SpeechNode extends DefaultMutableTreeNode{
		
		public SpeechNode() { //Constructor
			
		}
		
	}
}
