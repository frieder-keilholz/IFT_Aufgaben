package graph;

/**
 * Exception superclass thrown from methods of graphs.
 */
public class GraphException extends Exception {

	private static final long serialVersionUID = 1L;

	public GraphException() {
        super();
    }

    public GraphException( String msg ) {
        super( msg );
    }
}