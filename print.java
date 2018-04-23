public class Print extends Exp
{
	Exp aVal;

    public Print(Exp pVal) {
    	aVal = pVal;
    }
    
    public void accept(Visitor v)
    {
        v.visitPrint(this);
    }
}