import graph.*;
import graph.algorithm.*;

public class TestGraphTraversal {

	public TestGraphTraversal() throws Exception {
		Graph graph = new GraphImpl(true);
		Vertex  v1, v2, v3, v4, v5, v6, v7, v8;

		// Beispielgraph aus der Vorlesung
		v1 = new Vertex( "1" );
		v2 = new Vertex( "2" );
		v3 = new Vertex( "3" );
		v4 = new Vertex( "4" );
		v5 = new Vertex( "5" );
		v6 = new Vertex( "6" );
		v7 = new Vertex( "7" );
		v8 = new Vertex( "8" );

		graph.addEdge( v1, v2 );
		graph.addEdge( v1, v5 );
		graph.addEdge( v1, v7 );
		graph.addEdge( v2, v5 );
		graph.addEdge( v3, v2 );
		graph.addEdge( v3, v4 );
		graph.addEdge( v4, v2 );
		graph.addEdge( v5, v4 );
		graph.addEdge( v5, v6 );
		graph.addEdge( v6, v4 );
		graph.addEdge( v7, v8 );
		graph.addEdge( v8, v5 );
		graph.addEdge( v8, v6 );

		// Teste Suchalgorithmen
		System.out.println("Graph:\n" + graph);
		GraphTraversal traversal = new DepthFirstGraphTraversal( graph );
		java.util.List<Vertex> foundVertices = traversal.traverse( v1 );
		System.out.println("Tiefensuche mit Stack:\n" + foundVertices);
		System.out.println("Tiefensuchenummern: " + traversal.getVisitedMap());
		traversal = new BreadthFirstGraphTraversal( graph );
		foundVertices = traversal.traverse( v1 );
		System.out.println("Breitensuche mit Queue:\n" + foundVertices);
		System.out.println("Breitensuchenummern: " + traversal.getVisitedMap());
	}

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("unused")
		TestGraphTraversal test = new TestGraphTraversal();
	}
}
