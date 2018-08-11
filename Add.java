public class Add extends Exp{
    public Exp lhs;
    public Exp rhs;

	public Add(Exp a, Exp b)
	{
		lhs = a;
		rhs = b;
	}

    public void accept(Visitor v)
    {
        v.visit(this);
    }

}