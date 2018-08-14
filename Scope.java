import java.util.ArrayList;
import java.util.HashMap;

public class Scope extends Exp
{
    public Scope parent;
    private HashMap<String, Exp> data;
	private ArrayList<Exp> ins;

    //constructor
    public Scope(Scope s)
    {
        data = new HashMap<String, Exp>();
        ins = new ArrayList<Exp>();
        parent = s;
    }

    //data
    public HashMap<String, Exp> getData() { return data; }
    public void setData(HashMap<String, Exp> d) { data = d; }

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
    
    //visitor
    public void accept(Visitor v)
    {
        v.setScope(this);
        v.visit(this);
    }
}