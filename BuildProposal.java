package gui_demos;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import q.GnomenWald;
import q.Road;
import q.Village;


public class BuildProposal {
	public static class Subset {
		int parent;
		int rank;

		public String toString() {
			return "Parent:" + parent + " Rank:" + rank;
		}
	}

	// A utility function to find set of an element i
	// (uses path compression technique)
	public static int find(LinkedList<Subset> subsets, int indexOfVillage) {
		// find root and make root as parent of i (path compression)
		if (subsets.get(indexOfVillage).parent != indexOfVillage)
			subsets.get(indexOfVillage).parent = find(subsets, subsets.get(indexOfVillage).parent);

		return subsets.get(indexOfVillage).parent;
	}

	// A function that does union of two sets of x and y
	// (uses union by rank)
	public static void union(LinkedList<Subset> subsets, int indexOfVillage1, int indexOfVillage2) {
		int xroot = find(subsets, indexOfVillage1);
		int yroot = find(subsets, indexOfVillage2);

		// Attach smaller rank tree under root of high rank tree
		// (Union by Rank)
		if (subsets.get(xroot).rank < subsets.get(yroot).rank)
			subsets.get(xroot).parent = yroot;
		else if (subsets.get(xroot).rank > subsets.get(yroot).rank)
			subsets.get(yroot).parent = xroot;

		// If ranks are same, then make one as root and increment
		// its rank by one
		else {
			subsets.get(yroot).parent = xroot;
			subsets.get(xroot).rank++;
		}
	}

	public static void mst(GnomenWald wald) {
		GnomenWald g = createGnomenWald((List) wald.getAllVillages());
		System.out.println("-----MST road build plan-------");

		System.out.println("All villages");
		List<Village> villages = g.getAllVillages();
		for (Village v : villages) {
			System.out.println(v);
		}

		System.out.println("All Roads");
		for (Road r : g.getAllRoads()) {
			System.out.println(r);
		}

		// Step 1: Sort all the edges in non-decreasing order of their
		// weight. If we are not allowed to change the given graph, we
		// can create a copy of array of edges
		Collections.sort(g.getAllRoads());
		System.out.println("-----After Sorting Roads----");
		for (Road r : g.getAllRoads()) {
			System.out.println(r);
		}

		LinkedList<Road> mstRoads = new LinkedList<>(); 
		
		int e = 0; // An index variable, used for result[]
		int i = 0; // An index variable, used for sorted edges

		// Allocate memory for creating V ssubsets
		LinkedList<Subset> subsets = new LinkedList<>();
		for (int j = 0; j < g.getAllVillages().size(); j++) {
			subsets.add(new Subset());
		}

		// Create V subsets with single elements
		for (int v = 0; v < g.getAllVillages().size(); ++v) {
			Subset subset = subsets.get(v);
			subset.parent = v;
			subset.rank = 0;
		}

		i = 0; // Index used to pick next edge

		// Number of edges to be taken is equal to V-1
		while (e < villages.size() - 1) {
			// Step 2: Pick the smallest edge. And increment the index
			Road nextRoad = g.getAllRoads().get(i++);
			int x = find(subsets, villages.indexOf(nextRoad.getFrom()));
			int y = find(subsets, villages.indexOf(nextRoad.getTo()));

			// If including this edge does't cause cycle, include it
			// in result and increment the index of result for next edge
			if (x != y) {
				mstRoads.add(nextRoad);
				union(subsets, x, y);
				e++;
			}
		}

		// print the contents of result[] to display the built MST
		System.out.println("======= MST Min. Cost Roads =======");
		double totalCost = 0.0;
		for (i = 0; i < e; ++i) {
			Road road = mstRoads.get(i);
			System.out.println(road);
			totalCost = totalCost + road.getDistance();
		}
		System.out.println("Total cost:" + totalCost);
		
		//Update GUI to show MST tree
		updateRoads(mstRoads, wald);
	}

	/**
	 * Create new GnomenWald, a graph, using given villages
	 */
	public static GnomenWald createGnomenWald(List<GuiVillage> villages) {
		GnomenWald g = new GnomenWald();
		for (GuiVillage v : villages) {
			GuiVillage nv = new GuiVillage(v.getPoint(), v.getColor());
			g.addVillage(nv);
		}
		createAllRoads(g);
		return g;
	}

	/**
	 * Build roads to connect all villages
	 * 
	 */
	public static void createAllRoads(GnomenWald g) {
		LinkedList<Village> villages = g.getAllVillages();
		for (int i = 0; i < villages.size(); i++) {
			Village src = villages.get(i);
			for (int j = i + 1; j < villages.size(); j++) {
				Village dest = villages.get(j);
				g.createRoad(src, dest);
			}
		}
	}

	/**
	 * Update GnomenWald graph to use given roads. The existing roads will be
	 * deleted.
	 * 
	 * @param g
	 */
	public static void updateRoads(LinkedList<Road> roads, GnomenWald g) {
		// Delete existing road from GnomenWald graph
		for (Iterator<Road> it = g.getAllRoads().iterator(); it.hasNext(); ) {
			Road road = it.next();
			g.deleteRoad(road.getFrom(), road.getTo());
		}
		
		for (Road road : roads) {
			g.createRoad(road.getFrom(), road.getTo());
			;
		}

	}
}
