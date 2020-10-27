package graph;

public class NoSuchVertexException extends GraphException {

	private static final long serialVersionUID = 1L;

	public NoSuchVertexException() {
        super();
    }

    public NoSuchVertexException( String msg ) {
        super( msg );
    }
}