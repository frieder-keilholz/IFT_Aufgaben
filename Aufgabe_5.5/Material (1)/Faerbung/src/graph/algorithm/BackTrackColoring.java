package graph.algorithm;

import graph.*;

import java.util.*;

/**
 * Class for coloring a graph using a back tracking algorithm.
 * Extends the abstract GraphColoring class.
 *
 * @author Ralf Vandenhouten
 */
public class BackTrackColoring extends GraphColoring {

	private static final long serialVersionUID = 1L;
	
	/**
	 * A list of the vertices of the graph.
	 */
	protected List<Vertex> vertices;

	/**
	 * Constructor for BackTrackColoring objects
	 */
	public BackTrackColoring( Graph graph ) {
		super( graph );
		vertices = graph.cloneVertices();
	}

	/**
	 * Method that performs a minimum coloring by a binary search that calls
	 * coloring(maxColor) iteratively, where the binary search finds the optimum
	 * value for maxColor.
	 *
	 * @return The HashMap containing the color mapping of the vertices.
	 */
	public Map<Vertex,Integer> coloring() {
		Map<Vertex,Integer> result;
		int lower=1, upper=vertices.size(), middle;

		while ( lower < upper ) {
			middle = (lower+upper)/2;
			result = null;
			try {
				colorMap.clear();
				result = coloring( middle );
			} catch (NotEnoughColorsException e) {}
			if ( result != null )
				if ( lower == middle )
					return result;
				else
					upper = middle;
			else
				if ( lower == middle )
					try {
						colorMap.clear();
						return coloring( lower+1 );
					} catch (NotEnoughColorsException e) { return null; }
					else
						lower = middle;
		}
		return null;
	}

	/**
	 * Coloring method using a back tracking algorithm.
	 *
	 * @param maxNumOfColors The maximum number of colors to be used for coloring.
	 *          If that is not enough, a NotEnoughColorsException is thrown.
	 *
	 * @return The HashMap containing the color mapping of the vertices.
	 */
	public Map<Vertex,Integer> coloring( int maxNumOfColors )
	throws NotEnoughColorsException 
	{
		if ( !tryColor( 0, maxNumOfColors ) )
			throw new NotEnoughColorsException("Backtracking needs more than "+
					maxNumOfColors +" colors.");
		else
			return colorMap;
	}

	/**
	 * Recursive helper method for coloring() that actually performs the
	 * back tracking.
	 */
	protected boolean tryColor(int i, int maxColor) {
		int color = 0;
		Integer theColor;
		Vertex v;
		boolean quit = false;

		do {
			theColor = new Integer(++color);
			if ( possible(i, theColor) ) {
				v = vertices.get(i);
				colorMap.put( v, theColor );
				if ( i+1 < vertices.size() ) {
					quit = tryColor( i+1, maxColor ); // recursive call
					if ( !quit ) {
						colorMap.remove( v );
					}
				} else return true;
			}
		} while ( !(quit || color==maxColor) );
		return quit;
	}

	/**
	 * Helper method for tryColor() that checks if one of the neighbors has
	 * already the current color.
	 */
	private boolean possible(int i, int color) {
		Vertex v = (Vertex)vertices.get(i);
		for (Vertex nb : graph.getAdjacentVertices(v)) {
			Integer col = colorMap.get(nb);
			if ( col != null )
				if ( col.equals(color) ) // neighbor has the same color -> bad!
					return false;
		}
		return true;
	}
}