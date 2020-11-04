import graph.*;
import graph.algorithm.*;

/**
 * Übung IfT
 * Aufgabe 5.1
 * @author Frieder Keilholz
 * Compile and Execute this class to determine the order of the project-
 * parts introduced in this assignment.
 * To determine the order a directed acyclic graph is created and the
 * project-parts are inserted as vertices.
 * Edges are created to respresent the project-parts relations.
 * If a project is not executeable, because the relation of the parts forms a cycle,
 * the DirectedAcyclicGraphImpl object will notice and throw an exception.
 */
public class Main{

    public static void main(String[] args) {
        /**
         * Create an directed acyclic graph
         */
        DirectedAcyclicGraphImpl dgraph = new DirectedAcyclicGraphImpl();
        /**
         * Create the cvertices, representing the project-parts
         */
        Vertex t1 = new Vertex("T1");
        Vertex t2 = new Vertex("T2");
        Vertex t3 = new Vertex("T3");
        Vertex t4 = new Vertex("T4");
        Vertex t5 = new Vertex("T5");
        Vertex t6 = new Vertex("T6");
        Vertex t7 = new Vertex("T7");

        
        try {
            /**
             * Add vertices to graph.
             */
            dgraph.add( t1 );
            dgraph.add( t2 );
            dgraph.add( t3 );
            dgraph.add( t4 );
            dgraph.add( t5 );
            dgraph.add( t6 );
            dgraph.add( t7 );

            /**
             * Add edges, representing the relations of the project-parts.
             */
            dgraph.addEdge(t3,t2);
            dgraph.addEdge(t6,t3);
            dgraph.addEdge(t4,t2);
            dgraph.addEdge(t1,t4);
            dgraph.addEdge(t4,t5);
            dgraph.addEdge(t6,t5);
            dgraph.addEdge(t7,t5);
            dgraph.addEdge(t1,t6);
            dgraph.addEdge(t3,t7);
            
            /**
             * If until here no exception occured, the graph is acyclic and the project executeable.
             */
            System.out.println("The project is executable with the following order:");
            /**
             * Using the topologicalSort function, to determine the order.
             */
            System.out.println(dgraph.topologicalSort());
        }catch (CycleException e){
            /**
             * A CycleException occured. The project is not executeable.
             */
            System.out.println("The project is not executeable.");
            System.out.println("The relation of project-parts forms a cycle.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}