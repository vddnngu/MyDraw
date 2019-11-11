package TopoGeomAPI.Topology;

import TopoGeomAPI.Geometry.Curve;
import TopoGeomAPI.Support.Types;

public class Edge implements TopoEntity {
    //States-------------------------------------------------------------------

    Curve myCurve;
    private double first,last;  // first and last parameters t;

    //Methods------------------------------------------------------------------

    public Edge(Curve curve, double first, double last) {
        myCurve=curve;
        this.first=first;
        this.last=last;
    }

    public double getFirst(){
        return first;
    }

    public double getLast(){
        return last;
    }

    public Vertex startVertex(){
        return new Vertex(myCurve.Value(first));
    }

    public Vertex endVertex(){
        return new Vertex(myCurve.Value(last));
    }

    public Vertex getVertex(double t) {
        if((t<first) && (t>last) || (t<last) && (t>first)) return null;
        return new Vertex(myCurve.Value(t));
    }

    @Override
    public Types Type() {
        return Types.ShapeTypes_Edge;
    }
}
