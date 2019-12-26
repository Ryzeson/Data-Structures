/**
 * Ryzeson Maravich
 * Vertex.java
 * Vertex of a graph
 */

import java.util.TreeMap;

public class Vertex {

	int id;
	static int index;
	String label;
	Vertex pred;
	double cost;
	boolean visited;
	TreeMap<String, Edge> adj;

	/**
	 * Instantiates a new Vertex with the given label and initializes all its instance variables
	 * @param label label of vertex
	 */
	public Vertex(String label) {
		this.label = label;
		id = index++; 
		adj = new TreeMap<>(new StringComp());
	}

	/**
	 * Resets this Vertex for graph algorithms (visited, cost, pred)
	 */
	public void reset() {
		visited = false;
		pred = null;
		cost = 0;
	}

	/**
	 * Marks this Vertex as processed (visited; no need to check)
	 */
	public void mark() {
		visited = true;
	}

	/**
	 * Marks this Vertex as _not_ processed; need to check
	 */
	public void unmark() {
		visited = false;
	}

	/**
	 * Adds c to the current cost of this Vertex ('s path)
	 * @param c cost
	 */
	public void addCost(double c) {
		cost += c;
	}

	/**
	 * Returns whether this Vertex is processed (marked, or visited) or not
	 * @return whether this Vertex is processed (marked, or visited) or not
	 */
	public boolean isMarked() {
		return visited;
	}

	/**
	 * Returns the number of neighbors this vertex has (size of adjacency list)
	 * @return the number of neighbors this vertex has (size of adjacency list)
	 */
	public int adjSize() {
		return adj.size();
	}

	/**
	 * Returns the edge connecting this vertex to the Vertex labeled dst
	 * @param dst label of destination vertex
	 * @return the edge connecting this vertex to the Vertex labeled dst, or returns null if no such edge exists
	 */
	public Edge getAdj(String dst) {
		return adj.get(dst);
	}

	/**
	 * returns the information of this vertex in the following format: label (id, number_of_neighbors): neighbor1 neighbor2 neighbor3 ...
	 * @return information of this vertex as a string
	 */
	public String toString() {
		String str = label + "(" + id + ", " + adjSize() + "): "; 
		for(Edge e : adj.values()) {
			str += e.dst.label + " (" + e.cost + ") ";
		}
		return str;
	}

	/**
	 * Creates and adds a new Edge with vdst and w to this Vertex's neighbor list
	 * @param vdst destination vertex
	 * @param w weight
	 */
	public void addAdj(Vertex vdst, double w) {
		adj.put(vdst.label, new Edge(this, vdst, w));
	}
	
	/**
	 * Adds the given Edge to this Vertex's neighbor list
	 * @param e edge
	 */
	public void addAdj(Edge e) {
		adj.put(e.dst.label, e);
	}
	
}