package q;

import java.util.*;

/**
 * Census class to store the data for all gnomes in GnomenWald. Also
 * has search methods to search for individual gnomes or groups of gnomes.
 *
 */
public class Census {
	ArrayList<Gnome> population;
	public static final Census instance = new Census();
	
	/** Deletes a Gnome from the population list, given a valid ID
	 * Precondition: id must be an id that belongs to a created Gnome
	 */
	public void deleteGnome(int id) {
		if (id <= population.size()) { population.remove(id-1); }
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
	
	public String searchVIPs(int vip) {
		VIPBST tree = new VIPBST();
		for (int i = 0; i < population.size(); i ++) {
			tree.insertVIP(population.get(i));
		}
		List found = new ArrayList<Gnome>();
		while (tree.searchVIP(vip) != null) {
			found.add(tree.searchVIP(vip).getData().toString());
			tree.deleteVIP(tree.root, vip);
		}
		String out = "";
		for (int j = 0; j < found.size(); j++) {
			out += found.get(j).toString() + "\n";
		}
		return out;
	}
	
	public Census() {
		this.population = new ArrayList<Gnome>();
	}

}
