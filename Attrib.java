public class Attrib extends Exp
{   
    public String name;
    public Exp rhs;

    public Attrib(String vName, Exp b)
    {
        name = vName.replace("var ", "");
        rhs = b;
	}
    
    public void accept(Visitor v)
    {
        v.visitAttrib(this);
    }
}