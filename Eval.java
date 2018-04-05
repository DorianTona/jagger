public class Eval extends Visitor
{
	public double res;

	public Eval(Exp exp){
		res = 0.0;
		exp.accept(this);
	}

	public void visitNum(Num pVal){

		res = pVal.aVal;
	}

	public void visitAdd(Add pVal){
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = temp + res;
	}

	public void visitSub(Sub pVal){
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = temp - res;
	}

	public void visitMul(Mul pVal){
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = temp * res;
	}

	public void visitDiv(Div pVal){
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = temp / res;
	}

//====================================================//
	public void visitUnNeg(UnNeg pVal){
		pVal.aVal.accept(this);
		res = -res;
	}

	public void visitSup(Sup pVal){
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = (temp > res)?1:0;
	}

	public void visitInf(Inf pVal){
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = (temp < res)?1:0;
	}

	public void visitSupEqual(SupEqual pVal){
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = (temp >= res)?1:0;
	}

	public void visitInfEqual(InfEqual pVal){
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = (temp <= res)?1:0;
	}

	public void visitEqual(Equal pVal){
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = (temp == res)?1:0;
	}

	public void visitNonEqual(NonEqual pVal){
		pVal.lhs.accept(this);
		double temp = res;
		pVal.rhs.accept(this);
		res = (temp != res)?1:0;
	}

	public void print(Exp e)
    {
        e.accept(this);
    }
}