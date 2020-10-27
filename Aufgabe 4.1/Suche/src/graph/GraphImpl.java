package graph;

import java.util.*;
import graph.algorithm.*;

/**
 * An implementation of the Graph interface. A Graph object
 * represents a graph data structure, which are vertices
 * connected by edges, where the edges are non-directional.
 */
public class GraphImpl implements Graph {

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  boolean variable that is <tt>true</tt> if graph is directed
	 */
	protected boolean isDirected = false;

	/**
	 * List of vertices in the graph.
	 */
	protected ArrayList<Vertex> vertices;

	/**
	 * List of edges in the graph. Each element in the List is a
	 * List in itself, such that each element is are the incident edges of
	 * a Vertex. The index of the Vertex in vertices is the same index
	 * as that incident edges of the Vertex in edges.
	 */
	protected List<List<Edge>> edges;

	public GraphImpl(boolean directed) {
		isDirected = directed;
		vertices = new ArrayList<Vertex>( 10 );
		edges = new ArrayList<List<Edge>>( 10 );
	}

	/**
	 * Returns true if the Graph is directed.
	 */
	public boolean isDirected() {
		return isDirected;
	}

	/**
	 * Returns an iterator that iterates through the graph's vertices.
	 *
	 * @return  An itereator of List vertices.
	 */
	public Iterator<Vertex> getVerticesIterator() {
		return this.vertices.iterator();
	}

	/**
	 * Returns a clone of the List of vertices.
	 *
	 * @return  A clone of the List of vertices.
	 */
	@SuppressWarnings("unchecked")
	public List<Vertex> cloneVertices() {
		return (List<Vertex>) this.vertices.clone();
	}

	/**
	 * Returns a List of edges of the specified vertex.
	 *
	 * @param   v   The vertex whose edges we want returned
	 * @return  A List of Edges that are incident edges of the specified vertex.
	 */
	public List<Edge> getEdges(Vertex v) {
		List<Edge>    incidentedges = null;
		int index = this.vertices.indexOf( v );

		if( index >= 0 ) {
			incidentedges = this.edges.get( index );
		}
		return incidentedges;
	}

	/**
	 * Returns a set of all edges in the graph.
	 *
	 * @return  A Set of all Edges in the Graph.
	 */
	public Set<Edge> getAllEdges() {
		HashSet<Edge> allEdges = new HashSet<Edge>();
		for (List<Edge> l : edges)
			allEdges.addAll(l);
		return allEdges;
	}

	/**
	 * Adds a Vertex into the Graph. This will also create a new entry
	 * in the edges List and add the newly added Vertex to its own
	 * connected set, thereby adding a new List in the connectedSet List.
	 * Finally, all GraphAddVertexListeners are informed of the event that a
	 * Vertex has been added to the Graph.
	 *
	 * @param    newvertex   Vertex to be added to the Graph
	 */
	public void add(Vertex newvertex) throws Exception {
		// Add the vertex
		vertices.add( newvertex );

		// Whenever a new vertex is added, we also need to create
		// a blank adjacenct edge list for the new vertex
		edges.add( new ArrayList<Edge>( 10 ));
	}

	/**
	 * Returns <code>true</code> if there is an edge from v1 to v2.
	 *
	 * @param   v1    One endpoint of the edge
	 * @param   v2    Other endpoint of the edge
	 */
	public boolean haveCommonEdge( Vertex v1, Vertex v2 ) {
		List<Edge> v1edges = getEdges(v1);

		for (Edge edge : v1edges)
			if (isDirected) {
				DirectedEdge e = (DirectedEdge)edge;
				if (e.getSource()==v1)
					if (e.getSink()==v2)
						return true;
			} else {
				if (edge.getOppositeVertex(v1)==v2)
					return true;
			}
		return false;
	}

	/**
	 * Method to create the proper type of Edge class.
	 *
	 * @param   v1    One endpoint of the edge
	 * @param   v2    Other endpoint of the edge
	 */
	public Edge createEdge( Vertex v1, Vertex v2 ) {
		Edge edge;
		if (this.isDirected)
			edge = new DirectedEdge( v1, v2 );
		else
			edge = new Edge( v1, v2 );
		return edge;
	}

	/**
	 * Adds an Edge into the Graph. The vertices of the Edge need not already
	 * be existing in the Graph for this method to work properly.
	 * The vertices in both ends of the Edge are merged into one connected set,
	 * thereby possibly decreasing the number of Lists in the coonectedSet List.
	 * Finally, all GraphAddEdgeListeners are informed of the event that a
	 * Edge has been added to the Graph.
	 *
	 * @param    v1  One endpoint of the edge
	 * @param    v2  Other endpoint of the edge
	 * @return   The Edge object created and added to the Graph.
	 */
	public Edge addEdge( Vertex v1, Vertex v2 ) throws Exception {
		Edge    edge;
		List<Edge>  v1edges, v2edges;

		// If the vertices of the edge are not in the Graph, add them.
		if( !this.vertices.contains( v1 ) )
			this.add( v1 );
		if( !this.vertices.contains( v2 ) )
			this.add( v2 );

		if (this.isDirected)
			edge = new DirectedEdge( v1, v2 );
		else
			edge = new Edge( v1, v2 );

		v1edges = this.getEdges( v1 );
		v2edges = this.getEdges( v2 );

		// Add the edge as an incident edge of both vertices
		v1edges.add( edge );
		v2edges.add( edge );

		return edge;
	}

