/**
 * Ryzeson Maravich
 * GraphUtil.java (hw9)
 * Various traversal and shortest path algorithms for graph
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphUtil {

	/**
	 * Lists the vertices (labels) visited by the Breadth-First-Search traversal algorithm on the graph g from the src vertex 
	 * @param g		graph
	 * @param src	label of source vertex
	 */
	public static void bfs(Graph g, String src) {
		Queue<Vertex> q = new LinkedList<>();
		q.add(g.getVertex(src));

		while (!q.isEmpty()) {
			Vertex v = q.poll();
			v.mark();

			System.out.print(v.label + "-");

			for (Edge e : v.adj.values()) {
				Vertex adjV = e.dst;
				if (!adjV.isMarked()) {
					q.add(adjV);
					adjV.mark();
				}
			}
		}
		System.out.print("(done)\n");
	}

	/**
	 * Lists the vertices (labels) visited by the Depth-First-Search traversal algorithm on the graph g from the src vertex
	 * @param g		graph
	 * @param src	label of source vertex
	 */
	public static void dfs(Graph g, String src) {
		Vertex v = g.getVertex(src);
		v.mark();

		System.out.print(v.label + "-");

		for (Edge e :  v.adj.values()) {
			Vertex adjV = e.dst;
			if (!adjV.isMarked()) {
				dfs(g, adjV.label);
			}
		}
	}

	/**
	 * 1. Applies the Dijkstra's shortest path algorithm to the graph g from the src vertex
	 * 2. Prints the paths from src to each vertex in the graph g using printPath
	 * @param g		graph
	 * @param src	label of source vertex
	 */
	public static void dijkstra(Graph g, String src) {
		for (Vertex v : g.verts) {
			v.pred = null;
			v.visited = false;
			v.cost = Double.POSITIVE_INFINITY;
		}

		Vertex vsrc = g.getVertex(src);
		vsrc.cost = 0;
		ArrayList<Vertex> pq = new ArrayList<>();
		pq.addAll(g.verts);

		while (!pq.isEmpty()) {
			Vertex v = removeMin(pq);
			v.mark();
			for(Edge e : v.adj.values()) {
				Vertex adjV = e.dst;
				if (!adjV.isMarked() && adjV.cost > (v.cost + e.cost)) {
					adjV.cost = v.cost + e.cost;
					adjV.pred = v;
				}
			}
		}
		
		for (Vertex v : g.verts) {
			printPath(g, src, v.label);
		}

	}

	/**
	 * Removes and returns the Vertex with the lowest cost
	 * @param pq priority queue of Vertices
	 * @return Vertex with the lowest cost
	 */
	public static Vertex removeMin(ArrayList<Vertex> pq) {
		Vertex lcVertex = pq.get(0);
		for (int i = 1; i < pq.size(); i++) {
			if (pq.get(i).cost < lcVertex.cost) {
				lcVertex = pq.get(i);
			}
		}
		pq.remove(lcVertex);
		return lcVertex;
	}

	/**
	 * Prints the path from src to dst assuming a shortest path algorithm had been applied and vertices have proper information (cost and predecessor of the path, if exists)
	 * @param g		graph
	 * @param src	label of source vertex
	 * @param dst	label of destination vertex
	 */
	public static void printPath(Graph g, String src, String dst) {
		String path = dst;

		if (src != dst) {
			Vertex ptr = g.getVertex(dst);
			Vertex vdst = ptr;
			if(ptr.pred == null) {
				System.out.println(src + " --> " + dst + ": no path");
			}
			else {
				while(ptr.pred != null) {
					path = ptr.pred.label + "-" + path;
					ptr = ptr.pred;
				}

				System.out.println(src + " --> " + dst + ": (" + vdst.cost + ") " + path + " (done)");
			}
		}
	}
	
	/**
	 * Prints the edges in the array in the order they were added, with the smallest (alphabetically first) vertex listed first 
	 * @param mst minimum spanning tree
	 */
	private static void printPath(ArrayList<Edge> mst) {
		double totalCost = 0;
		String edges = "";
		for (Edge e : mst) {
			if (e.src.label.compareTo(e.dst.label) > 0) {
				edges += ("" + e.dst.label + "-" + e.src.label + " (" + e.cost + ") ");
			}
			else {
				edges += e.toString() + " ";
			}
			totalCost += e.cost;
		}
		System.out.println("(" + totalCost + ") [ " + edges + "]");
	}

	/**
	 * applies Floyd-Warshall algorithm to compute the all-pairs shortest paths algorithm to g and prints the paths from every vertex to every other vertex
	 * @param g	graph
	 */
	public static void floyd_warshall(Graph g) {
		//create and initialize distance and predecessor matrices
		int n = g.nVerts();
		double[][] dist = new double[n][n];
		Vertex[][] pred = new Vertex[n][n];
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				if (g.adjmat[row][col] == 0) {
					dist[row][col] = Double.POSITIVE_INFINITY;
					pred[row][col] = null;
				}
				else {
					dist[row][col] = g.adjmat[row][col];
					pred[row][col] = g.verts.get(row);
				}
			}
		}

		//find all shortest paths and updates the two matrices
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					double newDist = dist[i][k] + dist[k][j];
					if (newDist < dist[i][j]) {
						dist[i][j] = newDist;
						pred[i][j] = pred[k][j];
					}
				}
			}
		}

		for (int row = 0; row < n; row++) {
			Vertex vsrc = g.verts.get(row);
			for (int col = 0; col < n; col++) {
				Vertex vdst = g.verts.get(col);
				if (row != col) {
					System.out.print(vsrc.label + " --> " + vdst.label + ":");
					if (pred[row][col] == null) {
						System.out.print("no path\n");
					}
					else {
						System.out.print("(" + dist[row][col] + ") ");
						//print path using pred matrix
						String path = "";
						Vertex v = vdst;
						while (v != vsrc) {
							path = pred[row][v.id].label + "-" + path;
							v = pred[row][v.id];
						}
						System.out.println(path + vdst.label);
					}
				}
			}
		}
	}

	/**
	 * applies Prim's algorithm and prints the total cost and the edges in the minimum spanning tree in the order they are added to it
	 * @param g		graph
	 * @param src	label of source vertex
	 */
	public static void prim(Graph g, String src) {
		for (Vertex v: g.verts) {
			v.pred = null;
			v.visited = false;
			v.cost = Double.POSITIVE_INFINITY;
		}
		Vertex vsrc = g.getVertex(src);
		vsrc.cost = 0;
		ArrayList<Vertex> pq = new ArrayList<>();
		pq.addAll(g.verts);
		ArrayList<Edge> mst = new ArrayList<>();

		while (!pq.isEmpty()) {
			Vertex v = removeMin(pq);
			v.mark();
			if (v.pred != null) {
				mst.add(g.getEdge(v, v.pred));
			}
			for (Edge e : v.adj.values()) {
				Vertex adjV = e.dst;
				if (!adjV.isMarked() && e.cost < adjV.cost) {
					adjV.cost = e.cost;
					adjV.pred = v;
				}
			}
		}
		
		printPath(mst);
	}

	/**
	 * applies Kruskal's algorithm and prints the total cost and the edges in the minimum spanning tree in the order they are added to its
	 * @param g		graph
	 */
	public static <E> void kruskal(Graph g) {
		ArrayList<Edge> mst = new ArrayList<>();
		DisjointSet<Vertex> dj = new DisjointSet<Vertex>(g.verts);
		ArrayList<Edge> sortedEdges = g.getEdgesUndirected();
		for (Edge e : sortedEdges) {
			if (dj.findRep(e.src) != dj.findRep(e.dst)) {
				mst.add(e);
				dj.union(e.src, e.dst);
			}
			if (mst.size() == g.nVerts() - 1) {
				break;
			}
		}
		
		printPath(mst);
	}
}
