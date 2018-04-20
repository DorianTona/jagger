public class Variable extends Exp
{
    public String aVal;

    public Variable(String pVal)
    {
        aVal = pVal;
    }
    
    public void accept(Visitor v)
    {
        v.visitVariable(this);
    }
}