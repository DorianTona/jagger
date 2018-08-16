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

    public void setScope(Scope s) { scope = s; }

    public double getRes() { return res; }

    ///////TYPES///////
	public void visit(Num pVal) {
		res = pVal.aVal;
		sRes = Double.toString(res);
	}
    public void visit(Chaine n)
    {
        sRes = n.aVal;
    }

    ///////CALCUL///////
    //unary
	public void visit(UnNeg pVal){
		pVal.aVal.accept(this);
		res = -res;
		sRes = Double.toString(res);
	}

	//binary
	public void visit(Add pVal) {
		//Transform variables into expressions
		if(pVal.lhs instanceof Variable){
			pVal.lhs = scope.getInScope(((Variable)pVal.lhs).name);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = scope.getInScope(((Variable)pVal.rhs).name);
		}

		if(pVal.lhs instanceof Chaine && pVal.rhs instanceof Chaine) {
    	    pVal.lhs.accept(this);
    	    String temp = sRes.replace("\"", "");
    	    pVal.rhs.accept(this);
    	    sRes = "\""+temp + sRes.replace("\"", "") + "\"";
    	} else {
			pVal.lhs.accept(this);
			double temp = res;
			pVal.rhs.accept(this);
			res = temp + res;
			sRes = Double.toString(res);
		}
	}
	public void visit(Sub pVal) {
		if(pVal.lhs instanceof Variable){
			pVal.lhs = scope.getInScope(((Variable)pVal.lhs).name);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = scope.getInScope(((Variable)pVal.rhs).name);
		}
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = temp - res;
		sRes = Double.toString(res);
	}
	public void visit(Mul pVal) {
		if(pVal.lhs instanceof Variable){
			pVal.lhs = scope.getInScope(((Variable)pVal.lhs).name);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = scope.getInScope(((Variable)pVal.rhs).name);
		}
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = temp * res;
		sRes = Double.toString(res);
	}
	public void visit(Div pVal) {
		if(pVal.lhs instanceof Variable){
			pVal.lhs = scope.getInScope(((Variable)pVal.lhs).name);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = scope.getInScope(((Variable)pVal.rhs).name);
		}
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = temp / res;
		sRes = Double.toString(res);
	}

    ///////COMPARISONS///////
	public void visit(Sup pVal) {
		if(pVal.lhs instanceof Variable){
			pVal.lhs = scope.getInScope(((Variable)pVal.lhs).name);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = scope.getInScope(((Variable)pVal.rhs).name);
		}
		if(pVal.lhs instanceof Chaine && pVal.rhs instanceof Chaine) {
			pVal.lhs.accept(this);
			double temp = sRes.length();
			pVal.rhs.accept(this);
			if(temp > sRes.length())
				sRes = "1";
			else
				sRes = "0";
		} else {
			pVal.lhs.accept(this);
			double temp = res;
			pVal.rhs.accept(this);
			res = (temp > res)?1:0;
			sRes = Double.toString(res);
		}
	}
	public void visit(SupEqual pVal){
		if(pVal.lhs instanceof Variable){
			pVal.lhs = scope.getInScope(((Variable)pVal.lhs).name);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = scope.getInScope(((Variable)pVal.rhs).name);
		}
		if(pVal.lhs instanceof Chaine && pVal.rhs instanceof Chaine) {
			pVal.lhs.accept(this);
			double temp = sRes.length();
			pVal.rhs.accept(this);
			if(temp >= sRes.length())
				sRes = "1";
			else
				sRes = "0";
		} else {
			pVal.lhs.accept(this);
			double temp = res;
			pVal.rhs.accept(this);
			res = (temp >= res)?1:0;
			sRes = Double.toString(res);
		}
	}

	public void visit(Inf pVal) {
		if(pVal.lhs instanceof Variable){
			pVal.lhs = scope.getInScope(((Variable)pVal.lhs).name);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = scope.getInScope(((Variable)pVal.rhs).name);
		}
		if(pVal.lhs instanceof Chaine && pVal.rhs instanceof Chaine) {
			pVal.lhs.accept(this);
			double temp = sRes.length();
			pVal.rhs.accept(this);
			if(temp < sRes.length())
				sRes = "1";
			else
				sRes = "0";
		} else  {
			pVal.lhs.accept(this);
			double temp = res;
			pVal.rhs.accept(this);
			res = (temp < res)?1:0;
			sRes = Double.toString(res);
		}
	}
	public void visit(InfEqual pVal){
		if(pVal.lhs instanceof Variable){
			pVal.lhs = scope.getInScope(((Variable)pVal.lhs).name);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = scope.getInScope(((Variable)pVal.rhs).name);
		}
		if(pVal.lhs instanceof Chaine && pVal.rhs instanceof Chaine) {
			pVal.lhs.accept(this);
			double temp = sRes.length();
			pVal.rhs.accept(this);
			if(temp <= sRes.length())
				sRes = "1";
			else
				sRes = "0";
		} else {
			pVal.lhs.accept(this);
			double temp = res;
			pVal.rhs.accept(this);
			res = (temp <= res)?1:0;
			sRes = Double.toString(res);
		}
	}

	public void visit(Equal pVal){
		if(pVal.lhs instanceof Variable){
			pVal.lhs = scope.getInScope(((Variable)pVal.lhs).name);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = scope.getInScope(((Variable)pVal.rhs).name);
		}
		if(pVal.lhs instanceof Chaine && pVal.rhs instanceof Chaine) {
			pVal.lhs.accept(this);
			String temp = sRes;
			pVal.rhs.accept(this);
			if(temp.equals(sRes))
				sRes = "1";
			else
				sRes = "0";
		} else {
			pVal.lhs.accept(this);
			double temp = res;
			pVal.rhs.accept(this);
			res = (temp == res)?1:0;
			sRes = Double.toString(res);
		}
	}
	public void visit(NonEqual pVal){
		if(pVal.lhs instanceof Variable){
			pVal.lhs = scope.getInScope(((Variable)pVal.lhs).name);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = scope.getInScope(((Variable)pVal.rhs).name);
		}
		if(pVal.lhs instanceof Chaine && pVal.rhs instanceof Chaine) {
			pVal.lhs.accept(this);
			String temp = sRes;
			pVal.rhs.accept(this);
			if(!temp.equals(sRes))
				sRes = "1";
			else
				sRes = "0";
		} else {
			pVal.lhs.accept(this);
			double temp = res;
			pVal.rhs.accept(this);
			res = (temp != res)?1:0;
			sRes = Double.toString(res);
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
    	if(n.cond instanceof Variable){
			n.cond = scope.getInScope(((Variable)n.cond).name);
		}
		if(n.expThen instanceof Variable){
			n.expThen = scope.getInScope(((Variable)n.expThen).name);
		}
		if(n.expElse instanceof Variable){
			n.expElse = scope.getInScope(((Variable)n.expElse).name);
		}

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

    }
}