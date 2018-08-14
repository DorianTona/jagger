public class Assignment extends Exp
{
    public String vName;
    public Exp exp;
    public Scope scope;

	public Assignment(String str, Exp e, Scope s)
	{
		vName = str;
        exp = e;
        scope = s;
	}

    public void accept(Visitor v)
    {
        v.visit(this);
    }
}