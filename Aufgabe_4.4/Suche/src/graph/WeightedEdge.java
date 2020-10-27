package graph;

/**
 * Represents a weighted edge in a graph.
 */
public class WeightedEdge extends Edge {

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The weight of the edge.
	 */
	protected double weight;

	/**
	 * Creates an WeightedEdgeImpl object.
	 *
	 * @see		Vertex
	 */
	public WeightedEdge( Vertex a, Vertex b, double weight ) {
		super( a, b );
		this.weight = weight;
		this.str = vertexA.toString() + "-" + vertexB.toString() + " ( " + this.weight +" )";
	}

	/**
	 * Returns the weight of the edge.
	 */
	public double getWeight() {
		return this.weight;
	}

	/**
	 * Sets the weight of the edge.
	 *
	 * @param   weight    The new weight of the edge
	 */
	public void setWeight( double weight ) {
		this.weight = weight;
	}
}