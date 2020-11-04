package graph.algorithm;

import graph.*;
import java.util.*;

/**
 * A concrete subclass of GraphTraversal that uses depth-first search
 * in traversing a graph. Note that the traverse() method will only
 * traverse the connected set to which the Vertex the traversal will start at belongs.
 *
 * @author Ralf Vandenhouten
 * @version 2.0 2010-09-16
 */
public class DepthFirstRecursiveGraphTraversal extends GraphTraversal {

	private static final long serialVersionUID = 2L;

	private int count;

	/**
	 * Creates a DepthFirstRecursiveGraphTraversal object.
	 */
	public DepthFirstRecursiveGraphTraversal( Graph graph ) {
		super( graph );
		count = 0;
	}

	/*
	 * (non-Javadoc)
	 * @see graph.algorithm.GraphTraversal#traverse(graph.Vertex, java.util.List, graph.Visitor)
	 */
	public int traverse( Vertex startat, List<Vertex> visited, Visitor visitor ) {

		visitedMap.put( startat, count++ );
		visited.add( startat );

		// Exit if the visitor tells us so
		if( !visitor.visit( startat ))
			return TERMINATEDBYVISITOR;

		// Get all of its adjacent vertices and call recursively traverse() for
		// each, but only if it has not been visited
		for ( Vertex adjacent : graph.getOutgoingAdjacentVertices(startat) ) {
			if ( visitedMap.get(adjacent) == null )
				// Recursive call
				if ( traverse( adjacent, visited, visitor ) == TERMINATEDBYVISITOR )
					return TERMINATEDBYVISITOR;
		}

		return OK;
	}

	/*
	 * (non-Javadoc)
	 * @see graph.algorithm.GraphTraversal#traverse(graph.Vertex)
	 */
	public List<Vertex> traverse( Vertex startat ) {
		return this.traverse( startat, new NullVisitor());
	}

	/*
	 * (non-Javadoc)
	 * @see graph.algorithm.GraphTraversal#traverse(graph.Vertex, graph.Visitor)
	 */
	public List<Vertex> traverse( Vertex startat, Visitor visitor ) {
		List<Vertex> visited = new ArrayList<Vertex>(10);

		this.traverse( startat, visited, visitor );
		return visited;
	}
}