	/**
	 * Adds an Edge into the Graph. The vertices of the Edge need not be
	 * existing in the Graph for this method to work properly.
	 * The vertices in both ends of the Edge are merged into one connected set,
	 * thereby possibly decreasing the number of Lists in the coonectedSet List.
	 * Finally, all GraphAddEdgeListeners are informed of the event that a
	 * Edge has been added to the Graph.
	 * <p>
	 * In the event that any one of the vertices are not existing in the Graph,
	 * they are added to the Graph.
	 * <p>
	 * <b>Note:</b> It is the caller's responsibility to make sure that the
	 * type of Edge being added is an EdgeImpl.
	 *
	 * @param  e   The edge to be added to the Graph.
	 */
	public void addEdge( Edge edge ) throws Exception {
		Vertex      v1, v2;
		List<Edge>  v1edges, v2edges;

		v1 = edge.getVertexA();
		v2 = edge.getVertexB();

		// If the vertices of the edge are not in the Graph, add them.
		if( !this.vertices.contains( v1 ) )
			this.add( v1 );
		if( !this.vertices.contains( v2 ) )
			this.add( v2 );

		// Only then should we call getEdges, since add( Vertex ) will
		// initialise these edgelist of a vertex.
		v1edges = this.getEdges( v1 );
		v2edges = this.getEdges( v2 );

		// Add the edge as an incident edge of both vertices
		v1edges.add( edge );
		v2edges.add( edge );
	}

	/**
	 * Remove a vertex and adjacent edges
	 */
	public void remove( Vertex v ) throws Exception {
		// Remove all the edges of the vertex.
		this.removeEdges( v );

		// Remove the adjacent edges list entry of the vertex
		this.edges.remove( this.vertices.indexOf( v ));

		// Finally, remove the vertex
		this.vertices.remove( v );
	}

	/**
	 * Removes the specified Edge from the Graph.
	 *
	 * @param   edge    The Edge object to be removed.
	 */
	public void removeEdge( Edge edge ) throws Exception {
		Vertex  	v1, v2;
		List<Edge>	v1edges, v2edges;

		// Remove the edge from the vertices incident edges.
		v1 = edge.getVertexA();
		v1edges = this.getEdges( v1 );
		v1edges.remove( edge );

		v2 = edge.getVertexB();
		v2edges = this.getEdges( v2 );
		v2edges.remove( edge );
	}

	/**
	 * Removes incident Edges of a Vertex. The Edges removed are those whose
	 * either endpoints has the specified vertex. This method is usually
	 * called just prior to removing a Vertex from a Graph.
	 *
	 * @param    v Vertex whose Edges are to be removed
	 */
	public void removeEdges( Vertex v ) throws Exception {
		List<Edge> vedges;
		Iterator<Edge> iterator;
		Edge edgetoremove;

		// Remove incident edges of vertex
		vedges = this.getEdges( v );
		for (iterator = vedges.iterator(); iterator.hasNext( ); ) {
			edgetoremove = iterator.next();
			this.removeEdge( edgetoremove );
			// Reinitialize the iterator as the removeEdge() method would have
			// modified the List.
			iterator = vedges.iterator();
		}
	}

	/**
	 * Returns the number of vertices in the graph
	 *
	 * @return The number of vertices in the graph.
	 */
	public int getVerticesCount() {
		return this.vertices.size();
	}

	/**
	 * Returns all vertices.
	 *
	 * @return  A collection containing all vertices.
	 */
	public List<Vertex> getVertices() {
		return this.vertices;
	}

	/**
	 * Returns all vertices with the specified degree.
	 *
	 * @param   degree    The degree of the vertex to be returned.
	 * @return  A collection of vertices with the above specified degree.
	 */
	public Set<Vertex> getVertices(int degree) {
		Set<Vertex> verticesofsamedegree = new HashSet<Vertex>();
		for (Vertex vertex : vertices) {
			if (this.getAdjacentVertices(vertex).size() == degree)
				verticesofsamedegree.add(vertex);
		}
		return verticesofsamedegree;
	}

	/**
	 * Returns the vertices adjacent to the specified vertex.
	 *
	 * @param   v  The Vertex you want to determine its adjacent vertices.
	 * @return   List of vertices adjacent to the specified vertex v.
	 */
	public List<Vertex> getAdjacentVertices(Vertex v) {
		List<Vertex> adjacentVertices = new ArrayList<Vertex>(10);
		List<Edge> incidentEdges = this.getEdges( v );
		Vertex    oppositeVertex;

		if( incidentEdges != null ) {
			for (Edge edge : incidentEdges) {
				oppositeVertex = edge.getOppositeVertex( v );
				if( oppositeVertex != null )
					adjacentVertices.add( oppositeVertex );
			}
		}
		return adjacentVertices;
	}

