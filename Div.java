public class Div extends Exp
{
    public Exp lhs;
    public Exp rhs;
    public void accept(Visitor v)
    {
        v.visitDiv(this);
    }
}