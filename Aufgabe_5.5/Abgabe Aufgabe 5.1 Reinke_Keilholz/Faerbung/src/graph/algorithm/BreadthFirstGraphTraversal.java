package graph.algorithm;

import java.util.*;
import graph.*;

/**
 * A concrete subclass of GraphTraversal that uses breadth-first search
 * in traversing a graph. Note that the traverse() method will only
 * traverse the connected set to which the Vertex the traversal will start at belongs.
 *
 * @author Ralf Vandenhouten
 * @version 2.0 2010-09-16
 */
public class BreadthFirstGraphTraversal extends GraphTraversal {

	private static final long serialVersionUID = 1L;

	private Queue<Vertex> queue;
	private int count;

	/**
	 * Creates a BreadthFirstGraphTraversal object.
	 */
	public BreadthFirstGraphTraversal( Graph graph ) {
		super( graph );
		this.queue = new ArrayDeque<Vertex>();
		count = 0;
	}

	/*
	 * (non-Javadoc)
	 * @see graph.algorithm.GraphTraversal#traverse(graph.Vertex, java.util.List, graph.Visitor)
	 */
	public int traverse( Vertex startat, List<Vertex> visited, Visitor visitor ) {
		// Push the starting vertex onto the stack
		queue.add( startat );
		visitedMap.put( startat, new Integer(count++) );

		do {
			// Get the next vertex in the queue and add it to the visited
			try {
				Vertex next = (Vertex) queue.remove();
				visited.add( next );

				// Exit if the visitor tells us so
				if( !visitor.visit( next ))
					return TERMINATEDBYVISITOR;

				// Get all of its adjacent vertices and push them onto the stack
				// only if it has not been visited
				for ( Vertex adjacent : graph.getOutgoingAdjacentVertices(next) ) {
					if ( visitedMap.get(adjacent) == null ) {
						visitedMap.put( adjacent, new Integer(count++) );
						this.queue.add( adjacent );
					}
				}
			} catch (NoSuchElementException e) { e.printStackTrace(); }

		} while ( !this.queue.isEmpty() );
		
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
		List<Vertex> visited = new ArrayList<Vertex>( 10 );

		this.traverse( startat, visited, visitor );
		return visited;
	}
}
