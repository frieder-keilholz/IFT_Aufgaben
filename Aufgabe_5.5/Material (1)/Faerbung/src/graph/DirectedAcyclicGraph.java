package graph;

import java.util.*;

/**
 * The DirectedAcyclicGraph class represents a directed acyclic graph (DAG)
 * where there is no cyclic paths for any of its vertices.
 *
 * A cylic path is a path from a vertex back to itself by following the
 * direction of the edges.
 */
public interface DirectedAcyclicGraph extends Graph {

	/**
	 * Returns a List of vertices that is not depended on by other vertices.
	 * That is, a List of vertices where there are no Edges pointing to it.
	 *
	 * @return	List of vertices
	 */
	public List<Vertex> getRoot( );

	/**
	 *  Perform a topological sort of the entire directed acyclic graph.
	 *  Note that the sequence of vertices in the return List will not distinguish
	 *  between connected components of the graph.
	 *
	 *  @return List containing the sequence of the vertices visited in the entire graph,
	 *  regardless of the connected components of the graph.
	 *  @see #reverseTopologicalSort()
	 */
	public List<Vertex> topologicalSort( );

	/**
	 *  Perform a reverse topological sort of the entire directed acyclic graph.
	 *  Note that the sequence of vertices in the return List will not distinguish
	 *  between connected components of the graph. This simply calls topologicalSort()
	 *  and reverses the sequence of vertices visited.
	 *
	 *  @return List containing the sequence of the vertices visited in the entire graph,
	 *  regardless of the connected components of the graph.
	 *  @see #topologicalSort()
	 */
	public List<Vertex> reverseTopologicalSort( );

	/**
	 * Perform a topological sort of the connected set of a directed acyclic graph
	 * to which Vertex startat belongs, starting at Vertex startat.
	 *
	 * @param	startat	  The Vertex to which you want to start the traversal.
	 *
	 * @return  A List of vertices in the order that they were visited.
	 */
	public List<Vertex> topologicalSort(Vertex startat);

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
	public List<Vertex> reverseTopologicalSort(Vertex startat);
}
