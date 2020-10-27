package graph.algorithm;

import java.util.*;
import graph.*;

/**
 * An implementation of the transitive closure algorithm
 * of S.A. Warshall and B. Roy
 *
 * @author  Ralf Vandenhouten
 */

public class WarshallRoy {

	/**
	 * Perform the Warshall/Roy algorithm for determining the transitive closure
	 * of a graph
	 *
	 * @param	graph   The Graph that the algorithm will be applied to.
	 *
	 * @result    The resulting Graph of the algorithm (identical to input
	 *            parameter graph).
	 */
	public static Graph transClosure(Graph graph) {
		// Three iterators are needed instead of integer loop variables
		// in order to meet the specifications of the Graph interface
		Iterator<Vertex> i, j, l;
		Vertex vi, vj, vl;

		// The algorithm itself is quite the same as the original
		for (l = graph.getVerticesIterator(); l.hasNext(); ) {
			vl = l.next();
			for (i = graph.getVerticesIterator(); i.hasNext(); ) {
				vi = i.next();
				if ( graph.haveCommonEdge( vi, vl ) ) {
					for (j = graph.getVerticesIterator(); j.hasNext(); ) {
						vj = j.next();
						if ( graph.haveCommonEdge(vl, vj) && !graph.haveCommonEdge(vi, vj)) {
							try {
								graph.addEdge( vi, vj );
							} catch (Exception e) {}
						}
					}
				}
			}
		}
		return graph;
	}
}