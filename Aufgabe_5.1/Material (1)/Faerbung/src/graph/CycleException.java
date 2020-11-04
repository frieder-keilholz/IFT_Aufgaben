package graph;

/**
 * Thrown when a cycle has occured when it is not desired.
 * This is typically thrown by <tt>DirectedAcyclicGraph</tt>s
 * and <tt>Tree</tt>s.
 */
public class CycleException extends GraphException {

	private static final long serialVersionUID = 1L;

	public CycleException() {
        super();
    }

    public CycleException( String msg ) {
        super( msg );
    }

}