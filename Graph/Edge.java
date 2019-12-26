/**
 * Ryzeson Maravich
 * Edge.java
 * Edge of a graph
 */
public class Edge {
	
	Vertex src, dst;
	double cost;
	
	/**
	 * Instantiates a new Edge
	 * @param vsrc	source vertex
	 * @param vdst	destination vertex
	 * @param w		weight
	 */
	public Edge(Vertex vsrc, Vertex vdst, double w) {
		src = vsrc;
		dst = vdst;
		cost = w;
	}
	
	/**
	 * Instantiates a new Edge with a weight of 1
	 * @param vsrc	source vertex
	 * @param vdst	destination vertex
	 */
	public Edge(Vertex vsrc, Vertex vdst) {
		this(vsrc, vdst, 1);
	}
	
	/**
	 * Overrides the equals method in Object class
	 * @return true if obj is of Edge type and its source and destination labels match the labels of this Edge's source and destination vertices
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Edge) {
			Edge edge = (Edge) obj;
			if (edge.src.equals(src) && edge.dst.equals(dst)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the information of this Edge in the following String format: srcLabel-dstLabel (cost)
	 * @return the information of this edge as a string
	 */
	public String toString() {
		return ("" + src.label + "-" + dst.label + " (" + cost + ")");
	}
}
