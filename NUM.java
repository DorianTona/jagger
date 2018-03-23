public class Num extends Exp
{
    public double aVal;

    public Num(double pVal)
    {
        aVal = pVal;
    }
    
    public void accept(Visitor v)
    {
        v.visitNum(this);
    }
}