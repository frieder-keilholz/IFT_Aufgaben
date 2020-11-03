import graph.Vertex;
import graph.WeightedGraph;
import graph.WeightedGraphImpl;
import graph.algorithm.MinSpanTreeKruskalAlgorithm;
/**
 * Übung IfT
 * Aufgabe 4.1
 * @author Frieder Keilholz
 */
class TestKruskal{
    public static void main(String[] args){
        System.out.println("Creating a weighted graph...");
        /**
         * Erstellen eines gewichteten, ungerichteten Graphens
         */
        WeightedGraph testWeightedGraph = new WeightedGraphImpl(false);
        /**
         * Erstellen des 6 Ecken, des Beispiels aus der Veranstaltung
         */
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");

        try {
            /**
             * Erstellen der Kanten, des Beispiels aus der Veranstaltung
             */
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

            /**
             * Erzeugen eines MinSpanTreeKruskalAlgorithm-Objektes und Berechnung des Minimal Aufspannenden Baumes 
             */
            MinSpanTreeKruskalAlgorithm kruskalAlgorithm = new MinSpanTreeKruskalAlgorithm(testWeightedGraph);
            WeightedGraph minSpamGraph = kruskalAlgorithm.minimumSpanningTree();

            /**
             * Ausgabe des Minimal Aufspannenden Baumes
             */
            System.out.println();
            System.out.println("Minimum spanning graph resulting from Kruskal algorithm:");
            System.out.println(minSpamGraph.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}