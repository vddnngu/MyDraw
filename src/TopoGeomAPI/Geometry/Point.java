package TopoGeomAPI.Geometry;

public class Point implements GeometryEntity {
    //States-------------------------------------------------------------------

    public double X, Y;

    //Methods------------------------------------------------------------------

    public Point(double X, double Y)
    {
        this.X = X;
        this.Y = Y;
    }
    public Point(Point p)
    {
        this.X = p.X;
        this.Y = p.Y;
    }
}
