public class InfEqual extends Exp
{
    public Exp lhs;
    public Exp rhs;

	public InfEqual(Exp a, Exp b)
	{
		lhs = a;
		rhs = b;
	}

    public void accept(Visitor v)
    {
        v.visitInfEqual(this);
    }
}