package TopoGeomAPI.HotFix;

public class Point {
    //States-------------------------------------------------------------------

    public int X, Y;

    //Methods------------------------------------------------------------------

    public Point(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
    public Point(Point p) {
        this.X = p.X;
        this.Y = p.Y;
    }
}
