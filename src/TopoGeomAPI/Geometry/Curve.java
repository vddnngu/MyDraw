package TopoGeomAPI.Geometry;

import TopoGeomAPI.Support.PEquation;
import TopoGeomAPI.Support.Types;

public class Curve implements GeometryEntity {
    //States-------------------------------------------------------------------

    private PEquation myEquation;
    private double first,last;  // first and last parameters t;
    private Types myType;

    //Methods------------------------------------------------------------------

    public Curve(PEquation Equation, double first, double last){
        this.myEquation=Equation;
        this.first=first;
        this.last=last;
        switch (Equation.Type()){
            case EquationType_Lineal:       myType=Types.CurveTypes_Line; break;
            case EquationType_Circle:       myType=Types.CurveTypes_Circle; break;
            case EquationType_Ellipse:      myType=Types.CurveTypes_Ellipse; break;
        }
    }

    public double getFirst(){
        return first;
    }

    public double getLast(){
        return last;
    }

    public Point Value(double t){
        return new Point(myEquation.xt(t), myEquation.yt(t));
    }

    public Types Type(){
        return myType;
    }

}
