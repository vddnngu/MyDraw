package TopoGeomAPI.HotFix;

import TopoGeomAPI.HotFix.Point;

import java.util.ArrayList;
import java.util.List;

public class Wire implements  Shape{

    List<Point> points;
    Point Ax2d;
    @Override
    public STypes type() {
        return STypes.Wire;
    }

    public Wire(){
        points = new ArrayList<>();
    }

    public Wire(List<Point> Points){
        //TODO
    }

    public List<Point> getPoints() {
        List<Point> resPoints = new ArrayList<>();
        for (int i =0; i<points.size();i++){
            resPoints.add(new Point(points.get(i).X+Ax2d.X, points.get(i).Y+Ax2d.Y));
        }

        return resPoints;
    }

    public Point getAx2d() {
        return Ax2d;
    }

    public void setAx2d(Point ax2d) {
        Ax2d = ax2d;
    }

    public void addPoint(Point point){
        points.add(point);
    }
}
