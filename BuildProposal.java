package gui_demos;

import java.util.Collections;
import java.util.LinkedList;

import q.GnomenWald;
import q.Road;
import q.Village;

public class BuildProposal {
	
	public static class Subset {
    	int parent;
		int rank;
	
    }
    
    // A utility function to find set of an element i
    // (uses path compression technique)
    public static int find(LinkedList<Subset> subsets, int indexOfVillage)
    {
        // find root and make root as parent of i (path compression)
        if (subsets.get(indexOfVillage).parent != indexOfVillage)
            subsets.get(indexOfVillage).parent = find(subsets, subsets.get(indexOfVillage).parent);
 
        return subsets.get(indexOfVillage).parent;
    }
    
 // A function that does union of two sets of x and y
    // (uses union by rank)
    public static void union(LinkedList<Subset> subsets, int indexOfVillage1, int indexOfVillage2)
    {
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
        else
        {
            subsets.get(yroot).parent = xroot;
            subsets.get(xroot).rank++;
        }
    }
    
    public static void mst(GnomenWald g) {
    	System.out.println("----------------");
    	System.out.println("All villages");
    	for(Village v : g.getAllVillages()) {
    		System.out.println(v);
    	}
    	
    	//Build roads
    	LinkedList<Village> villages = g.getAllVillages();
    	for(int i = 0; i < villages.size(); i++) {
    		Village src = villages.get(i);
    		for (int j = i+1; j < villages.size(); j++) {
    			Village dest = villages.get(j);
    			g.createRoad(src, dest);
    		}
    	}
    	System.out.println("All Roads");
    	for (Road r : g.getAllRoads()){
    		System.out.println(r);
    	}
    	

        LinkedList<Road> result = new LinkedList<>();  // Tnis will store the resultant MST
        int e = 0;  // An index variable, used for result[]
        int i = 0;  // An index variable, used for sorted edges
        
            
        // Step 1:  Sort all the edges in non-decreasing order of their
        // weight.  If we are not allowed to change the given graph, we
        // can create a copy of array of edges
        Collections.sort(g.getAllRoads());
        System.out.println("-----After Sorting Roads----");
        for (Road r : g.getAllRoads()){
    		System.out.println(r);
    	}
            // Allocate memory for creating V ssubsets
            LinkedList<Subset> subsets = new LinkedList<>();
            for (int j = 0; j < g.getAllVillages().size(); j++) {
            	subsets.add(new Subset());
            }
     
            // Create V subsets with single elements
            for (int v = 0; v < g.getAllVillages().size(); ++v)
            {
            	Subset subset = subsets.get(v);
                subset.parent = v;
                subset.rank = 0;
            }
     
            i = 0;  // Index used to pick next edge
     
            // Number of edges to be taken is equal to V-1
            while (e < villages.size() - 1)
            {
                // Step 2: Pick the smallest edge. And increment the index
                // for next iteration
//                Edge next_edge = new Edge();
//                next_edge = edge[i++];
                Road nextRoad = g.getAllRoads().get(i++);
                System.out.println("========" + nextRoad + "=======");
                int x = find(subsets, villages.indexOf(nextRoad.getFrom()));
                int y = find(subsets, villages.indexOf(nextRoad.getTo()));
     
                // If including this edge does't cause cycle, include it
                // in result and increment the index of result for next edge
                if (x != y)
                {
                	System.out.println("Roads to be added:" + nextRoad);
                    result.add(nextRoad);
                    union(subsets, x, y);
                }
                e++;
                // Else discard the next_edge
            }
     
            // print the contents of result[] to display the built MST
            System.out.println("Following are the edges in the constructed MST");
            for (i = 0; i < e; ++i) {
                System.out.println(result.get(i).getFrom() +" -- "+result.get(i).getTo()+" == "+
                                   result.get(i).getDistance());
        }
    }
}
