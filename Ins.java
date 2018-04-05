public class Ins extends Exp
{
    
    
    public void accept(Visitor v)
    {
        v.visitIns(this);
    }
}