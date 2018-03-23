public class Sup extends Exp
{
    public Exp lhs;
    public Exp rhs;

	public Sup(Exp a, Exp b)
	{
		lhs = a;
		rhs = b;
	}

    public void accept(Visitor v)
    {
        v.visitSup(this);
    }
}