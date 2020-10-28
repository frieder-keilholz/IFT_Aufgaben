import graph.Vertex;

public class SpielfeldVertex extends Vertex {
    private Spielfeld spielfeld;

    public SpielfeldVertex(Spielfeld feld){
        super();
        this.spielfeld = feld;
    }

    public Spielfeld getSpielfeld(){
        return this.spielfeld;
    }
}
