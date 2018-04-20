import java.util.ArrayList;
import java.util.HashMap;

public class Scope extends Exp
{
    public HashMap<String, Exp> data;
	public ArrayList<Exp> _in;

    public Scope(HashMap<String, Exp> pLet, ArrayList<Exp> pIn)
    {
        data = pLet;
        _in = pIn;
	}
    
    public void accept(Visitor v)
    {
        v.visitScope(this);
    }
}