package TopoGeomAPI.Geometry;

public class Point {
    double X, Y;
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
