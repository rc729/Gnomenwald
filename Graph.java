package q;

import java.util.*;

/**
 * Generic Graph class that creates a directional graph.
 */
public class Graph {
	
	public final static Graph Gnomenwald = new Graph();
	
	private static Random rand = new Random();
	LinkedList<Road> roads;
	LinkedList<Village> villages;
	
	public Graph() {
		this.villages = new LinkedList<>();
		this.roads = new LinkedList<>();
	}
	
	// Constructor
	public Graph(LinkedList<Village> villages) {
		
		this.villages = villages;
		this.roads = new LinkedList<>();
	}
	
	public LinkedList<Road> getAllRoads() {
		return roads;
	}
	
	public LinkedList<Village> getAllVillages() {
		return villages;
	}
	
	public void addVillage(Village v) {
		villages.add(v);
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + "(" + roads + ")\n(" + villages + ")";
		
	}
	
	//(1) any roads that went through the village should also be deleted, 
	public void deleteVillage1(Village v) {
		villages.remove(v);
		for(Iterator<Road> it = roads.iterator(); it.hasNext();) {
			Road road = it.next();
			if(road.getFrom().equals(v) || road.getTo().equals(v)) {
				it.remove();
				road.destroy();
			}
		}
	}
	
	//TODO (2) any roads that went through the village 
	//en route to other villages should be made direct (for example, if there is a road from A to 
	//B and roads from B to C and from B to D, then in case 
	//(2) there will now be a road from A to C and A to D). 
	public void deleteVillage2(Village v) {
		LinkedList<Road> inboundRoads = v.getInboundRoads();
		LinkedList<Road> outboundRoads = v.getOutboundRoads();
		LinkedList<Village> inVillages = new LinkedList<>();
		LinkedList<Village> outVillages = new LinkedList<>();

		for(Iterator<Road> it = inboundRoads.iterator(); it.hasNext();) {
			Road inRoad = it.next();
			inVillages.add(inRoad.getFrom());	
		}
		for(Iterator<Road> it = outboundRoads.iterator(); it.hasNext();) {
			Road outRoad = it.next();
			outVillages.add(outRoad.getTo());
		}
		for(int i = 0; i < inVillages.size(); i++) {
			Village  src = inVillages.get(i);
			for(int j = 0; j < outVillages.size(); j++) {
				Village dst = outVillages.get(j);
				
				if (src.getOutboundRoad(dst) == null && !(src.equals(dst))) {
					createRoad(src, dst);
				}
			}	
		}
		for(Road road : inboundRoads) {
			road.getFrom().removeOutboundRoad(road);
		}

		for(Road road : outboundRoads) {
			road.getTo().removeInboundRoad(road);
		}
		villages.remove(v);
		roads.removeAll(inboundRoads);
		roads.removeAll(outboundRoads);
	}
		
	public void deleteRoad(Village from, Village to) {
		for(Iterator<Road> it = roads.iterator(); it.hasNext();) {
			Road road = it.next();
			if(road.getFrom().equals(from) && road.getTo().equals(to)) {
				it.remove();
				road.destroy();
			}
		}
	}

	// This method adds an edge from node a to b 
	public void createRoad(Village from, Village to, int capacity, int VIP) {
		Road road = new Road(from, to, capacity, VIP);
		roads.add(road);
	}
	
	//This method uses default capacity and VIP
	public void createRoad(Village from, Village to) {
		Road road = new Road(from, to);
		roads.add(road);
	}

	// This method gets all nodes in graph
	public LinkedList<Village> allNodesInGraph() {
		return villages;
	}
	
	//Method finds all edges FROM this node
	public LinkedList<Road> getRoads(Village from) {
		LinkedList<Road> allRoads = new LinkedList<>();
		for(Iterator<Road> it = roads.iterator(); it.hasNext();) {
			Road road = it.next();
			if(road.getFrom().equals(from)) 
				allRoads.add(road);
		}
		return allRoads;
	}
	
	// Find all paths from source node to target node in the given graph g
	// Reference
	// http://www.geeksforgeeks.org/find-paths-given-source-destination/
	private void dfsFindAllPaths(Village source, Village target, LinkedList<Village> visited,
			Path path, LinkedList<Path> paths) {
		visited.add(source);
		path.add(source);
		if (source.equals(target)) {
			// we are reach to target node. Print path
			// System.out.println(path);
			paths.add(new Path(path));

		} else {
			LinkedList<Road> roads = source.getOutboundRoads();
			for (int i = 0; i < roads.size(); i++) {
				Road temp = roads.get(i);
				if (!visited.contains(temp.getTo())) {
					dfsFindAllPaths(temp.getTo(), target, visited, path, paths);
				}
			}
		}

		// Remove "source" from path and mark it as unvisited
		path.removeLast();
		visited.remove(source);
	}

	// Find all paths from source node to target node in the given graph g
	// Reference
	// http://www.geeksforgeeks.org/find-paths-given-source-destination/
	public LinkedList<Path> findAllPaths(Village source, Village target) {
		LinkedList<Village> visited = new LinkedList<>();
		Path path = new Path();
		LinkedList<Path> paths = new LinkedList<>();

		dfsFindAllPaths(source, target, visited, path, paths);

		return paths;
	}
	
	//Find shortest path from source to target
	public Path shortestPath(Village source, Village target) {
		LinkedList<Path> paths = findAllPaths(source, target);
		Path shortestPath = new Path();
		double shortestDistance = Double.MAX_VALUE;
		for(int i = 0; i < paths.size(); i++) {
			Path temp = paths.get(i);
			double tempDistance = 0;
			for (int j = 0; j < temp.size() - 1; j++) {
				Road road = temp.get(j).getOutboundRoad(temp.get(j+1));
				if (road != null)
					tempDistance += road.getDistance();
			}
			if (tempDistance < shortestDistance) {
				shortestDistance = tempDistance;
				shortestPath = temp;
			}
		}
		return shortestPath;
	}
	
	//pick random path from all paths 
	public Path randomPath(Village source, Village target) {
		LinkedList<Path> paths = findAllPaths(source, target);
		if (paths.size() == 0 )
			return null;
		if (paths.size() == 1)
			return paths.get(0);
		else {
			int rand = randInt(0, paths.size()-1);
			return paths.get(rand);
		}
	}

	/**
	 * Get random int between two ints
	 */
	public static int randInt(int min, int max) {

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
