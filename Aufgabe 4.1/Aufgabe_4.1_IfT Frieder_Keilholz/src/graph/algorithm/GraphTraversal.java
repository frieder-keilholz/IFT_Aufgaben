package graph.algorithm;

import graph.*;
import java.util.*;
import java.io.*;

/**
 * Abstract class for an algorithm implementing graph traversal.
 * Classes implementing the Graph interface uses the Strategy
 * pattern to allow different implementations of the graph traversal
 * algorithm to be used.
 *
 * Concrete implementations of this class must never modify the Graph itself.
 */

public abstract class GraphTraversal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	static final public int   TERMINATEDBYVISITOR = -1;
	static final public int   OK = 1;

	/**
	 * The Graph on which graph traversal will be performed.
	 */
	Graph   graph;

	/**
	 * The HashMap for mapping the traversed vertices to Integers indicating
	 * their position during traversal.
	 */
	HashMap<Vertex, Integer> visitedMap;

	public GraphTraversal( Graph graph ) {
		this.graph = graph;
		this.visitedMap = new HashMap<Vertex, Integer>();
	}

	/**
	 * Abstract traversal method to be implemented by subclasses.
	 *
	 * @param startat The vertex from which traversal will start.
	 * @param visitor Visitor object controlling if and when traversal will stop,
	 *                apart from having visited all the vertices.
	 * @param visited A List of vertices that has been visited in sequence by the traversal
	 */
	public abstract int traverse(Vertex startat, List<Vertex> visited, Visitor visitor);

	/**
	 * Abstract traversal method to be implemented by subclasses.
	 *
	 * @param startat The vertex from which traversal will start.
	 * @return  A VList of vertices that has been visited in sequence by the traversal
	 */
	public abstract List<Vertex> traverse( Vertex startat );

	/**
	 * Abstract traversal method to be implemented by subclasses.
	 *
	 * @param startat The vertex from which traversal will start.
	 * @param visitor Visitor object controlling if and when traversal will stop,
	 *                apart from having visited all the vertices.
	 * @return  A List of vertices that has been visited in sequence by the traversal
	 */
	public abstract List<Vertex> traverse( Vertex startat, Visitor visitor );

	/**
	 * Returns a HashMap that maps each traversed vertex to an Integer object
	 * that indicates its position during the traversal.
	 *
	 * @return The HashMap that maps each traversed vertex to its position.
	 */
	public Map<Vertex,Integer> getVisitedMap() {
		return visitedMap;
	}
}