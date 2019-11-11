package TopoGeomAPI.Topology;

import TopoGeomAPI.Geometry.Point;

public class Vertex implements TopoEntity {
    Point myGlobalPoint;
    public Vertex()
    {
        myGlobalPoint = new Point(0.,0.);
    }
    public Vertex(double X, double Y)
    {
        myGlobalPoint = new Point(X,Y);
    }
    public Vertex(Point p)
    {
        myGlobalPoint = new Point(p);
    }
    public Vertex(Vertex v)
    {
        myGlobalPoint = new Point(v.myGlobalPoint);
    }
}
