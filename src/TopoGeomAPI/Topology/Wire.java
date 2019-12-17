package TopoGeomAPI.Topology;

import TopoGeomAPI.Geometry.Point;
import TopoGeomAPI.Support.Types;

import java.util.*;

public class Wire implements TopoEntity {
    //States-------------------------------------------------------------------

    public List<Edge> myEdges;
    public List<Point> points;

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

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public Types Type() {
        return Types.ShapeTypes_Wire;
    }
}
