import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Scope extends Exp
{
    public Scope parent;
    private HashMap<String, Exp> data;
	private ArrayList<Exp> ins;
    private HashMap<String, Function> functions;
    private Function bindedFunction;

    //constructor
    public Scope(Scope s)
    {
        parent = s;
        data = new HashMap<String, Exp>();
        ins = new ArrayList<Exp>();
        functions = new HashMap<String, Function>();
    }

    //data
    public Set<String> dataKeySet() { return data.keySet(); }
    public boolean dataIsEmpty() { return data.isEmpty(); }
    public Exp dataGet(String s) { return data.get(s); }

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
        //if a function is binded, search for parameters before searching for scope variables
        if(bindedFunction != null)
            if(bindedFunction.bindedParams.containsKey(s))
                return bindedFunction.bindedParams.get(s);

        if(data.containsKey(s))
            return dataGet(s);
        else if(parent != null)            
            return parent.getInScope(s);
        else{
            System.out.println("This variable does not exist in current scope");
            return null;
        }
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
    public Set<String> fKeySet() { return functions.keySet(); }
    public boolean fIsEmpty() { return functions.isEmpty(); }
    public Function fGet(String s) { return functions.get(s); }
    public void setBindedFunction(Function f) { bindedFunction = f; }

    public void addFunction(Function f)
    {
        functions.putIfAbsent(f.name, f);
    }

    public Exp fGetInScope(String s)
    {
        if(functions.containsKey(s))
            return fGet(s);
        else
            return parent.fGetInScope(s);
    }
    
    //visitor
    public void accept(Visitor v)
    {
        v.visit(this);
    }
}