public class Num extends Exp
{
    public int aVal;

    public Num(int pVal)
    {
        aVal = pVal;
    }
    public void accept(Visitor v)
    {
        v.visitNum(this);
    }
}