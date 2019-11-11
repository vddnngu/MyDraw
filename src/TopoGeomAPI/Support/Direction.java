package TopoGeomAPI.Support;

import TopoGeomAPI.Geometry.Point;

public class Direction {
    //States-------------------------------------------------------------------

    private double X,Y;

    //Methods------------------------------------------------------------------

    public Direction(double x, double y) {
        X = x;
        Y = y;
        Normalize();
    }

    public Point getCords(){
        return new Point(X,Y);
    }

    private void Normalize(){
        double len = Math.sqrt(X*X+Y*Y);
        X/=len;
        Y/=len;
    }
}
