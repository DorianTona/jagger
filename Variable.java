public class Variable extends Exp
{
    public String name;

    public Variable(String pVal)
    {
        name = pVal;
    }
    
    public void accept(Visitor v)
    {
        v.visit(this);
    }
}