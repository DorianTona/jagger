public class SupEqual extends Exp
{
    public Exp lhs;
    public Exp rhs;

	public SupEqual(Exp a, Exp b)
	{
		lhs = a;
		rhs = b;
	}

    public void accept(Visitor v)
    {
        v.visit(this);
    }
}