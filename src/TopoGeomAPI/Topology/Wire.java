package TopoGeomAPI.Topology;

import TopoGeomAPI.Support.Types;

import java.util.*;

public class Wire implements TopoEntity {
    //States-------------------------------------------------------------------

    public List<Edge> myEdges;

    //Methods------------------------------------------------------------------

    public Wire(List<Edge> edges) {
        myEdges = edges;
    }

    public Wire()
    {
        myEdges = new ArrayList<Edge>();
    }

    public void Add(Edge edge){
        myEdges.add(edge);
    }

    @Override
    public Types Type() {
        return Types.ShapeTypes_Wire;
    }
}
