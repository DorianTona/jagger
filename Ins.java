public class Ins extends Exp
{
	Exp cond, expThen, expElse;

    public Ins(Exp pCond, Exp pExpThen, Exp pExpElse)
    {
        cond=pCond;
		expThen=pExpThen;
		expElse=pExpElse;
	}
    
    public void accept(Visitor v)
    {
        v.visitIns(this);
    }
}