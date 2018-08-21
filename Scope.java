import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Scope extends Exp
{
    public Scope parent;
    public HashMap<String, Exp> data;
	public ArrayList<Exp> ins;
    public HashMap<String, Function> functions;
    public Function bindedFunction;

    //constructor
    public Scope(Scope s)
    {
        parent = s;
        data = new HashMap<String, Exp>();
        ins = new ArrayList<Exp>();
        functions = new HashMap<String, Function>();
    }

    //data
    public void addDeclaration(String s, Exp e)
    {
        Eval ev = new Eval(this);
        e.accept(ev);
        Num trueValue = new Num(ev.getRes());
        if(!data.containsKey(s))
            data.put(s, trueValue);
        else
            System.out.println("The variable " + s + " is already defined in the scope.");
    }
    public void changeValue(String s, Exp e)
    {
        Eval ev = new Eval(this);
        e.accept(ev);
        Num trueValue = new Num(ev.getRes());

        if(data.containsKey(s))
            data.remove(s);

        data.put(s, trueValue);
    }
    public Exp getInScope(String s)
    {
        //if a function is binded, search for parameters before searching for scope variables
        if(bindedFunction != null)
            if(bindedFunction.bindedParams.containsKey(s))
                return bindedFunction.bindedParams.get(s);

        //search for the variable in the current scope and its parents
        if(data.containsKey(s))
            return data.get(s);
        else if(parent != null)            
            return parent.getInScope(s);
        else{
            System.out.println("The variable " + s + " does not exist in current scope");
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
    public void addFunction(Function f)
    {
        functions.putIfAbsent(f.name, f);
    }

    public Exp getFunctionInScope(String s)
    {
        if(functions.containsKey(s))
            return functions.get(s);
        else
            return parent.getFunctionInScope(s);
    }
    
    //visitor
    public void accept(Visitor v)
    {
        v.visit(this);
    }
}