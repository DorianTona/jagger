public class Equal extends Exp
{
    public Exp lhs;
    public Exp rhs;

	public Equal(Exp a, Exp b)
	{
		lhs = a;
		rhs = b;
	}

    public void accept(Visitor v)
    {
        v.visitEqual(this);
    }
}