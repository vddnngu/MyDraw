package TopoGeomAPI.Topology;

import TopoGeomAPI.Support.Ax2d;
import TopoGeomAPI.Support.Direction;

public class TopoShape {

    //States-------------------------------------------------------------------

    Ax2d myAXO; // now iеs only Location in the future also Rotation
    TopoEntity myShape; // shape prototype

    //Methods------------------------------------------------------------------

    public TopoShape(TopoEntity shape, Ax2d AXO){
        myShape=shape;
        myAXO=AXO;
    }

    public TopoShape getCopy() {
        return new TopoShape(myShape,myAXO);
    }

    public TopoEntity getTopoEntity(){
        return myShape;
    }

    public Ax2d getAXO() {
        return  myAXO;
    }

    public TopoShape getInstance(Ax2d newAXO) {
        return new TopoShape(myShape, newAXO);
    }

    public void Move(Direction dir, double distance){
        myAXO.Move(dir,distance);
    }

    public TopoShape Moved(Direction dir, double distance){
        return new TopoShape(myShape,myAXO.Moved(dir,distance));
    }
}
