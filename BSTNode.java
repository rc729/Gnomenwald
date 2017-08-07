package finalProject;

public class BSTNode {
	public BSTNode left, right, parent;
	private Gnome data;
	
	public BSTNode(Gnome g) {
		left = null;
		right = null;
		parent = null;
		data = g;
	}
	
	public void setLeft(BSTNode t) {
		left = t;
	}
	
	public void setRight(BSTNode t) {
		right = t;
	}
	public void setParent(BSTNode t) {
		parent = t;
	}
	public void setData(Gnome g) {
		data = g;
	}
	public BSTNode getLeft() {
		return left;
	}
	
	public BSTNode getRight() {
		return right;
	}
	
	public BSTNode getParent() {
		return parent;
	}
	
	public Gnome getData() {
		return data;
	}
	
	public Gnome min() {
		if (left == null) {
			return data;
		}
		else {
			return left.min();
		}
	}
}
