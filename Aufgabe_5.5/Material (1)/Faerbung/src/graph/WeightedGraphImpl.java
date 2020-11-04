package graph;

import java.util.*;

/**
 * An implementation of the WeightedGraph interface where all
 * edges in the graph have a weight.
 */
public class WeightedGraphImpl extends GraphImpl implements WeightedGraph {

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of WeightedGraphImpl. Default algorithm
	 * for minimum spanning tree will use Kruskal's method
	 * (MinimumSpanningTreeKruskalAlgorithm).
	 */
	public WeightedGraphImpl(boolean isDirected) {
		super(isDirected);
	}

	/**
	 * Creates an instance of a WeightedEdge.
	 *
	 * @param   v1    One endpoint of the edge
	 * @param   v2    The other endpoint of the edge
	 */
	public Edge createEdge(Vertex v1, Vertex v2) {
		Edge edge;
		if (this.isDirected)
			edge = new DirectedWeightedEdge( v1, v2, 0 );
		else
			edge = new WeightedEdge( v1, v2, 0 );
		return edge;
	}

	/**
	 * Convenience method to add a WeightedEdge with a specified weight
	 * into the WeightedGraph. The default addEdge( v1, v2 ) will add a
	 * WeightedEdge with zero weight, after which you can call setWeight()
	 * to specify the weight.
	 *
	 * @return  The WeightedEdge that has been added.
	 */
	public WeightedEdge addEdge(Vertex v1, Vertex v2, double weight) throws Exception {
		WeightedEdge  edge = new WeightedEdge(v1, v2, weight);
		addEdge( edge );
		return edge;
	}

	/**
	 * Determines the Vertex that is 'closest' to the Vertex specified.
	 * The definition of the closest vertex in this context is a
	 * vertex that is directly adjacent to Vertex v where the edge
	 * has the least weight.
	 *
	 * @return  The Vertex closes to Vertex v.
	 */
	public Vertex getClosest(Vertex v) {
		// If the vertex has no edges, return null
		if (this.getEdges( v ).size() == 0)
			return null;

		// Specify a comparator to sort the adjacent edges by their weights
		TreeSet<Edge> set = new TreeSet<Edge>(
				new Comparator<Edge>() {
					public int compare(Edge e1, Edge e2) {
						WeightedEdge edge1 = (WeightedEdge)e1;
						WeightedEdge edge2 = (WeightedEdge)e2;
						if (edge1.getWeight() < edge2.getWeight())
							return -1;
						else if (edge1.getWeight() > edge2.getWeight())
							return 1;
						else
							return 0;
					}
					public boolean equals( Object obj ) {
						return obj.equals( this );
					}
				});

		set.addAll(this.getEdges( v ));
		Edge e = set.first();
		return e.getOppositeVertex( v );
	}
}