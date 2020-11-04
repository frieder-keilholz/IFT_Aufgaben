package graph;

/**
 * Thrown whenever a <tt>Tree</tt> becomes malformed as a result
 * of calling a method that is declared on its superinterface
 * but is not supported.
 */
public class IllegalTreeException extends GraphException {

	private static final long serialVersionUID = 1L;

	public IllegalTreeException() {
        super();
    }

    public IllegalTreeException( String msg ) {
        super( msg );
    }
}