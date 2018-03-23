public class Add extends Exp{
    public Exp lhs;
    public Exp rhs;
    public void accept(Visitor v)
    {
        v.visitAdd(this);
    }

}