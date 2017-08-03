package q;

import java.util.*;

/**
 * Generic Graph class that creates a directional graph.
 */
public class Graph {

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
			if(road.getFrom().equals(v) || road.getTo().equals(v)) 
				it.remove();
		}
	}
	
	//TODO (2) any roads that went through the village 
	//en route to other villages should be made direct (for example, if there is a road from A to 
	//B and roads from B to C and from B to D, then in case 
	//(2) there will now be a road from A to C and A to D). 
	public void deleteVillage2(Village v) {
		
	}
	public void deleteRoad(Village from, Village to) {
		for(Iterator<Road> it = roads.iterator(); it.hasNext();) {
			Road road = it.next();
			if(road.getFrom().equals(from) && road.getTo().equals(to)) 
				it.remove();
		}
	}

	// This method adds an edge from node a to b 
	public void addRoad(Village from, Village to, int distance, int capacity, int VIP) {
		Road road = new Road(from, to, distance, capacity, VIP);
		roads.add(road);
	}
	
	public void addRoad(Road road){
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
//	private void dfsFindAllPaths(Village source, Village target, LinkedList<Village> visited,
//		Path<Village> path, LinkedList<Path<Village>> paths) {
//		visited.add(source);
//		path.add(source);
//		if (source.equals(target)) {
//			// we are reach to target node. Print path
//			// System.out.println(path);
//			paths.add(new Path<Village>(path));
//
//		} else {
//			LinkedList<Road> allRoads = this.getRoads(source);
//			for (int i = 0; i < allRoads.size(); i++) {
//				Village p = allRoads.get(i).getTo();
//				if (!visited.contains(p)) {
//					dfsFindAllPaths(p, target, visited, path, paths);
//				}
//			}
//		}
//
//		// Remove "source" from path and mark it as unvisited
//		path.pop();
//		visited.remove(source);
//	}
//
//	// Find all paths from source node to target node in the given graph g
//	// Reference
//	// http://www.geeksforgeeks.org/find-paths-given-source-destination/
//	public LinkedList<Path<Village>> findAllPaths(Village source, Village target) {
//		LinkedList<Village> visited = new LinkedList<>();
//		Path<Village> path = new Path<>();
//		LinkedList<Path<Village>> paths = new LinkedList<>();
//
//		dfsFindAllPaths(source, target, visited, path, paths);
//
//		return paths;
//	}	
//
//	/**
//	 * Find all paths for each node not directly connect to "source" from
//	 * "source"
//	 * 
//	 * @param source
//	 * @return
//	 */
//	public LinkedList<LinkedList<Path<Village>>> findAllPathsToAllNodes(Village source) {
//		// Find all nodes which are not directly connected to "source"
//		LinkedList<Road> directlyConnectedNodes = getRoads(source);
//		LinkedList<Village> notDirectlyConnectedNodes = new LinkedList<>();
//		for (Village node : villages) {
//			if (!node.equals(source)
//					&& directlyConnectedNodes.indexOf(node) == -1) {
//				notDirectlyConnectedNodes.add(node);
//			}
//		}
//
//		// Find all paths from "source" to each node in
//		// notDirectlyConnectedNodes
//		LinkedList<LinkedList<Path<Village>>> nodePaths = new LinkedList<>();
//		for (int i = 0; i < notDirectlyConnectedNodes.size(); i++) {
//			Village node = notDirectlyConnectedNodes.get(i);
//			LinkedList<Path<Village>> paths = findAllPaths(source, node);
//			nodePaths.add(paths);
//		}
//
//		return nodePaths;
//	}
//
//	// Build all possible routes. A route contains a path to each node which not
//	// directly connected to "source"
//	public LinkedList<Route<Village>> buildAllPossibleRoutes(Village source) {
//		LinkedList<LinkedList<Path<Village>>> nodePaths = findAllPathsToAllNodes(
//				source);
//
//		// routeList holds all routes we found
//		LinkedList<Route<Village>> routeList = new LinkedList<>();
//		Route<Village> route = new Route<>();
//		buildAllPossibleRoutes(nodePaths, 0, route, routeList);
//
//		return routeList;
//	}
//	//This method recursively builds all possible routes from given list of paths
//	private void buildAllPossibleRoutes(
//			LinkedList<LinkedList<Path<Village>>> nodePaths, int nodeIndex,
//			Route<Village> route, LinkedList<Route<Village>> routeList) {
//
//		LinkedList<Path<Village>> pathList = nodePaths.get(nodeIndex);
//		for (int i = 0; i < pathList.size(); i++) {
//			route.add(pathList.get(i));
//			if (nodeIndex == nodePaths.size() - 1) {
//				// This is the last node. we finished a route build
//				Route<Village> newRoute = new Route<>(route);
//				routeList.add(newRoute);
//				route.pop();
//			}
//			if (nodeIndex + 1 < nodePaths.size()) {
//				buildAllPossibleRoutes(nodePaths, nodeIndex + 1, route,
//						routeList);
//			}
//		}
//
//		if (nodeIndex > 0) {
//			route.pop();
//		}
//	}
//
//	/**
//	 * Best route is the route which reaches the most of nodes in the graph in
//	 * given list of routes. In other words, the best route is the route which
//	 * contains the most of paths
//	 * 
//	 * @param routes
//	 * @return
//	 */
//	public static <T> LinkedList<Route<T>> findBestRoutes(
//			LinkedList<Route<T>> routes) {
//		int maxPaths = 0;
//		LinkedList<Route<T>> bestRoutes = new LinkedList<>();
//		for (int i = 0; i < routes.size(); i++) {
//			Route<T> route = routes.get(i);
//			int numPaths = route.size();
//			if (maxPaths < numPaths) {
//				maxPaths = numPaths;
//				bestRoutes.clear();
//				bestRoutes.add(route);
//			} else if (maxPaths == numPaths) {
//				bestRoutes.add(route);
//			}
//		}
//
//		return bestRoutes;
//	}

}
