import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Scope extends Exp
{
    public Scope parent;
    private HashMap<String, Exp> data;
	private ArrayList<Exp> ins;
    private HashMap<String, Exp> functions;

    //constructor
    public Scope(Scope s)
    {
        parent = s;
        data = new HashMap<String, Exp>();
        ins = new ArrayList<Exp>();
        functions = new HashMap<String, Exp>();
    }

    //data
    public Set<String> keySet() { return data.keySet(); }
    public boolean containsKey(String s) { return data.containsKey(s); }
    public Exp get(String s) { return data.get(s); }

    public void addDeclaration(String s, Exp e)
    {
        Eval ev = new Eval(this);
        e.accept(ev);
        Num trueValue = new Num(ev.getRes());
        data.putIfAbsent(s, trueValue);
    }
    public void changeValue(String s, Exp e)
    {
        Eval ev = new Eval(this);
        e.accept(ev);
        Num trueValue = new Num(ev.getRes());
        data.remove(s);
        data.put(s, trueValue);
    }
    public Exp getInScope(String s)
    {
        if(containsKey(s))
            return get(s);
        else
            return parent.getInScope(s);
    }
    
    //instruction
    public ArrayList<Exp> getInstructions()
    {
        return ins;
    }
    public void addInstruction(Exp e)
    {
        ins.add(e);
    }

    //functions
    public void addFunction(Function f)
    {
        functions.putIfAbsent(f.name, f);
    }
    
    //visitor
    public void accept(Visitor v)
    {
        //v.setScope(this);
        v.visit(this);
    }
}