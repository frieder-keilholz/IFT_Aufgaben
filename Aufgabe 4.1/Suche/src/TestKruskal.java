import graph.Vertex;
import graph.WeightedGraph;
import graph.WeightedGraphImpl;
import graph.algorithm.MinSpanTreeKruskalAlgorithm;

class TestKruskal{
    public static void main(String[] args){
        System.out.println("Creating a weighted graph...");
        WeightedGraph testWeightedGraph = new WeightedGraphImpl(false);
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");

        try {
            testWeightedGraph.addEdge(v1, v2, 2);    
            testWeightedGraph.addEdge(v1, v6, 1);
            testWeightedGraph.addEdge(v2, v6, 1);
            testWeightedGraph.addEdge(v2, v3, 7);
            testWeightedGraph.addEdge(v3, v4, 6);
            testWeightedGraph.addEdge(v3, v5, 2);
            testWeightedGraph.addEdge(v3, v6, 2);
            testWeightedGraph.addEdge(v4, v5, 4);
            testWeightedGraph.addEdge(v4, v6, 5);
            testWeightedGraph.addEdge(v5, v6, 2);

            System.out.println();
            System.out.println("Weighted graph before applying Kruskal algorithm:");
            System.out.println(testWeightedGraph.toString());

            MinSpanTreeKruskalAlgorithm kruskalAlgorithm = new MinSpanTreeKruskalAlgorithm(testWeightedGraph);
            WeightedGraph minSpamGraph = kruskalAlgorithm.minimumSpanningTree();

            System.out.println();
            System.out.println("Minimum spanning graph resulting from Kruskal algorithm:");
            System.out.println(minSpamGraph.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}