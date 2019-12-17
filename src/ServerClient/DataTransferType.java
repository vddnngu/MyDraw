package ServerClient;

import TopoGeomAPI.Geometry.Point;
import TopoGeomAPI.HotFix.Circle;
import TopoGeomAPI.HotFix.STypes;
import TopoGeomAPI.HotFix.Shape;
import TopoGeomAPI.HotFix.Wire;
import TopoGeomAPI.Support.Types;
import TopoGeomAPI.Topology.TopoShape;

import java.util.List;

public class DataTransferType {

    public DataTransferType(){

    }
    public static String transfer(Shape shape){
        String res = "";
        if(shape.type()== STypes.Wire){
            Wire tmp = (Wire)shape;
            res+="W";
            res+="|"+tmp.getAx2d().X+"|"+tmp.getAx2d().Y;
            List<Point> listPoints = tmp.getPoints();
            res+="|"+listPoints.size();
            for (int i = 0; i<listPoints.size();i++){
                res+="|"+listPoints.get(i).X+"|"+listPoints.get(i).Y;
            }
        }else
        {
            Circle tmp = (Circle)shape;
            res+="C";
            res+="|"+tmp.getAx2d().X+"|"+tmp.getAx2d().Y;
            res+="|"+tmp.getRadius();
            res+="|"+tmp.getCentr().X+"|"+tmp.getCentr().Y;
        }
        return res;
    }
    public static Shape transfer(String str) {
        Shape res;
        if(str.charAt(0)=='W'){
            res = new Wire();
            String[] strs = str.split("|");
            ((Wire)res).setAx2d(new Point(Integer.parseInt(strs[1]),Integer.parseInt(strs[2])));
            int size = Integer.parseInt(strs[3]);
            for (int i = 0; i<size*2; i+=2){
                ((Wire)res).addPoint(new Point(new Point(Integer.parseInt(strs[4+i]),Integer.parseInt(strs[5+i]))));
            }
        }else{
            res = new Circle();
            String[] strs = str.split("|");
            ((Circle)res).setAx2d(new Point(Integer.parseInt(strs[1]),Integer.parseInt(strs[2])));
            ((Circle)res).setRadius(Integer.parseInt(strs[3]));
            ((Circle)res).setCentr(new Point(Integer.parseInt(strs[4]),Integer.parseInt(strs[5])));
        }
        return res;
    }
}
