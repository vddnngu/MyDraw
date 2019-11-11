package TopoGeomAPI.Support;

import TopoGeomAPI.Geometry.Point;

public class Ax2d {
    //States-------------------------------------------------------------------

    private Direction myXDir;
    private Direction myYXir;
    private Point myCenter;

    //Methods------------------------------------------------------------------

    public Ax2d(Point centet, Direction yDir){
        myCenter = centet;
        myYXir = yDir;
        //TODO Calculate xDir!
    }

    public void Move(Direction dir, double distance){
        myCenter.X+=dir.getCords().X*distance;
        myCenter.Y+=dir.getCords().Y*distance;
    }

    public Ax2d Moved(Direction dir, double distance){
        Point newCenter = new Point(
        myCenter.X+dir.getCords().X*distance,
        myCenter.Y+dir.getCords().Y*distance);
        return new Ax2d(newCenter, myYXir);

    }
}
