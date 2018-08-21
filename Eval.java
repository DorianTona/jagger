import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Eval extends Visitor
{
	private double res = 0.0;
	private String sRes;
	private ArrayList<String> toPrint = new ArrayList<String>();
    private Scope scope;

	//constructors
	public Eval(Exp e)
	{
		e.accept(this);

		for(String s:toPrint)
			System.out.println(s);
		System.out.println();
	}
	public Eval(Scope s)
	{
		scope = s;
	}

    public double getRes() { return res; }

    ///////TYPES///////
	public void visit(Num n) {
		res = n.aVal;
		sRes = Double.toString(res);
	}
    public void visit(Chaine n)
    {
        sRes = n.aVal;
    }

    ///////CALCUL///////
    //unary
	public void visit(UnNeg n){
		n.aVal.accept(this);
		res = -res;
		sRes = Double.toString(res);
	}

	//binary
	public void visit(Add n) 
	{
		if(n.lhs instanceof Chaine && n.rhs instanceof Chaine) {
    	    n.lhs.accept(this);
    	    String temp = sRes.replace("\"", "");
    	    n.rhs.accept(this);
    	    sRes = "\""+temp + sRes.replace("\"", "") + "\"";
    	} else if(!(n.lhs instanceof Chaine) && !(n.rhs instanceof Chaine)) {
			n.lhs.accept(this);
			double temp = res;
			n.rhs.accept(this);
			res = temp + res;
			sRes = Double.toString(res);
		} else
		System.out.println("These types can not be operated together.");
	}
	public void visit(Sub n) 
	{
		if(!(n.lhs instanceof Chaine) && !(n.rhs instanceof Chaine)) {
			n.lhs.accept(this);
			double temp = res;
			n.rhs.accept(this);
			res = temp - res;
			sRes = Double.toString(res);
		} else
		System.out.println("These types can not be operated together.");
	}
	public void visit(Mul n) 
	{
		if(!(n.lhs instanceof Chaine) && !(n.rhs instanceof Chaine)) 
		{
			n.lhs.accept(this);
			double temp = res;
			n.rhs.accept(this);
			res = temp * res;
			sRes = Double.toString(res);
		} else
		System.out.println("These types can not be operated together.");
	}
	public void visit(Div n) 
	{
		if(!(n.lhs instanceof Chaine) && !(n.rhs instanceof Chaine)) 
		{
			n.lhs.accept(this);
			double temp = res;
			n.rhs.accept(this);
			res = temp / res;
			sRes = Double.toString(res);
		} else
		System.out.println("These types can not be operated together.");
	}

    ///////COMPARISONS///////
	public void visit(Sup n) 
	{
		if(n.lhs instanceof Chaine && n.rhs instanceof Chaine) {
			n.lhs.accept(this);
			double temp = sRes.length();
			n.rhs.accept(this);
			if(temp > sRes.length())
				sRes = "1";
			else
				sRes = "0";
		} else if(!(n.lhs instanceof Chaine) && !(n.rhs instanceof Chaine)) {
			n.lhs.accept(this);
			double temp = res;
			n.rhs.accept(this);
			res = (temp > res)?1:0;
			sRes = Double.toString(res);
		} else {
			res = 0;
			System.out.println("These types can not be operated together.");
		}
	}
	public void visit(SupEqual n)
	{
		if(n.lhs instanceof Chaine && n.rhs instanceof Chaine) {
			n.lhs.accept(this);
			double temp = sRes.length();
			n.rhs.accept(this);
			if(temp >= sRes.length())
				sRes = "1";
			else
				sRes = "0";
		} else if(!(n.lhs instanceof Chaine) && !(n.rhs instanceof Chaine)) {
			n.lhs.accept(this);
			double temp = res;
			n.rhs.accept(this);
			res = (temp >= res)?1:0;
			sRes = Double.toString(res);
		} else {
			res = 0;
			System.out.println("These types can not be operated together.");
		}
	}

	public void visit(Inf n) 
	{
		if(n.lhs instanceof Chaine && n.rhs instanceof Chaine) {
			n.lhs.accept(this);
			double temp = sRes.length();
			n.rhs.accept(this);
			if(temp < sRes.length())
				sRes = "1";
			else
				sRes = "0";
		} else if(!(n.lhs instanceof Chaine) && !(n.rhs instanceof Chaine)) {
			n.lhs.accept(this);
			double temp = res;
			n.rhs.accept(this);
			res = (temp < res)?1:0;
			sRes = Double.toString(res);
		} else {
			res = 0;
			System.out.println("These types can not be operated together.");
		}
	}
	public void visit(InfEqual n)
	{
		if(n.lhs instanceof Chaine && n.rhs instanceof Chaine) {
			n.lhs.accept(this);
			double temp = sRes.length();
			n.rhs.accept(this);
			if(temp <= sRes.length())
				sRes = "1";
			else
				sRes = "0";
		} else if(!(n.lhs instanceof Chaine) && !(n.rhs instanceof Chaine)) {
			n.lhs.accept(this);
			double temp = res;
			n.rhs.accept(this);
			res = (temp <= res)?1:0;
			sRes = Double.toString(res);
		} else {
			res = 0;
			System.out.println("These types can not be operated together.");
		}
	}

	public void visit(Equal n)
	{
		if(n.lhs instanceof Chaine && n.rhs instanceof Chaine) {
			n.lhs.accept(this);
			String temp = sRes;
			n.rhs.accept(this);
			if(temp.equals(sRes))
				sRes = "1";
			else
				sRes = "0";
		} else if(!(n.lhs instanceof Chaine) && !(n.rhs instanceof Chaine)) {
			n.lhs.accept(this);
			double temp = res;
			n.rhs.accept(this);
			res = (temp == res)?1:0;
			sRes = Double.toString(res);
		} else {
			res = 0;
			System.out.println("These types can not be operated together.");
		}
	}
	public void visit(NonEqual n)
	{
		if(n.lhs instanceof Chaine && n.rhs instanceof Chaine) {
			n.lhs.accept(this);
			String temp = sRes;
			n.rhs.accept(this);
			if(!temp.equals(sRes))
				sRes = "1";
			else
				sRes = "0";
		} else if(!(n.lhs instanceof Chaine) && !(n.rhs instanceof Chaine)) {
			n.lhs.accept(this);
			double temp = res;
			n.rhs.accept(this);
			res = (temp != res)?1:0;
			sRes = Double.toString(res);
		} else {
			res = 0;
			System.out.println("These types can not be operated together.");
		}
	}

	///////KEYWORDS///////
	public void visit(Print n)
	{
		n.aVal.accept(this);
		toPrint.add(sRes);
	}
	public void visit(Ins n)
    {
	    n.cond.accept(this);
		if(this.res == 1) {
			n.expThen.accept(this);
		} else {
			n.expElse.accept(this);
		}
    }
    public void visit(Variable n)
    {
        scope.getInScope(n.name).accept(this);
        sRes = Double.toString(res);
    }
    public void visit(Scope s)
    {
    	scope = s;
        for (Exp a : s.getInstructions()) {
            a.accept(this);
        }
        scope = s.parent;
    }
	public void visit(Assignment a)
	{
		a.scope.changeValue(a.vName, a.exp);
	}
    public void visit(Function n)
    {
    	Function f = scope.functions.get(n.name);
    	f.bindParams(n);
    	scope.bindedFunction = f;
        for (Exp a : f.getIns()) {
            a.accept(this);
        }
        scope.bindedFunction = null;
    }
}