	/**
	 * Returns the vertices adjacent to the specified vertex where the connecting
	 * edge is directed from the adjacent vertex to the specified vertex.
	 * This method is useful only for directed graphs.
	 *
	 * @param    v The Vertex you want to determine its adjacent vertices.
	 * @return   List of vertices adjacent to the specified vertex v.
	 */
	public List<Vertex> getIncomingAdjacentVertices( Vertex v ) {
		return getAdjacentVertices( v, false );
	}

	/**
	 * Returns the vertices adjacent to the specified vertex where the connecting
	 * edge is directed from the specified vertex to the adjacent vertex.
	 * This method is useful only for directed graphs.
	 *
	 * @param    v The Vertex you want to determine its adjacent vertices.
	 * @return   List of vertices adjacent to the specified vertex v.
	 */
	public List<Vertex> getOutgoingAdjacentVertices( Vertex v ) {
		return getAdjacentVertices( v, true );
	}

	/**
	 * Returns the vertices adjacent to the specified vertex where the connecting
	 * edge is directed from the specified vertex to the adjacent vertex if the
	 * parameter outgoing is true, otherwise from the adjacent vertex to the
	 * specified vertex.
	 * This method is useful only for directed graphs.
	 *
	 * @param  v         The Vertex you want to determine its adjacent vertices.
	 * @param  outgoing  Determines the direction of the connecting edges.
	 * @return   List of vertices adjacent to the specified vertex v.
	 */
	public List<Vertex> getAdjacentVertices( Vertex v, boolean outgoing) {
		if ( !isDirected )
			return getAdjacentVertices( v );

		// Graph is directed
		List<Vertex> adjacentVertices = new ArrayList<Vertex>(10);
		List<Edge> incidentEdges = this.getEdges( v );
		Vertex    oppositeVertex;

		if( incidentEdges != null ) {
			for (Edge edge : incidentEdges) {
				if (outgoing)
					oppositeVertex = edge.getVertexB();
				else
					oppositeVertex = edge.getVertexA();
				if (oppositeVertex != null && oppositeVertex != v)
					adjacentVertices.add( oppositeVertex );
			}
		}
		return adjacentVertices;
	}

	/**
	 * Returns the vertices adjacent to all the vertices in the given collection.
	 *
	 * @param    vertices    List of Vertex where each vertex in the returned Set
	 *                       must be adjacent to.
	 * @return   Set of vertices adjacent to all the vertices in the supplied List.
	 */
	public Set<Vertex> getAdjacentVertices(List<Vertex> vertices) {
		int i, size = vertices.size();
		HashSet<Vertex> adjacentVertices = new HashSet<Vertex>(this.getAdjacentVertices(vertices.get(0)));
		
		for( i = 1; i < size; i++ ) {
			adjacentVertices.retainAll(this.getAdjacentVertices(vertices.get(i)));
		}
		return adjacentVertices;
	}

	/**
	 * Returns the degree of the graph, which is simply the highest degree
	 * of all the graph's vertices.
	 *
	 * @return  An int indicating the degree of the graph.
	 */
	public int getDegree( ) {
		Vertex  v;
		HashSet<Vertex> set;

		set = new HashSet<Vertex>(this.vertices);
		if( set.size() > 0 ){
			v = Collections.max(set, new Comparator<Vertex>() {
				public int compare(Vertex v1, Vertex v2) {
					int    countv1 = getDegree( v1 );
					int    countv2 = getDegree( v2 );

					if( countv1 < countv2 ) return -1;
					if( countv1 > countv2 ) return 1;
					else return 0;
				}
				public boolean equals(Object objcomparator) {
					return objcomparator.equals( this );
				}
			});
			return this.getEdges( v ).size();
		}
		else
			return 0;
	}

	/**
	 * Returns the degree of the vertex, which is simply the number of edges
	 * of the vertex.
	 *
	 * @return  The degree of the vertex.
	 */
	public int getDegree( Vertex v ) {
		return this.getEdges( v ).size();
	}

	/**
	 * Returns a String representation of the Graph. The string returned in the form:
	 * "Vertices: " + this.vertices.toString() + "\n" + "Edges: " + this.edges.toString()
	 *
	 * @return  String representation of the Graph
	 */
	public String toString() {
		return "Vertices: " + this.vertices.toString() + "\n" +
		"Edges: " + this.getAllEdges().toString();
	}

	/**
	 * Determines if there is a path from Vertex fromVertex to Vertex toVertex.
	 * This will not return true if the only path has at least one Edge pointing
	 * in the opposite direction of the path.
	 *
	 * @param   fromVertex    starting Vertex for the path
	 * @param   toVertex      ending Vertex for the path
	 * @return  true if there is a path from Vertex to toVertex. false otherwise.
	 */
	public boolean isPath( Vertex fromVertex, Vertex toVertex ){
		List<Vertex>  visited = new ArrayList<Vertex>( 10 );
		GraphTraversal traversal = new DepthFirstGraphTraversal(this);

		traversal.traverse( fromVertex, visited, new StopAtVisitor(toVertex));
		if (toVertex == visited.get(visited.size()-1))
			return true;
		else
			return false;
	}
}

