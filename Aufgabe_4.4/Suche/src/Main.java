import java.util.*;
import graph.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Test");    
        Spielfeld feld = new Spielfeld(3);
        feld.fillRandom();
        System.out.println(feld.toString());
        Spielfeld zielFeld = new Spielfeld(3);
        int[][] zielStellung = {{1,2,3},{4,5,6},{7,8,0}};
        zielFeld.fillSpielfeld(zielStellung);
        System.out.println(zielFeld.toString());

        // Plan: Während des RDFGT die nächsten Spielfelder erstellen & die Suchtiefe iterativ erhöhen

        boolean shortestWayFound = false;
        int suchtiefe = 1;
        SpielfeldVisitor visitor = new SpielfeldVisitor(zielFeld);

        while (!shortestWayFound) {            
            // starte DFS

        } 

    }
}

public static void RealDepthFirstSearch(SpielfeldVertex startat, List<SpielfeldVertex> visited, SpielfeldVisitor visitor){
    Stack<SpielfeldVertex> stack;
    int count;
    HashMap<Vertex, Integer> visitedMap;
    
    stack.push( startat );

		do {
			// Get the next vertex in the queue and add it to the visited
			Vertex next = stack.pop();
			// Check if vertex has already been processed
			if (visitedMap.get(next)!=null && !visitedMap.get(next).equals(-1))
				continue;
			visited.add( next );
			visitedMap.put( next, count++ );

			// Exit if the visitor tells us so
			if (!visitor.visit( next ))
				return TERMINATEDBYVISITOR;

			// Get all of its adjacent vertices and push them onto the stack
			// only if it has not been visited
			for ( Vertex adjacent : graph.getOutgoingAdjacentVertices(next) ) {
				if ( visitedMap.get(adjacent) == null || visitedMap.get(adjacent).equals(-1)) {
					visitedMap.put( adjacent, -1 ); // Mark as visited
					this.stack.push( adjacent );
				}
            }
        }while ( !this.stack.isEmpty() );
    }
			
}
