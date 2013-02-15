import java.util.Collection;


public class TreeNode {

	private Collection<TreeNode> children;
	private String content;

	public TreeNode(Collection<TreeNode> children, String content) {
		this.children = children;
		this.content = content;
	}

	public Collection<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(Collection<TreeNode> children) {
		this.children = children;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}