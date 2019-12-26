/**
 * Ryzeson Maravich
 * Graph.java
 * Graph
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Graph {

	ArrayList<Vertex> verts;
	ArrayList<Edge> edges;
	double[][] adjmat;
	HashMap<String, Vertex> mapLabel;

	/**
	 * Reads vertices (labels) from the given text file vFile and sets up the vertices for this graph and reads in the edge information from the given text file eFile (in the form of weight matrix) and sets up the edges for this graph
	 * @param vfile		contains a list of labels (vertices) separated by a single space
	 * @param efile		contains an adjacency matrix of weights
	 * @throws FileNotFoundException 
	 */
	public Graph(String vfile, String efile) throws FileNotFoundException {
		mapLabel = new HashMap<>();
		verts = new ArrayList<>();

		//Read vertex file
		File vertFile = new File(vfile);
		Scanner in = new Scanner(vertFile);
		while(in.hasNext()) {
			String label = in.next();
			Vertex vertex = new Vertex(label);
			verts.add(vertex);
			mapLabel.put(label, vertex);
		}
		in.close();

		edges = new ArrayList<>();
		int vertSize = verts.size();
		adjmat = new double[vertSize][vertSize];

		//Reads edge file
		File edgeFile = new File(efile);
		Scanner in2 = new Scanner(edgeFile);
		for(int row = 0; row < vertSize; row++) {
			Vertex vsrc = getVertex(row);
			for (int col = 0; col < vertSize; col++) {
				double cost = in2.nextDouble();
				adjmat[row][col] = cost;
				//add the edge to edge list, and update source vertex to contain edge information
				if (cost != 0) {
					Vertex vdst = getVertex(col);
					Edge edge = new Edge(vsrc, vdst, cost);
					edges.add(edge);
					vsrc.addAdj(edge);
				}
			}
		}
		in2.close();
	}

	/**
	 * Resets predecessor, cost, and visited fields of all vertices in this graph
	 */
	public void resetVerts() {
		for (Vertex v: verts) {
			v.reset();
		}
	}

	/**
	 * returns the list of vertices 
	 * @return the list of vertices 
	 */
	public ArrayList<Vertex> getVerts() {
		return verts;
	}

	/**
	 * returns the number of vertices in this graph
	 * @return the number of vertices in this graph
	 */
	public int nVerts() {
		return verts.size();
	}

	/**
	 * returns the number of edges in this graph 
	 * @return the number of edges in this graph 
	 */
	public int nEdges() {
		return edges.size();
	}

	/**
	 * Returns the vertex associated with label
	 * @param label	label of vertex
	 * @return the vertex associated with label or null if no vertex in this graph matches the label 
	 */
	public Vertex getVertex(String label) {
		return mapLabel.get(label);
	}

	/**
	 * Returns the index-th vertex (0-based)
	 * @param index	index of vertex
	 * @return the index-th vertex or null if index is invalid
	 */
	public Vertex getVertex(int index) {
		if (index >=0 && index < verts.size()) {
			return verts.get(index);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the edge from vsrc to vdst
	 * @param vsrc	source vertex
	 * @param vdst  destination vertex
	 * @return the edge from vsrc to vdst or null if no edge exists
	 */
	public Edge getEdge(Vertex vsrc, Vertex vdst) { 
		return vsrc.getAdj(vdst.label);
	}

	/**
	 * Returns the edge from src to dst
	 * @param src	source label
	 * @param dst	destination label
	 * @return the edge from src to dst or null if no edge exists
	 */
	public Edge getEdge(String src, String dst) {
		if (getVertex(src) == null) {
			return null;
		}
		else {
			return getVertex(src).getAdj(dst);
		}
	}

	/**
	 * Returns the adjacency matrix of this graph
	 * @return the adjacency matrix of this graph
	 */
	public double[][] adjMatrix() {
		return adjmat;
	}

	/**
	 * returns the information of this graph in the following String format: 
	 * label (index, # of neighbors): neighbor1 (cost) neighbor2 (cost) ...
	 * @return information of this graph as a string
	 */
	public String toString() {
		String str = "";
		for (Vertex v: verts) {
			str += v.toString() + "\n";
		}
		return str;
	}

	/**
	 * EdgeComp.java
	 * comparator for edge weights first then edge labels
	 * compare first the weights of the edges
	 *         then source vertex labels        (if same weight)
	 *         then destination vertex labels   (if same weight and same source vertex)
	 */

	private class EdgeComp implements Comparator<Edge> {
		public int compare(Edge e1, Edge e2) {
			int diff = (int) (Math.round((e1.cost - e2.cost) * 100)) / 100;

			// if weights of the two edges are the same, compare the edge labels
			if (diff == 0) {
				String eLabel1 = e1.src.label + e1.dst.label;
				String eLabel2 = e2.src.label + e2.dst.label;

				return eLabel1.compareTo(eLabel2);
			}
			else {
				return diff;
			}
		}
	}

	/**
	 * (to be used for Kruskal's algorithm)
	 * internally convert directed graph to undirected graph 
	 * and return the edges sorted by weight and label in increasing order
	 * @return list of edges sorted by weight first then by edge label in increasing order
	 */
	public ArrayList<Edge> getEdgesUndirected() {
		ArrayList<Edge> und = new ArrayList<Edge>();
		EdgeComp edgeComp = new EdgeComp();

		for (Edge e : edges) {
			Vertex src = e.src;
			Vertex dst = e.dst;
			double w   = e.cost;

			// each edge's src vertex is < dst vertex in 
			// undirected graph for easier comparison
			String srcLabel = src.label;
			String dstLabel = dst.label;

			if (srcLabel.compareTo(dstLabel) > 0) {
				Vertex tmp = src;
				src = dst;
				dst = tmp;
			}

			Edge ne = new Edge(src, dst, w);

			// similar to one iteration of the insertion sort algorithm
			// add the newly created edge to a correct location according 
			// to the sort order
			int i = 0, eCount = und.size();
			while (i < eCount && edgeComp.compare(ne, und.get(i)) > 0) {
				++i;
			}
			und.add(i, ne);
		}

		return und;
	}

	/**
	 * (to be used before applying the MST algorithms)
	 * for each directed edge (v->u) add edge (u->v) if it doesn't exist
	 * if it does, update edge (u->v)'s weight to the weight of edge (v->u)
	 * update adjacency matrix accordingly
	 */
	public void toUndirected() {
		for (Vertex v : verts) {
			int vID = v.id;
			//TreeMap<String, Edge> v_adj = v.getAdj();
			TreeMap<String, Edge> v_adj = v.adj;

			// for each edge (v->u)
			for (Edge e : v_adj.values()) {
				Vertex u = e.dst;
				double w = e.cost;
				int uID  = u.id;

				// add v as neighbor to dst if it isn't already a neighbor
				// edge (u->v)
				if (u.getAdj(v.label) == null) {
					Edge newE = new Edge(u, v, w);
					u.addAdj(newE);
					edges.add(newE);
				}

				// if edge (u->v) already exists, need to update it with the same weight
				else {
					Edge oldE = u.getAdj(v.label);
					oldE.cost = w;
				}

				// update the adjacency matrix accordingly
				adjmat[uID][vID] = w;
			}
		}
	}
}
