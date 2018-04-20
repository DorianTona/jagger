import java.util.ArrayList;
import java.util.HashMap;

public class PrettyPrinter extends Visitor
{
	private HashMap<String, Exp> data = new HashMap<String, Exp>();
	///////TYPES///////
	public void visitNum(Num n)
	{
		System.out.print(n.aVal);
	}
	public void visitUnNeg(UnNeg n)
	{
		System.out.print("(-");
		n.aVal.accept(this);
		System.out.print(")");
	}
	public void visitChaine(Chaine n)
	{
		System.out.print(n.aVal);
	}

	///////CALCUL///////
	public void visitAdd(Add n){
		System.out.print("(");
		n.lhs.accept(this);
		System.out.print(" + ");
		n.rhs.accept(this);
		System.out.print(")");
	}
	public void visitSub(Sub n){
		System.out.print("(");
		n.lhs.accept(this);
		System.out.print(" - ");
		n.rhs.accept(this);
		System.out.print(")");
	}

	public void visitMul(Mul n)
	{
		System.out.print("(");
		n.lhs.accept(this);
		System.out.print(" * ");
		n.rhs.accept(this);
		System.out.print(")");
	}
	public void visitDiv(Div n)
	{
		System.out.print("(");
		n.lhs.accept(this);
		System.out.print(" / ");
		n.rhs.accept(this);
		System.out.print(")");
	}
	//////COMPARISON///////
	public void visitEqual(Equal n)
	{
		System.out.print("(");
		n.lhs.accept(this);
		System.out.print(" == ");
		n.rhs.accept(this);
		System.out.print(")");
	}
	public void visitNonEqual(NonEqual n)
	{
		System.out.print("(");
		n.lhs.accept(this);
		System.out.print(" != ");
		n.rhs.accept(this);
		System.out.print(")");
	}

	public void visitInf(Inf n)
	{
		if(n.lhs instanceof Chaine && n.rhs instanceof Chaine) {
			System.out.print("(");
			n.lhs.accept(this);
			System.out.print(".Length < ");
			n.rhs.accept(this);
			System.out.print(".Length)");
		} else {
			System.out.print("(");
			n.lhs.accept(this);
			System.out.print(" < ");
			n.rhs.accept(this);
			System.out.print(")");
		}
	}
	public void visitInfEqual(InfEqual n)
	{
		if(n.lhs instanceof Chaine && n.rhs instanceof Chaine) {
			System.out.print("(");
			n.lhs.accept(this);
			System.out.print(".Length <= ");
			n.rhs.accept(this);
			System.out.print(".Length)");
		} else {
			System.out.print("(");
			n.lhs.accept(this);
			System.out.print(" <= ");
			n.rhs.accept(this);
			System.out.print(")");
		}
	}

	public void visitSup(Sup n)
	{
		if(n.lhs instanceof Chaine && n.rhs instanceof Chaine) {
			System.out.print("(");
			n.lhs.accept(this);
			System.out.print(".Length > ");
			n.rhs.accept(this);
			System.out.print(".Length)");
		} else {
			System.out.print("(");
			n.lhs.accept(this);
			System.out.print(" > ");
			n.rhs.accept(this);
			System.out.print(")");
		}
	}
	public void visitSupEqual(SupEqual n)
	{
		if(n.lhs instanceof Chaine && n.rhs instanceof Chaine) {
			System.out.print("(");
			n.lhs.accept(this);
			System.out.print(".Length >= ");
			n.rhs.accept(this);
			System.out.print(".Length)");
		} else {
			System.out.print("(");
			n.lhs.accept(this);
			System.out.print(" >= ");
			n.rhs.accept(this);
			System.out.print(")");
		}
	}
	
	//////CONDITIONS///////
	public void visitIns(Ins n)
	{
		System.out.print("if(");
		n.cond.accept(this);
		System.out.print(") then (");
		n.expThen.accept(this);
		System.out.print(") else (");
		n.expElse.accept(this);
		System.out.print(")");
	}
	public void visitVariable(Variable n)
	{
		data.get(n.aVal).accept(this);
	}
	public void visitScope(Scope n)
	{
		data = n.data;
		System.out.println("let ");
		for (String entry : data.keySet()) {
			System.out.print("  ");
			System.out.print("var "+ entry + " := ");
			data.get(entry).accept(this);
			System.out.println();
		}
		System.out.println("in");

		for (Exp a : n._in) {
			System.out.print("  ");
			a.accept(this);
			System.out.println();
		}
		System.out.println("end");
	}

	////PRINT//////
	public void print(Exp e)
	{
		e.accept(this);
	}
}