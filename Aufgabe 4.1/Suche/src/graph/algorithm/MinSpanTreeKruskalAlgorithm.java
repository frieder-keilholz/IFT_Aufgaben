package graph.algorithm;

import graph.*;
import java.util.*;

/**
 * Kruskal's algorithm for determining a minimum spanning tree of a graph.
 *
 * @author Ralf Vandenhouten
 * @version 2.0 2010-09-16
 */
public class MinSpanTreeKruskalAlgorithm {

	/**
	 * The weighted graph.
	 */
	protected WeightedGraph wgraph;

	/**
	 * Creates an instance of MinimumSpanningTreeKruskalAlgorithm
	 *
	 * @param wgraph  The WeightedGraph where the minimum spanning tree will be determined.
	 */
	public MinSpanTreeKruskalAlgorithm( WeightedGraph wgraph ) {
		this.wgraph = wgraph;
	}

	/**
	 * Determine the minimum spanning tree of a weighted graph using Kruskal's method.
	 */
	public WeightedGraph minimumSpanningTree() {
		Edge      edge;
		int       numVertices = wgraph.getVerticesCount();
		int       treeSize = 0;
		WeightedGraph  spanningtree = new WeightedGraphImpl(false);
		HashSet<Edge>   allEdges = new HashSet<Edge>();
		// HashMap fuer Zahl der verbundenen Ecken zu jeder Ecke
		HashMap<Vertex,Integer> numConnected = new HashMap<Vertex,Integer>();
		// Verkettete Liste zusammenh�ngender Ecken wird ebenfalls als HashMap
		// dargestellt, da einfacher zu handhaben. Jede Ecke wird durch die HashMap
		// auf ihren "Nachfolger" abgebildet (zyklisch).
		HashMap<Vertex,Vertex> nextInConnectedSet = new HashMap<Vertex,Vertex>();
		// Zusammenhangskomponente jeder Ecke wird auch als HashMap dargestellt.
		// Die Z.k. wird durch ihre "erste" Ecke identifiziert.
		HashMap<Vertex,Vertex> connectedSet = new HashMap<Vertex,Vertex>();

		// Initialisiere die drei HashMaps
		for (Vertex v1 : wgraph.getVertices()) {
			numConnected.put(v1, new Integer(1));
			nextInConnectedSet.put(v1, v1);
			connectedSet.put(v1, v1);
		}

		// Sammle alle Kanten des Graphs. Die Klasse HashSet sorgt daf�r, dass
		// keine Kante doppelt aufgenommen wird.
		allEdges.addAll( wgraph.getAllEdges() );

		// Copy the HashSet to a List so we can sort the edges using
		// the sort() method of the Collections class.
		List<Edge> listEdges = new ArrayList<Edge>( allEdges );
		Collections.sort( listEdges,
				new Comparator<Edge>() {
			public int compare( Edge obj1, Edge obj2 ) {
				WeightedEdge edge1 = (WeightedEdge) obj1;
				WeightedEdge edge2 = (WeightedEdge) obj2;

				if( edge1.getWeight() < edge2.getWeight() )
					return -1;
				else if( edge1.getWeight() > edge2.getWeight() )
					return 1;
				else
					return 0;
			}
			public boolean equals( Object obj ) {
				return obj.equals( this );
			}
		});

		// For each edge ...
		for (Iterator<Edge> iterator = listEdges.iterator(); iterator.hasNext() && treeSize < numVertices-1; ) {
			edge = (WeightedEdge) iterator.next();

			// Test the feasibility of adding an edge into the spanning tree
			Vertex v1 = edge.getVertexA();
			Vertex v2 = edge.getVertexB();

			// Falls v1 und v2 noch nicht zusammenhaengen, baue die Ecke in den
			// spanning tree ein und aktualisiere die HashMaps
			if ( connectedSet.get(v1) != connectedSet.get(v2) ) {
				try {
					spanningtree.addEdge( edge );
					treeSize++;
				} catch (Exception e) { e.printStackTrace(); }

				// Finde Vertex mit kleinerer/groesserer Zusammenhangskomponente
				@SuppressWarnings("unused")
				Vertex min, max, v;
				int i1, i2;
				i1 = numConnected.get(connectedSet.get(v1));
				i2 = numConnected.get(connectedSet.get(v2));
				if ( i1 < i2 ) {
					min = v1;
					max = v2;
				} else {
					min = v2;
					max = v1;
				}

				// Berechne Groesse der neuen Zusammenhangskomponente
				numConnected.put(connectedSet.get(max), i1 + i2);

		        // Ordne alle Ecken der kleineren Zus.komp. der groesseren zu (durch
		        // zyklisches Durchlaufen der "Zusammenhangs-HashMap")
				Vertex i = min;
				do {
					connectedSet.put(i, connectedSet.get(max));
					i = nextInConnectedSet.get(i);
				} while (i != min);				
				/*
		            Hier fehlen ein paar Zeilen ...
				*/
		        
		        // Vertausche die Nachfolger von v1 und v2, um die beiden zyklischen
				// Zusammenhangslisten zusammenzufuehren
				Vertex h = nextInConnectedSet.get(v1);
				nextInConnectedSet.put(v1, nextInConnectedSet.get(v2));
				nextInConnectedSet.put(v2, h);
		        /*
		            ... und hier fehlen auch ein paar.
		        */
			}
		}

		return spanningtree;
	}
}