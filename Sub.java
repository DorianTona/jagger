public class Sub extends Exp{
    public Exp lhs;
    public Exp rhs;
    public void accept(Visitor v)
    {
        v.visitSub(this);
    }

}