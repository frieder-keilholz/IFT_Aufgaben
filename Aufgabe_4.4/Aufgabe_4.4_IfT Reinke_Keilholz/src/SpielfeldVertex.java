import graph.Vertex;

/**
 * Für jeden Möglichen (Folge-)Zustand wird in der Tiefensuche ein Spielfeld-Vertex angelegt.
 * Die SpielfeldVertices sind nötig, um die Tiefensuche auf einem Graphen durchführen zu lassen.
 * @author Julia Reinke & Frieder Keilholz
 */
public class SpielfeldVertex extends Vertex {
    
    /**
	 * The serial version UID
	 */
    private static final long serialVersionUID = 1L;

    /**
     * Jeder SpielfeldVertex speichert ein Spielfeld
     */
    private Spielfeld spielfeld;
    /**
     * Wurden die Folgezustände des Spielfelds dieses Vertexes erstellt und den Graphen hinzugefügt, 
     * wird in der Tiefensuche dieses Flag auf true gesetzt.
     * Der SpielfeldVertex speichert dies, damit die Vertexe der Folgezustände nicht erneut erstellt werden müssen. 
     */
    public boolean childVerticesCreated = false;

    /**
     * Der Vertex kann seine Tiefe im Graph speichern.
     * Die Tiefensuche verwendet dieses Attribut, um die Suchtiefe einzuhalten. 
     */
    public int tiefe = -1;

    /**
     * Der Konstruktor erstellt einen SpielVertex mit gegebenem Spielfeld.
     * @param feld das Spielfeld, welches der SpielVertex darstellt
     */
    public SpielfeldVertex(Spielfeld feld){
        super();
        this.spielfeld = feld;
    }

    /**
     * getter des Spielfeldes
     * @return das Spielfeld-Objekt des SpielfeldVertex
     */
    public Spielfeld getSpielfeld(){
        return this.spielfeld;
    }

}
