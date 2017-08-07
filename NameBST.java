package finalProject;

public class NameBST {
	public BSTNode root;
	
	public NameBST() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void insertName(Gnome g) {
		root = insertName(root, g);
	}
	
	public BSTNode insertName(BSTNode n, Gnome g) {
		if (n==null) {
			n = new BSTNode(g);
		}
		else {
			if (g.getName().compareTo(n.getData().getName()) < 0) {
				n.left = insertName(n.left,g);
				n.left.parent = n;
			}
			else {
				n.right = insertName(n.right,g);
				n.right.parent = n;
			}
		}
		return n;
	}
	
	public void deleteName(BSTNode b, String name) {
		BSTNode parent = b.getParent();
		if (b.getData().getName() == name) {
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
		else if (name.compareTo(b.getData().getName()) < 0) {
			deleteName(b.getLeft(), name);
		}
		else {
			deleteName(b.getRight(), name);
		}
	}
	public BSTNode searchName(String name) {
		return searchName(root, name);
	}
	
	public BSTNode searchName(BSTNode n, String name) {
		while (n != null) {
			if (n.getData().getName() == name) {
				return n;
			}
			else if (name.compareTo(n.getData().getName()) < 0) {
				n = n.getLeft();
			}
			else {
				n = n.getRight();
			}
		}
		return null;
	}
}
