import java.util.*;
import graph.*;

/**
 * Übung IfT
 * Aufgabe 4.4
 * @author Julia Reinke & Frieder Keilholz
 * Dies ist die Lösung zu Aufgabe 4.4.
 * Es wird ein Pushpuzzle-Feld eingelesen und der kürzeste mögliche Lösungsweg per iterativer Tiefensuche gefunden.
 * Die Tiefensuche wird mit aufsteigender Suchtiefe immer wieder aufgerufen, bis der erste Lösungsweg gefunden ist.
 * Es wird dabei immer auf dem gleichen Graph gearbeitet. 
 * Die erstellten Vertices & Spielfelder aus den vorhergehenden Tiefensuchen werden nicht neu erstellt sondern erneut durchlaufen.
 * Sind mehr als 20 Schritte nötig, um zu einer Lösung zu kommen nimmt die Berechnung viel Zeit in Anspruch.
 */
public class Main {
    /**
     * Der Graph auf dem gearbeitet wird.
     */ 
    static SpielfeldGraph graph;
    
    public static void main(String[] args) {
        /**
         * Das Ausgangsfeld wird vom Nutzer eingegeben und ein Spielfeld-Objekt erstellt.
         */
        SpielfeldBuilder builder = new SpielfeldBuilder();
        Spielfeld feld = builder.buildField();
        System.out.println("Ausgangsfeld:");    
        //Spielfeld feld = new Spielfeld(3);
        // Mit feld.fillRandom() könnte ein zufälliges Spielfeld erstellt werden.
        //feld.fillRandom();
        //int[][] startStellung = {{1,3,5},{0,2,6},{4,7,8}};
        //feld.fillSpielfeld(startStellung);
        System.out.println(feld.toString());
        /**
         * Das vorgegebene Ziel-Spielfeld wird erstellt und angezeigt.
         */
        int[][] zielStellung = null;
         if(feld.size == 2){
            int[][] zielStellung2 = {{1,2},{3,0}};
            zielStellung = zielStellung2;
        }else if(feld.size == 3){
            int[][] zielStellung3 = {{1,2,3},{4,5,6},{7,8,0}};
            zielStellung = zielStellung3;
        }else if(feld.size == 4){
            int[][] zielStellung4 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
            zielStellung = zielStellung4;
        }
        Spielfeld zielFeld = new Spielfeld(feld.size);
        zielFeld.fillSpielfeld(zielStellung);
        System.out.println("Zielstellung:");
        System.out.println(zielFeld.toString());

        System.out.println("------------");

        /**
         * shortestWayFound ist false so lange keine Lösung gefunden wurde 
         */
        boolean shortestWayFound = false;
        /**
         * Die Suchtiefe wird bei jedem Aufruf der iterativen Tiefensuche mitgegeben und inkrementiert.
         */
        int suchtiefe = 1;
        /**
         * Der visitor ist ein SpielfeldVisitor, welcher die Suche abbricht, sobald der Lösungsweg gefunden wurde.
         */
        SpielfeldVisitor visitor = new SpielfeldVisitor(zielFeld);
        /**
         * Der Graph wird initialisiert und das Ausgangs-Spielfeld als Wurzel eingefügt.
         */
        graph = new SpielfeldGraph(new SpielfeldVertex(feld));
        SpielfeldVertex loesung = null;

        /**
         * Bis eine Lösung gefunden ist wird die Tiefensuche immer wieder mit erhöhter Suchtiefe aufgerufen.
         * Dies könnte effizienter gestaltet werden, wenn die Tiefensuche nicht jedesmal von der Wurzel gestartet wird,
         * sondern von den zuletzt eingefügten Vertices.
         */
        while (!shortestWayFound) {
            System.out.println("Suche mit Tiefe: " + suchtiefe);
            // starte DFT
            loesung = RealDepthFirstSearch(graph.wurzelVertex, visitor, suchtiefe++);
            if(loesung != null){
                shortestWayFound = true;
            }
        }
        System.out.println("Kürzeste Loesung gefunden!");
        
        /**
         * Wurde eine LÖsung gefunden werden vom Ziel-Vertex bis zur Wurzel die Vertices in einen Stack eingefügt
         * und in verkehrter Reihenfolge wieder ausgegeben.
         */
        Stack<SpielfeldVertex> zuegeStack = new Stack<SpielfeldVertex>();
        SpielfeldVertex temp = loesung;
        while(temp != graph.wurzelVertex){
            zuegeStack.push(temp);
            temp = (SpielfeldVertex) graph.getIncomingAdjacentVertices(temp).get(0);
        }
        
        System.out.println("Ausgangsfeld:");
        System.out.println(feld);
        System.out.println("------------");
        System.out.println("Folgende Schritte:");
        int schrittCounter = 1;
        /**
         * Jeder Schritt wird vom Stack geladen und nummeriert ausgegeben.
         */
        while(!zuegeStack.isEmpty()){
            System.out.println("Schritt "+schrittCounter++ +":");
            System.out.println(zuegeStack.pop().getSpielfeld().toString());
        }

    }

