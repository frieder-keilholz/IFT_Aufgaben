package graph;

/**
 * A visitor that always return true when visiting.
 */
public class NullVisitor implements Visitor {

	private static final long serialVersionUID = 1L;

	public boolean visit( Vertex vertexToVisit ){
        return true;
    }
}

