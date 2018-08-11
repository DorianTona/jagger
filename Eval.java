import java.util.ArrayList;
import java.util.HashMap;

public class Eval extends Visitor
{
	public double res;
	public String sRes;
	private ArrayList<Scope> data = new ArrayList<Scope>();

	//constructor
	public Eval() {
		res = 0.0;
		sRes = "";
	}

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
			pVal.lhs = getInActiveScope(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = getInActiveScope(((Variable)pVal.rhs).aVal);
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
			pVal.lhs = getInActiveScope(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = getInActiveScope(((Variable)pVal.rhs).aVal);
		}
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = temp - res;
		sRes = Double.toString(res);
	}
	public void visit(Mul pVal) {
		if(pVal.lhs instanceof Variable){
			pVal.lhs = getInActiveScope(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = getInActiveScope(((Variable)pVal.rhs).aVal);
		}
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = temp * res;
		sRes = Double.toString(res);
	}
	public void visit(Div pVal) {
		if(pVal.lhs instanceof Variable){
			pVal.lhs = getInActiveScope(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = getInActiveScope(((Variable)pVal.rhs).aVal);
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
			pVal.lhs = getInActiveScope(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = getInActiveScope(((Variable)pVal.rhs).aVal);
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
			pVal.lhs = getInActiveScope(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = getInActiveScope(((Variable)pVal.rhs).aVal);
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
			pVal.lhs = getInActiveScope(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = getInActiveScope(((Variable)pVal.rhs).aVal);
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
			pVal.lhs = getInActiveScope(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = getInActiveScope(((Variable)pVal.rhs).aVal);
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
			pVal.lhs = getInActiveScope(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = getInActiveScope(((Variable)pVal.rhs).aVal);
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
			pVal.lhs = getInActiveScope(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = getInActiveScope(((Variable)pVal.rhs).aVal);
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
	}
	public void visit(Ins n)
    {
    	if(n.cond instanceof Variable){
			n.cond = getInActiveScope(((Variable)n.cond).aVal);
		}
		if(n.expThen instanceof Variable){
			n.expThen = getInActiveScope(((Variable)n.expThen).aVal);
		}
		if(n.expElse instanceof Variable){
			n.expElse = getInActiveScope(((Variable)n.expElse).aVal);
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
        getInActiveScope(n.aVal).accept(this);
        sRes = Double.toString(res);
    }
    public void visit(Scope n)
    {
    	data = n.activeScopes;
        for (Exp a : n._in) {
            a.accept(this);
        }
    }

    ///////PRINT///////
	public void print(Exp e)
    {
        e.accept(this);
        System.out.println(sRes);
    }

    /////UTILITAIRES/////
    public Exp getInActiveScope(String s)
    {       
        Exp e = null;
        for(Scope scope:data) {
            if(scope.hasId(s)) {
                e = scope.get(s);
                break;
            }
        }
        return e;
    }
}