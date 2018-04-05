public class UnNeg extends UnOp
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