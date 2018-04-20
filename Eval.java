import java.util.ArrayList;
import java.util.HashMap;

public class Eval extends Visitor
{
	public double res;
	public String sRes;
	private HashMap<String, Exp> data = new HashMap<String, Exp>();

	public Eval(){
		res = 0.0;
		sRes = "";
	}

    ///////TYPES///////
	public void visitNum(Num pVal) {
		res = pVal.aVal;
		sRes = Double.toString(res);
	}
	public void visitUnNeg(UnNeg pVal){
		pVal.aVal.accept(this);
		res = -res;
		sRes = Double.toString(res);
	}
    public void visitChaine(Chaine n)
    {
        sRes = n.aVal;
    }

    ///////CALCUL///////
	public void visitAdd(Add pVal) {
		//Transform variables into expressions
		if(pVal.lhs instanceof Variable){
			pVal.lhs = data.get(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = data.get(((Variable)pVal.rhs).aVal);
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
	public void visitSub(Sub pVal) {
		if(pVal.lhs instanceof Variable){
			pVal.lhs = data.get(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = data.get(((Variable)pVal.rhs).aVal);
		}
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = temp - res;
		sRes = Double.toString(res);
	}

	public void visitMul(Mul pVal) {
		if(pVal.lhs instanceof Variable){
			pVal.lhs = data.get(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = data.get(((Variable)pVal.rhs).aVal);
		}
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = temp * res;
		sRes = Double.toString(res);
	}
	public void visitDiv(Div pVal) {
		if(pVal.lhs instanceof Variable){
			pVal.lhs = data.get(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = data.get(((Variable)pVal.rhs).aVal);
		}
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = temp / res;
		sRes = Double.toString(res);
	}

    ///////COMPARISONS///////
	public void visitSup(Sup pVal) {
		if(pVal.lhs instanceof Variable){
			pVal.lhs = data.get(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = data.get(((Variable)pVal.rhs).aVal);
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
	public void visitSupEqual(SupEqual pVal){
		if(pVal.lhs instanceof Variable){
			pVal.lhs = data.get(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = data.get(((Variable)pVal.rhs).aVal);
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

	public void visitInf(Inf pVal) {
		if(pVal.lhs instanceof Variable){
			pVal.lhs = data.get(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = data.get(((Variable)pVal.rhs).aVal);
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
	public void visitInfEqual(InfEqual pVal){
		if(pVal.lhs instanceof Variable){
			pVal.lhs = data.get(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = data.get(((Variable)pVal.rhs).aVal);
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

	public void visitEqual(Equal pVal){
		if(pVal.lhs instanceof Variable){
			pVal.lhs = data.get(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = data.get(((Variable)pVal.rhs).aVal);
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
	public void visitNonEqual(NonEqual pVal){
		if(pVal.lhs instanceof Variable){
			pVal.lhs = data.get(((Variable)pVal.lhs).aVal);
		}
		if(pVal.rhs instanceof Variable){
			pVal.rhs = data.get(((Variable)pVal.rhs).aVal);
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

	///////CONDITIONS///////
	public void visitIns(Ins n)
    {
    	if(n.cond instanceof Variable){
			n.cond = data.get(((Variable)n.cond).aVal);
		}
		if(n.expThen instanceof Variable){
			n.expThen = data.get(((Variable)n.expThen).aVal);
		}
		if(n.expElse instanceof Variable){
			n.expElse = data.get(((Variable)n.expElse).aVal);
		}

	    n.cond.accept(this);
		if(this.res == 1) {
			n.expThen.accept(this);
		} else {
			n.expElse.accept(this);
		}
    }
    public void visitVariable(Variable n)
    {
        data.get(n.aVal).accept(this);
        sRes = Double.toString(res);
    }
    public void visitScope(Scope n)
    {
    	data = n.data;
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
}