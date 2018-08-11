public class Inf extends Exp
{
    public Exp lhs;
    public Exp rhs;

	public Inf(Exp a, Exp b)
	{
		lhs = a;
		rhs = b;
	}

    public void accept(Visitor v)
    {
        v.visit(this);
    }
}