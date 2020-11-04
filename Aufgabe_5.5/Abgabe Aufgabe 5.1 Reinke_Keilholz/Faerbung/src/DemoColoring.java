import graph.*;
import graph.algorithm.*;

public class DemoColoring {

	public DemoColoring() throws Exception {
		Graph graph = new GraphImpl(false);
		Vertex  v1, v2, v3, v4, v5, v6;

		// Beispielgraph aus der Vorlesung
		v1 = new Vertex( "1" );
		v2 = new Vertex( "2" );
		v3 = new Vertex( "3" );
		v4 = new Vertex( "4" );
		v5 = new Vertex( "5" );
		v6 = new Vertex( "6" );

		graph.add( v1 );
		graph.add( v2 );
		graph.add( v3 );
		graph.add( v4 );
		graph.add( v5 );
		graph.add( v6 );
		graph.addEdge( v1, v2 );
		graph.addEdge( v1, v6 );
		graph.addEdge( v2, v5 );
		graph.addEdge( v3, v4 );
		graph.addEdge( v3, v5 );
		graph.addEdge( v4, v6 );
		graph.addEdge( v5, v6 );

		// Teste Faerbung
		System.out.println("Graph:\n" + graph);
		GraphColoring gc = new GreedyColoring( graph );
		System.out.println("Faerbung (Greedy): " + gc.coloring());
		gc = new BackTrackColoring( graph );
		System.out.println("Faerbung (Backtracking): " + gc.coloring());
	}

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("unused")
		DemoColoring test = new DemoColoring();
	}
}
