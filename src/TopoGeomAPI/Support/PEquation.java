package TopoGeomAPI.Support;

public interface PEquation {
    //Methods------------------------------------------------------------------

    double xt(double t);
    double yt(double t);

    Types Type();
}

class LinealEquation implements PEquation
{
    /*
     * x = x0 + A*t
     * y = y0 + B*t
     */
    //States-------------------------------------------------------------------

    private double A; //x0
    private double B; //y0
    private double M; //dx
    private double N; //dy

    //Methods------------------------------------------------------------------

    public LinealEquation(double A, double B, double M, double N){
        this.A=A;
        this.B=B;
        this.M=M;
        this.N=N;
    }

    @Override
    public double xt(double t) {
        return A+t*M;
    }

    @Override
    public double yt(double t) {
        return B+t*N;
    }

    @Override
    public Types Type() {
        return Types.EquationType_Lineal;
    }
}

class CircleEquation implements PEquation
{
    /*
     * x = A*cos(t)
     * y = B*sin(t)
     */
    //States-------------------------------------------------------------------

    private double R1; //A
    private double R2; //B

    //Methods------------------------------------------------------------------

    public  CircleEquation(double R1, double R2){
        this.R1=R1;
        this.R2=R2;
    }

    @Override
    public double xt(double t) {
        return R1*Math.cos(t);
    }

    @Override
    public double yt(double t) {
        return R2*Math.sin(t);
    }

    @Override
    public Types Type() {
        if(R1!=R2)
            return Types.EquationType_Ellipse;
        else
            return Types.EquationType_Circle;
    }
}