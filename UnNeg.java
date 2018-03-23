public class UnNeg extends Exp
{
    public Exp aVal;

    public UnNeg(Exp pVal)
    {
        aVal = pVal;
    }
    
    public void accept(Visitor v)
    {
        v.visitUnNeg(this);
    }
}