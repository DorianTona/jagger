public class Sub extends Exp{
    public Exp lhs;
    public Exp rhs;

	public Sub(Exp a, Exp b)
	{
		lhs = a;
		rhs = b;
	}

    public void accept(Visitor v)
    {
        v.visit(this);
    }

}