import graph.Vertex;
import graph.Visitor;

/**
 * Der SpielfeldVisitor wird als Visitor der Tiefensuche eingesetzt.
 * Es wird beim Instanziieren das Ziel-Spielfeld festgelegt.
 * In der visit-Methode wird geprüft, ob der gegebene Spielfeld-Vertex der Zielstellung entspricht.
 * @author Julia Reinke & Frieder Keiholz
 */
public class SpielfeldVisitor implements Visitor {
    /**
	 * The serial version UID
	 */
    private static final long serialVersionUID = 1L;

    private Spielfeld zielSpielfeld;

    /**
     * Konstruktor
     * @param zielFeld die Zielstellung des Push-Puzzles
     */
    public SpielfeldVisitor(Spielfeld zielFeld){
        this.zielSpielfeld = zielFeld;
    }

    /**
     * Prüft, ob ein gegebener Vertex der gesetzten Zielstellung entspricht.
     * @param vertexToVisit der Vertex, welcher in der Tiefensuche gerade beschritten wird
     * @return true, wenn Vertex=Zielstellung; false, wenn nicht
     */
    @Override
    public boolean visit(Vertex vertexToVisit) {
        SpielfeldVertex spielfeldVertexToVisit = (SpielfeldVertex) vertexToVisit;
        /*
        System.out.println(spielfeldVertexToVisit.getSpielfeld().toString());
        System.out.println(zielSpielfeld.toString());
        System.out.println("-----------------------");
        */
        if(spielfeldVertexToVisit.getSpielfeld().compareContent(zielSpielfeld)){
            return true;
        }else{
            return false;
        }
    }
}
