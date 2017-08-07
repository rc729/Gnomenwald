package q;

import java.util.*;

/**
 * Census class to store the data for all gnomes in GnomenWald. Also
 * has search methods to search for individual gnomes or groups of gnomes.
 *
 */
public class Census {
	public static final Census instance = new Census();
	
	private class BinaryTree {
		private Gnome data;
		private BinaryTree left;
		private BinaryTree right;
		
		public BinaryTree(Gnome g) {
			this.data = g;
			this.left = null;
			this.right = null;
		}
		
		public void addNode(Gnome g) {
			if (g.getID() < this.data.getID()) { 
				if (this.left != null) { this.left.addNode(g); }
				else { this.left = new BinaryTree(g); }
			}
			else {
				if (this.right != null) { this.right.addNode(g); }
				else { this.right = new BinaryTree(g); }
			}
		}
	}
		
	ArrayList<Gnome> population;
	
	public List<Gnome> getPopulation() {
		return this.population;
	}
	
	public String searchID(int id) {
		IDBinarySearchTree tree = new IDBinarySearchTree();
		for (int i = 0; i < population.size(); i ++) {
			tree.insertID(population.get(i));
		}
		Gnome out = tree.searchID(id);
		return out.toString();
	}
	
	public String searchID(int id) {
		IDBinarySearchTree tree = new IDBinarySearchTree();
		for (int i = 0; i < population.size(); i ++) {
			tree.insertID(population.get(i));
		}
		Gnome out = tree.searchID(id);
		return out.toString();
	}
	
	public String searchNames(String name) {
		NameBST tree = new NameBST();
		for (int i = 0; i < population.size(); i ++) {
			tree.insertName(population.get(i));
		}
		List found = new ArrayList<Gnome>();
		while (tree.searchName(name) != null) {
			found.add(tree.searchName(name).getData());
			tree.deleteName(tree.root, name);
		}
		String out = "";
		for (int j = 0; j < found.size(); j++) {
			out += found.get(j).toString() + "\n";
		}
		return out;
	}
	
	public String searchColours(String colour) {
		ColourBST tree = new ColourBST();
		for (int i = 0; i < population.size(); i ++) {
			tree.insertColour(population.get(i));
		}
		List found = new ArrayList<Gnome>();
		while (tree.searchColour(colour) != null) {
			found.add(tree.searchColour(colour).getData());
			tree.deleteColour(tree.root, colour);
		}
		String out = "";
		for (int j = 0; j < found.size(); j++) {
			out += found.get(j).toString() + "\n";
		}
		return out;
	}
	
	/**
	 * Search through Gnome population by VIP. Returns a linked list of all gnomes with
	 * searched VIP
	 * @param VIP
	 * @return
	 */
	public LinkedList<Gnome> searchByVIP(int VIP) {
		LinkedList<Gnome> gnomes = new LinkedList<>();
		for(Iterator<Gnome> it = population.iterator(); it.hasNext();) {
			Gnome temp = it.next();
			if(temp.getVIP() == VIP) {
				gnomes.add(temp);
			}
		}
		return gnomes;
	}
	
	/** Deletes a Gnome from the population list, given a valid ID
	 * Precondition: id must be an id that belongs to a created Gnome
	 */
	public void deleteGnome(int id) {
		if (id <= population.size()) { population.remove(id-1); }
	}
	
	private Census() {
		this.population = new ArrayList<Gnome>();
	}

}
