public class Mul extends Exp
{
    public Exp lhs;
    public Exp rhs;
    public void accept(Visitor v)
    {
        v.visitMul(this);
    }
}