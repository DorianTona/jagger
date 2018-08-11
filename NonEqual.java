public class NonEqual extends Exp
{
    public Exp lhs;
    public Exp rhs;
    
    public NonEqual(Exp a, Exp b)
	{
		lhs = a;
		rhs = b;
	}

    public void accept(Visitor v)
    {
        v.visit(this);
    }
}