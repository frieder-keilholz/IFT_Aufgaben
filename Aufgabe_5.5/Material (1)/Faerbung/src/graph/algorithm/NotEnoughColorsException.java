package graph.algorithm;

import graph.*;

/**
 * Thrown when a <tt>GraphColoring</tt> class needs more colors than
 * specified by the caller.
 */
public class NotEnoughColorsException extends GraphException {

	private static final long serialVersionUID = 1L;

	public NotEnoughColorsException() {
        super();
    }

    public NotEnoughColorsException( String msg ) {
        super( msg );
    }
}