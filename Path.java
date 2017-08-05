package q;

import java.util.*;

/**
 * Path class holds a path from "source" to "target" 
 * source: the first element 
 * target: the last element
 *
 */
public class Path extends LinkedList<Village>{

	// Create a new Path same as give path
	public Path(Path otherPath) {
		super(otherPath);
	}

	public Path() {
		super();
	}

	// To string method
	public String toString() {
		String s = "";
		for (int i = 0; i < size(); i++) {
			if (s.isEmpty()) {
				s = this.get(i).toString();
			} else {
				s = s + " " + this.get(i);
			}
		}

		return "(" + s + ")";
	}

}
