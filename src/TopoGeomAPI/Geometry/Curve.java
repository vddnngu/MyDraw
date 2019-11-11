package TopoGeomAPI.Geometry;

import TopoGeomAPI.Support.PEquation;
import TopoGeomAPI.Support.Types;

public class Curve implements GeometryEntity {
    //States-------------------------------------------------------------------

    private PEquation myEquation;
    private Types myType;

    //Methods------------------------------------------------------------------

    public Curve(PEquation Equation, double first, double last){
        this.myEquation=Equation;
        switch (Equation.Type()){
            case EquationType_Lineal:       myType=Types.CurveTypes_Line; break;
            case EquationType_Circle:       myType=Types.CurveTypes_Circle; break;
            case EquationType_Ellipse:      myType=Types.CurveTypes_Ellipse; break;
        }
    }

    public Point Value(double t){
        return new Point(myEquation.xt(t), myEquation.yt(t));
    }

    public Types Type(){
        return myType;
    }

}
