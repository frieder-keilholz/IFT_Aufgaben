import graph.Vertex;
import graph.Visitor;

public class SpielfeldVisitor implements Visitor {

    private Spielfeld zielSpielfeld;

    public SpielfeldVisitor(Spielfeld zielFeld){
        this.zielSpielfeld = zielFeld;
    }

    @Override
    public boolean visit(Vertex vertexToVisit) {
        SpielfeldVertex spielfeldVertexToVisit = (SpielfeldVertex) vertexToVisit;
        if(spielfeldVertexToVisit.getSpielfeld().compareContent(zielSpielfeld)){
            return true;
        }else{
            return false;
        }
    }
}
