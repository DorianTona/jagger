import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Function extends Exp
{
    public Scope parent;
    public String name;
    private ArrayList<Exp> params;
    private HashMap<String,Exp> bindedParams;
	private Exp ins;

    //constructor
    public Function(Scope s, String str, ArrayList<Exp> p, Exp e)
    {
        parent = s;
        name = str;
        params = p;
        ins = e;
    }
    public Function(Scope s)
    {
        this(s, null, null, null);
    }

    //params
    public ArrayList<Exp> getParams() { return params; }
    public void addParam(Exp e) { params.add(e); }
    public int nbParams() { return params.size(); }

    public void bindParams(Function f)
    {
        if(this.nbParams() != f.nbParams())
        {
            bindedParams = new HashMap<String,Exp>();
            Iterator<Exp> variables = params.iterator();
            Iterator<Exp> values = f.getParams().iterator();
            while(variables.hasNext())
                bindedParams.put(((Variable)variables.next()).name, values.next());
        }
    }

    public Exp getInScope(String s)
    {
        if(bindedParams.containsKey(s))
            return bindedParams.get(s);
        else
            return parent.getInScope(s);
    }

    //ins
    public void setIns(Exp e) { ins = e; }
    
    //visitor
    public void accept(Visitor v)
    {
        //v.setScope(this);
        v.visit(this);
    }
}