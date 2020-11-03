package graph.algorithm;

import graph.*;
import java.util.*;

/**
 * A concrete subclass of GraphTraversal that uses depth-first search
 * in traversing a graph. Note that the traverse() method will only
 * traverse the connected set to which the Vertex the traversal will start at belongs.
 */
public class RealDepthFirstGraphTraversal extends GraphTraversal {

	private static final long serialVersionUID = 2L;

	private Stack<Vertex> stack;
	private int count;

	/**
	 * Creates a DepthFirstGraphTraversal object.
	 */
	public RealDepthFirstGraphTraversal( Graph graph ) {
		super( graph );
		this.stack = new Stack<Vertex>();
		count = 0;
	}

	/*
	 * (non-Javadoc)
	 * @see graph.algorithm.GraphTraversal#traverse(graph.Vertex, java.util.List, graph.Visitor)
	 */
	public int traverse( Vertex startat, List<Vertex> visited, Visitor visitor ) {
		// Push the starting vertex onto the stack
		this.stack.push( startat );

		do {
			// Get the next vertex in the queue and add it to the visited
			Vertex next = stack.pop();
			// Check if vertex has already been processed
			if (visitedMap.get(next)!=null && !visitedMap.get(next).equals(-1))
				continue;
			visited.add( next );
			visitedMap.put( next, count++ );

			// Exit if the visitor tells us so
			if (!visitor.visit( next ))
				return TERMINATEDBYVISITOR;

			// Get all of its adjacent vertices and push them onto the stack
			// only if it has not been visited
			for ( Vertex adjacent : graph.getOutgoingAdjacentVertices(next) ) {
				if ( visitedMap.get(adjacent) == null || visitedMap.get(adjacent).equals(-1)) {
					visitedMap.put( adjacent, -1 ); // Mark as visited
					this.stack.push( adjacent );
				}
			}
			
		} while ( !this.stack.isEmpty() );
		
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
		List<Vertex>  visited = new ArrayList<Vertex>( 10 );

		this.traverse( startat, visited, visitor );
		return visited;
	}
}