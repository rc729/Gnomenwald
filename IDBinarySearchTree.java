package finalProject;

public class IDBinarySearchTree {
	
	private BSTNode root;
	
	/* default constructor */
	public IDBinarySearchTree() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void insertID(Gnome g) {
		root = insertID(root,g);
	}
	
	public BSTNode insertID(BSTNode n, Gnome g) {
		if (n == null) {
			n = new BSTNode(g);
		}
		else {
			if (g.getID() <= n.getData().getID()) {
				n.left = insertID(n.left, g);
			}
			else {
				n.right = insertID(n.right, g);
			}
		}
		return n;
	}
	
	public void delete(int id) {
		if (isEmpty()) {
		}
		else if (searchID(id) == null) {
		}
		else {
			root = deleteID(root, id);
		}
	}
	
	public BSTNode deleteID(BSTNode b, int id) {
		BSTNode q, q2, n;
		if (b.getData().getID() == id) {
			BSTNode left = b.getLeft();
			BSTNode right = b.getRight();
			if (left == null && right == null) {
				return null;
			}
			else if (left == null) {
				q = right;
				return q;
			}
			else {
				q2 = right;
				q = right;
				while (q.getLeft() != null) {
					q = q.getLeft();
				}
				q.setLeft(left);
				return q2;
			}
		}
		if (id < b.getData().getID()) {
			n = deleteID(b.getLeft(), id);
			b.setLeft(n);
		}
		else {
			n = deleteID(b.getRight(), id);
			b.setRight(n);
		}
		return b;
	}
	
	public Gnome searchID(int id) {
		return searchID(root, id);
	}
	
	public Gnome searchID(BSTNode n, int id) {
		while (n != null) {
			if (n.getData().getID() == id) {
				return n.getData();
			}
			else if (n.getData().getID() > id) {
				n = n.getLeft();
			}
			else if (n.getData().getID() < id) {
				n = n.getRight();
			}
		}
		return null;
	}	
}
