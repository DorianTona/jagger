public class Chaine extends Exp
{
    public String aVal;

    public Chaine(String pVal)
    {
        aVal = pVal;
    }
    
    public void accept(Visitor v)
    {
        v.visitChaine(this);
    }
}