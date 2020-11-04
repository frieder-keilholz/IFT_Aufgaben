import graph.*;
import graph.algorithm.*;

/**
 * 
 */
public class Main{

    public static void main(String[] args) {
        //create the graph
        DirectedAcyclicGraphImpl dgraph = new DirectedAcyclicGraphImpl();
        //create the vertexes
        Vertex t1 = new Vertex("T1");
        Vertex t2 = new Vertex("T2");
        Vertex t3 = new Vertex("T3");
        Vertex t4 = new Vertex("T4");
        Vertex t5 = new Vertex("T5");
        Vertex t6 = new Vertex("T6");
        Vertex t7 = new Vertex("T7");

        //Add vertesxes to Graph

        try {
            dgraph.add( t1 );
            dgraph.add( t2 );
            dgraph.add( t3 );
            dgraph.add( t4 );
            dgraph.add( t5 );
            dgraph.add( t6 );
            dgraph.add( t7 );

            //add edges
            dgraph.addEdge(t3,t2);
            dgraph.addEdge(t6,t3);
            dgraph.addEdge(t4,t2);
            dgraph.addEdge(t1,t4);
            dgraph.addEdge(t4,t5);
            dgraph.addEdge(t6,t5);
            dgraph.addEdge(t7,t5);
            dgraph.addEdge(t1,t6);
            dgraph.addEdge(t3,t7);
            System.out.println(dgraph.topologicalSort());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}