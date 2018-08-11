import java.util.ArrayList;
import java.util.HashMap;

public class Scope extends Exp
{
    public HashMap<String, Exp> data;
	public ArrayList<Exp> _in;

    public static ArrayList<Scope> activeScopes = new ArrayList<Scope>();
    public static int currentScope = -1;

    public Scope()
    {
        data = new HashMap<String, Exp>();
        _in = new ArrayList<Exp>();
        activeScopes.add(this);
        currentScope++;
    }

    public void addDeclaration(String s, Exp e)
    {
        data.putIfAbsent(s, e);
    }

    public void addInstruction(Exp e)
    {
        _in.add(e);
    }

    public void changeValue(String s, Exp e)
    {
        data.put(s, e);
    }

    public boolean hasId(String s)
    {
        return data.containsKey(s);
    }

    public Exp get(String s)
    {
        return data.get(s);
    }

    public void exit()
    {
        activeScopes.remove(currentScope);
        currentScope--;
    }
    
    public void accept(Visitor v)
    {
        v.visit(this);
    }
}