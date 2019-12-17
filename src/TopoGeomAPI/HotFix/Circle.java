package TopoGeomAPI.HotFix;

import TopoGeomAPI.Geometry.Point;

public class Circle implements Shape{

    double radius;
    Point centr;
    Point Ax2d;
    @Override
    public STypes type() {
        return STypes.Circle;
    }

    public double getRadius() {
        return radius;
    }

    public Point getCentr() {
        return new Point(centr.X+Ax2d.X, centr.Y+Ax2d.Y);
    }

    public Point getAx2d() {
        return Ax2d;
    }

    public void setAx2d(Point ax2d) {
        Ax2d = ax2d;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setCentr(Point centr) {
        this.centr = centr;
    }
}