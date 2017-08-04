package finalProject;

import java.util.ArrayList;

public class Census {
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
	
	/** Searches through the Gnome population by ID. Implements a traditional binary search tree
	 * where if a value is less than the wanted id is less than the current id it goes left, and 
	 * vice versa
	 * Precondition: id must be an int, and it must be less than or equal to the length of
	 * the population list so that it is a Gnome that has been created.
	 */
	public Gnome searchID(int id) {
		BinaryTree search = new BinaryTree(population.get(0));
		for (int i = 1; i < population.size(); i++) {
			search.addNode(population.get(i));
		}
		while (search != null) {
			if (search.data.getID() == id) { return search.data; }
			else if (search.data.getID() > id) { search = search.left; }
			else { search = search.right; }
		}
		return null;
	}
	
	/** Deletes a Gnome from the population list, given a valid ID
	 * Precondition: id must be an id that belongs to a created Gnome
	 */
	public void deleteGnome(int id) {
		if (id <= population.size()) { population.remove(id-1); }
	}
	
	public Census() {
		this.population = new ArrayList<Gnome>();
	}

}