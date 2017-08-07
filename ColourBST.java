package finalProject;

public class ColourBST {
public BSTNode root;
	
	public ColourBST() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void insertColour(Gnome g) {
		root = insertColour(root, g);
	}
	
	public BSTNode insertColour(BSTNode n, Gnome g) {
		if (n==null) {
			n = new BSTNode(g);
		}
		else {
			if (g.getColour().compareTo(n.getData().getColour()) < 0) {
				n.left = insertColour(n.left,g);
				n.left.parent = n;
			}
			else {
				n.right = insertColour(n.right,g);
				n.right.parent = n;
			}
		}
		return n;
	}
	
	public void deleteColour(BSTNode b, String colour) {
		BSTNode parent = b.getParent();
		if (b.getData().getColour() == colour) {
			if (b.getLeft() == null && b.getRight()==null) {
				if (b == this.root) {
					this.root = null;
				}
				else if (parent.getLeft() == b) {
					parent.left = null;
				}
				else {
					parent.right = null;
				}
			}
			else if (b.left == null) {
				if (b == root) {
					root = root.right;
				}
				else if (parent.getLeft() == b) {
					parent.left = b.right;
				}
				else {
					parent.right = b.right;
				}
			}	
			else if (b.right == null) {
				if (b == root) {
					root = root.left;
				}
				else if (parent.getLeft() == b) {
					parent.left = b.left;
				}
				else {
					parent.right = b.left;
				}
			}
			else {
				BSTNode successor = b.right;
				BSTNode succPar = b;
				if (successor.left == null) {
					b.setData(successor.getData());
					b.right = successor.right;
				}
				while (successor.left != null) {
					succPar = successor;
					successor = successor.left;
				}
				b.setData(successor.getData());
				succPar.left = successor.right;
				
			}
		}
		else if (colour.compareTo(b.getData().getColour()) < 0) {
			deleteColour(b.getLeft(), colour);
		}
		else {
			deleteColour(b.getRight(), colour);
		}
	}
	public BSTNode searchColour(String colour) {
		return searchColour(root, colour);
	}
	
	public BSTNode searchColour(BSTNode n, String colour) {
		while (n != null) {
			if (n.getData().getColour() == colour) {
				return n;
			}
			else if (colour.compareTo(n.getData().getColour()) < 0) {
				n = n.getLeft();
			}
			else {
				n = n.getRight();
			}
		}
		return null;
	}
}
