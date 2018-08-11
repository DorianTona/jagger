public class Mul extends Exp
{
    public Exp lhs;
    public Exp rhs;

	public Mul(Exp a, Exp b)
	{
		lhs = a;
		rhs = b;
	}

    public void accept(Visitor v)
    {
        v.visit(this);
    }
}