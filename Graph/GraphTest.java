import java.io.FileNotFoundException;

public class GraphTest {

	public static void main(String[] args) throws FileNotFoundException {		
		System.out.println("TEST GRAPH");

		Graph g = new Graph(args[0], args[1]);
//		Graph g = new Graph("g1_labels", "g1_w");

		System.out.println(g.toString());
		//		A ( 0,  3): B (2.0) D (5.0) E (4.0) 
		//		B ( 1,  1): E (1.0) 
		//		C ( 2,  1): B (3.0) 
		//		D ( 3,  1): G (2.0) 
		//		E ( 4,  2): F (3.0) H (6.0) 
		//		F ( 5,  2): C (4.0) H (3.0) 
		//		G ( 6,  1): H (1.0) 
		//		H ( 7,  1): I (1.0) 
		//		I ( 8,  1): F (1.0) 

		System.out.println("\nGRAPH UTIL");

		System.out.println("BFS SEARCH");
		for (Vertex v : g.verts) {
			g.resetVerts();
			System.out.print("bfs(" + v.label + "): ");
			GraphUtil.bfs(g, v.label);
		}
		
		System.out.println("DFS SEARCH");
		for (Vertex v : g.verts) {
			g.resetVerts();
			System.out.print("dfs(" + v.label + "): ");
			GraphUtil.dfs(g, v.label);
			System.out.print("(done) \n");
		}
		
		
		System.out.println("DIJKSTRA");
		for (Vertex v : g.verts) {
			g.resetVerts();
			System.out.println("dijkstra(" + v.label + "): ");
			GraphUtil.dijkstra(g, v.label);
		}
		
		System.out.println("FLOYD-WARSHALL");
		GraphUtil.floyd_warshall(g);
		
		g.toUndirected();
		
		System.out.println("PRIM");
		for (Vertex v : g.verts) {
			g.resetVerts();
			System.out.print("prim(" + v.label + "): ");
			GraphUtil.prim(g, v.label);
		}
		
		System.out.println("KRUSKAL");
		System.out.print("kruskal: ");
		GraphUtil.kruskal(g);

//		System.out.println("\nTEST VERTEX");
//		Vertex v = new Vertex("A");
//		Vertex v2 = new Vertex("B");
//		Vertex v3 = new Vertex("C");
//		Vertex v4 = new Vertex("D");
//		System.out.println(v.toString());
//
//		Edge e = new Edge(v, v2);
//		v.addAdj(e);
//		System.out.println(v.toString());
//
//		v.addAdj(v3, 2);
//		System.out.println(v.toString());
//
//		Edge e2 = new Edge(v, v4, 3);
//		v.addAdj(e2);
//		System.out.println(v.toString());
//
//		System.out.println("\nTEST EDGE");
//		System.out.println(e.toString());
//		System.out.println(e2.toString());
//
//		System.out.println("Does e equal e?: " + e.equals(e));
//		System.out.println("Does e equal e2?: " + e.equals(e2));
//		System.out.println("Does e equal v?: " + e.equals(v));

	}

}
