package graph.algorithm;

import graph.*;
import java.util.*;
import java.io.*;

/**
 * Abstract class for an algorithm implementing the coloring of a graph.
 * Concrete implementations of this class must never modify the Graph itself.
 *
 * @author Ralf Vandenhouten
 * @version 2010-09-16
 */
public abstract class GraphColoring implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The Graph on which graph coloring will be performed.
	 */
	protected Graph   graph;

	/**
	 * The HashMap for mapping the vertices of the graph to their colors
	 */
	protected HashMap<Vertex,Integer> colorMap;

	public GraphColoring( Graph graph ) {
		this.graph = graph;
		this.colorMap = new HashMap<Vertex,Integer>();
	}

	/**
	 * Abstract coloring method to be implemented by subclasses.
	 *
	 * @param maxNumOfColors The maximum number of colors to be used for coloring.
	 *          If that is not enough, a NotEnoughColorsException is thrown.
	 *
	 * @return The HashMap containing the color mapping of the vertices.
	 */
	public abstract Map<Vertex,Integer> coloring( int maxNumOfColors )
	throws NotEnoughColorsException;

	/**
	 * Coloring method to be implemented by subclasses. The default behaviour
	 * is that it simply calls this.coloring(n) where n is the number of
	 * vertices of the graph.
	 *
	 * @return The HashMap containing the color mapping of the vertices.
	 */
	public Map<Vertex,Integer> coloring() {
		try {
			return coloring( graph.getVerticesCount() );
		} catch (NotEnoughColorsException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns a HashMap that maps each vertex to its color.
	 *
	 * @return The HashMap that maps each vertex to each color.
	 */
	public Map<Vertex,Integer> getColorMap() {
		return colorMap;
	}
}