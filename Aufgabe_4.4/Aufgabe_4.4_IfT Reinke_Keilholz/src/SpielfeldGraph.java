import graph.GraphImpl;

/**
 * Graph-Klasse, speichert die möglichen Lösungswege.
 * Erweitert die GraphImpl-Klasse um ein Attribut 'wurzelVertex'.
 * Rückblickend hätte sich hier ein Baum als Datenstruktur eher angeboten.
 * @author Julia Reinke & Frieder Keilholz
 */
public class SpielfeldGraph extends GraphImpl{
    /**
	 * The serial version UID
	 */
    private static final long serialVersionUID = 1L;

    public SpielfeldVertex wurzelVertex; // Ausgangs-Spielfeld
    
    public SpielfeldGraph(SpielfeldVertex wurzelVertex){
        super(true);
        this.wurzelVertex = wurzelVertex;
    }
} 
