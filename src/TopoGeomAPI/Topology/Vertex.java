package TopoGeomAPI.Topology;

import TopoGeomAPI.Geometry.Point;
import TopoGeomAPI.Support.Types;

public class Vertex implements TopoEntity {
    //States-------------------------------------------------------------------

    Point myLocalPoint;

    //Methods------------------------------------------------------------------

    public Vertex() {
        myLocalPoint = new Point(0.,0.);
    }

    public Vertex(double X, double Y) {
        myLocalPoint = new Point(X,Y);
    }

    public Vertex(Point p) {
        myLocalPoint = new Point(p);
    }

    public Vertex(Vertex v) {
        myLocalPoint = new Point(v.myLocalPoint);
    }

    public double X(){
        return myLocalPoint.X;
    }

    public double Y(){
        return myLocalPoint.Y;
    }

    @Override
    public Types Type() {
        return Types.ShapeTypes_Vertex;
    }
}