    /**
     * Iterative Tiefensuche mit maximaler Suchtiefe.
     * Basierend auf der Tiefensuche der Klasse RealDepthGraphTraversal.
     * Der Graph wird während der Tiefensuche um weitere SpielfeldVertices erweitert.
     * Wurden die Nachfolger von Vertices des Graphen bereits von einer vorhergehenden Ausführung erstellt, werden diese wiederverwendet. 
     * @param startat Der Start-SpielfeldVertex der Tiefensuche
     * @param visitor Das Visitor-Objekt der Tiefensuche. 
     * @param maximaleTiefe Die Maximale Suchtiefe der Tiefensuche
     * @return Den ersten SpielfeldVertex, mit dem gleichen Spielfeld wie die Zielstellung des Visitors
     */
    public static SpielfeldVertex RealDepthFirstSearch(SpielfeldVertex startat, SpielfeldVisitor visitor, int maximaleTiefe){
        List<SpielfeldVertex> visited = new ArrayList<SpielfeldVertex>();
        Stack<SpielfeldVertex> stack = new Stack<SpielfeldVertex>();
        int count = 0;
        HashMap<SpielfeldVertex, Integer> visitedMap = new HashMap<SpielfeldVertex, Integer>();
        
        stack.push( startat );

		do {
			// Get the next vertex in the queue and add it to the visited
			SpielfeldVertex next = stack.pop();
			// Check if vertex has already been processed
			if (visitedMap.get(next)!=null && !visitedMap.get(next).equals(-1)){
                continue;
            }
			visited.add( next );
			visitedMap.put( next, count++ );

            /**
             *  Terminiert, wenn das Spielfeld des SpielfeldVertices dem gesuchten Spielfeld des SpielfeldVisitors entspricht
             */
			if (visitor.visit( next )){
                return next;
            }

            /**
             * Wenn die Tiefe des Vertex noch nicht berechnet wurde (-1), wird sie nun berechnet und im Vertex gespeichert
             */
            if(next.tiefe == -1){
                // Tiefe des Vertex zum WurzelVertex berechnen
                int aktuelleTiefe = 0;
                SpielfeldVertex temp = next;
                while(temp != graph.wurzelVertex){
                    aktuelleTiefe++;
                    temp = (SpielfeldVertex) graph.getIncomingAdjacentVertices(temp).get(0);
                }
                // Die berechnete Tiefe des Vertex im Vertex speichert. Bei der nächsten Verwendung muss diese dann nicht neu berechnet werden.  
                next.tiefe = aktuelleTiefe;
            }

            /**
             * Wenn die maximale Suchtiefe noch nicht erreicht ist & die ChildVertices noch nicht berechnet wurden, werden die möglichen Spielfelder
             * berechnet und entsprechende SpielfeldVertices erstellt.
             * Die neuen SpielfeldVertices werden dem Graphen hinzugefügt und Kanten zum Vorgänger gezogen.
             */
            if(!next.childVerticesCreated && next.tiefe < maximaleTiefe){
                /**
                 * Es werden alle Vorgänger des Vertex next zur Wurzel in einer ArrayList gespeichert.
                 */
                SpielfeldVertex ttemp = next;
                ArrayList<SpielfeldVertex> vorgaenger = new ArrayList<SpielfeldVertex>();
                while(ttemp != graph.wurzelVertex){
                    vorgaenger.add(ttemp);
                    ttemp = (SpielfeldVertex)graph.getIncomingAdjacentVertices(ttemp).get(0);
                }
                // mögliche Spielfelder berechnen & je Spielfeld Vertex mit Spielfeld erstellen
                for(Spielfeld mglSpielfeld : next.getSpielfeld().possibleMoves()){
                    /**
                     * Für jedes mögliche Spielfeld wird geprüft, ob dieses Spielfeld bereits von einem next vorhergenden SpielfeldVertex abgebildet wurde.
                     */
                    boolean zugBereitsGezogen = false;
                    for(SpielfeldVertex sv: vorgaenger){
                        if(sv.getSpielfeld().compareContent(mglSpielfeld)){
                            zugBereitsGezogen = true;
                        }
                    }
                    /**
                     * Wenn der mögliche Zug noch nicht von einem Vorgänger von next gezogen wurde, wird für diesen ein SpielfeldVertex angelegt und Graph angefügt.
                     */
                    if(!zugBereitsGezogen){
                        SpielfeldVertex mglSpielfeldVertex = new SpielfeldVertex(mglSpielfeld);
                        try {
                            // Vertices in den Graphen einfügen
                            graph.add(mglSpielfeldVertex);
                            // Kante erstellen
                            graph.addEdge(next, mglSpielfeldVertex);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                // Vertices wurden erstellt -> setze flag auf true
                // damit müssen bei nächster DFT die Vertices nicht erneut erstellt werden
                next.childVerticesCreated = true;
            }

			// Get all of its adjacent vertices and push them onto the stack
			// only if it has not been visited
			for ( Vertex adjacent : graph.getOutgoingAdjacentVertices(next) ) {
				if ( visitedMap.get(adjacent) == null || visitedMap.get(adjacent).equals(-1)) {
					visitedMap.put( (SpielfeldVertex) adjacent, -1 ); // Mark as visited
					stack.push( (SpielfeldVertex) adjacent );
				}
            }
        }while ( !stack.isEmpty() );
        /**
          * Als doe maximale Suchtiefe erreicht wurde, sind keine neuen Folge-Vertices erzeugt worden.
          * Dann würde bei einer Suche ohne Ergebnis der Stack irgendwann leer sein und die Tiefensuche null zurückgeben.  
          */
        return null;
    }
			
}
