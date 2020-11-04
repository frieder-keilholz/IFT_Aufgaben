package graph.algorithm;

import java.util.*;
import graph.*;

/**
 * A concrete subclass of GraphTraversal that performs a topological sort
 * against a directed acyclic graph.
 */

public class TopologicalSorting extends GraphTraversal {

	private static final long serialVersionUID = 1L;

	DirectedAcyclicGraph  dag;
	int count;

	/**
	 * Creates an instance of TopologicalSorting that will perform a
	 * topological sort against a directed acyclic graph.
	 *
	 * @param dag   The DirectedAcyclicGraph on which topological sorting will be performed.
	 */
	public TopologicalSorting(DirectedAcyclicGraph dag) {
		super( dag );
		this.dag = dag;
		this.count = 0;
	}

	/**
	 * Perform a topological sort of the connected set of a directed acyclic graph
	 * to which Vertex startat belongs, starting at Vertex startat.
	 *
	 * @param	startat	  The Vertex to which you want to start the traversal.
	 * @param	visited	  List of vertices that has been visited,
	 *                  in the sequence they were visited.
	 * @param visitor   Visitor object to visit each vertex as they are visited.
	 *                  Return value of the visitor is ignored.
	 */
	public int traverse(Vertex startat, List<Vertex> visited, Visitor visitor) {
		List<Vertex> currentadjacentVertices;

		// Let the visitor object visit this vertex. Ignore return value.
		visitor.visit( startat );
		// Mark vertex as visited.
		visitedMap.put( startat, new Integer(count++) );

		// Get the adjacency list for the current vertex,
		// from which we can get the adjacent vertices
		currentadjacentVertices = this.dag.getOutgoingAdjacentVertices( startat );

		// For each vertex adjacent to the current vertex, visit the vertex,
		// calling this method recursively
		for (Vertex adjacentVertex : currentadjacentVertices) {
			if (visitedMap.get(adjacentVertex) == null)
				// Recursive call. Ignore return value of traversal
				this.traverse( adjacentVertex, visited, visitor );
		}
		// Now put the current vertex at the beginning of the list.
		visited.add( 0, startat );
		return OK;
	}

	/**
	 * Perform a topological sort of the connected set of a directed acyclic graph
	 * to which Vertex startat belongs, starting at Vertex startat.
	 *
	 * @param	startat	  The Vertex to which you want to start the traversal.
	 * @param visitor   Visitor object to visit each vertex as they are visited.
	 *                  Return value of the visitor is ignored.
	 *
	 * @return  A List of vertices in the order that they were visited.
	 */
	public List<Vertex> traverse(Vertex startat, Visitor visitor) {
		List<Vertex> visited = new ArrayList<Vertex>( 10 );

		this.traverse(startat, visited, visitor);
		return visited;
	}

	/**
	 * Perform a topological sort of the connected set of a directed acyclic graph
	 * to which Vertex startat belongs, starting at Vertex startat.
	 *
	 * @param	startat	  The Vertex to which you want to start the traversal.
	 *
	 * @return  A List of vertices in the order that they were visited.
	 */
	public List<Vertex> traverse(Vertex startat) {
		return this.traverse(startat, new NullVisitor());
	}

	/**
	 * Perform a reverse topological sort of the connected set of a directed acyclic graph
	 * to which Vertex startat belongs, starting at Vertex startat.
	 *
	 * This method is not part of the GraphTraversal abstract class, but is added
	 * here for convenience.
	 *
	 * @param	startat	  The Vertex to which you want to start the traversal.
	 *
	 * @return  A List of vertices in the order that they were visited.
	 */
	public List<Vertex> reverseTraverse(Vertex startat) {
		List<Vertex> sortSequence = this.traverse(startat, new NullVisitor());
		Collections.reverse( sortSequence );
		return sortSequence;
	}

	/**
	 * Perform a topological sort of the entire directed acyclic graph.
	 * Note that the sequence of vertices in the return List will not distinguish
	 * between connected components of the graph.
	 *
	 * This method is not part of the GraphTraversal abstract class, but is added
	 * here for convenience.
	 *
	 * @return List containing the sequence of the vertices visited in the
	 * entire directed acyclic graph, regardless of the connected components of the graph.
	 */
	public List<Vertex> traverse() {
		List<Vertex> sortSequence = new ArrayList<Vertex>( 10 );
		List<Vertex> rootVertices = new ArrayList<Vertex>( 10 );

		rootVertices = this.dag.getRoot();
		for (Vertex vertex : rootVertices) {
			this.traverse(vertex, sortSequence, new NullVisitor());
		}

		return sortSequence;
	}

	/**
	 * Perform a reverse topological sort of the entire directed acyclic graph.
	 * Note that the sequence of vertices in the return List will not distinguish
	 * between connected components of the graph.
	 *
	 * This method is not part of the GraphTraversal abstract class, but is added
	 * here for convenience.
	 *
	 * @return List containing the sequence of the vertices visited in the
	 * entire directed acyclic graph, regardless of the connected components of the graph.
	 */
	public List<Vertex> reverseTraverse( ){
		List<Vertex> sortSequence = this.traverse();
		Collections.reverse(sortSequence);
		return sortSequence;
	}
}