package graph.algorithm;

import graph.*;
import java.util.*;

/**
 * Class for coloring a graph using the greedy algorithm.
 * Extends the abstract GraphColoring class.
 *
 * @author Ralf Vandenhouten
 * @version 2.0 2010-09-16
 */
public class GreedyColoring extends GraphColoring {

	private static final long serialVersionUID = 2L;

	/**
     * Constructor for GreedyColoring objects
     */
    public GreedyColoring( Graph graph ) {
        super( graph );
    }

    /**
     * Coloring method using the simple greedy algorithm.
     *
     * @param maxNumOfColors The maximum number of colors to be used for coloring.
     *          If that is not enough, a NotEnoughColorsException is thrown.
     *
     * @return The HashMap containing the color mapping of the vertices.
     */
    public Map<Vertex,Integer> coloring( int maxNumOfColors )
    throws NotEnoughColorsException {
        int count=0, color=0, n = graph.getVerticesCount();

        while ( count < n ) {
            color++;
            if ( color > maxNumOfColors )
                throw new NotEnoughColorsException(
                    "Greedy needs more than "+ maxNumOfColors +" colors.");
            int currentColor = color;
            int blockedColor = -color;
            for ( Vertex v : graph.getVertices() ) {
                Integer mapped = (Integer)colorMap.get( v );
                if ( mapped != null && mapped != blockedColor ) {
                    if ( mapped < 0 ) // Vertex still without color
                        mapped = null;
                }
                if ( mapped == null ) { // Vertex can be colored with color
                    colorMap.put( v, currentColor ); // paint v with curr. color
                    count++;
                    for ( Vertex w : graph.getAdjacentVertices(v) ) { 
                        mapped = colorMap.get(w);
                        if ( mapped != null )
                            if ( mapped > 0 ) // w already colored
                                continue;
                        colorMap.put( w, blockedColor ); // block w for color
                    }
                }
            }
        }
        return this.colorMap;
    }
}