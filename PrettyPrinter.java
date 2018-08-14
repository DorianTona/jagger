import java.util.ArrayList;
import java.util.HashMap;

public class PrettyPrinter extends Visitor
{
    private Scope scope;
    private int nbOfScope;

	//constructor
	public PrettyPrinter(Exp e)
	{
		e.accept(this);
	}
    public void setScope(Scope s) { scope = s; }
    public void actualizeVisitorScope(Scope s) { scope.setData(s.getData()); }

	///////TYPES///////
	public void visit(Num n)
	{
		System.out.print(n.aVal);
	}
	public void visit(Chaine n)
	{
		System.out.print(n.aVal);
	}

	///////CALCUL///////
	//unary
	public void visit(UnNeg n)
	{
		System.out.print("(-");
		n.aVal.accept(this);
		System.out.print(")");
	}
	//binary
	public void visit(Add n){
		System.out.print("(");
		n.lhs.accept(this);
		System.out.print(" + ");
		n.rhs.accept(this);
		System.out.print(")");
	}
	public void visit(Sub n){
		System.out.print("(");
		n.lhs.accept(this);
		System.out.print(" - ");
		n.rhs.accept(this);
		System.out.print(")");
	}

	public void visit(Mul n)
	{
		System.out.print("(");
		n.lhs.accept(this);
		System.out.print(" * ");
		n.rhs.accept(this);
		System.out.print(")");
	}
	public void visit(Div n)
	{
		System.out.print("(");
		n.lhs.accept(this);
		System.out.print(" / ");
		n.rhs.accept(this);
		System.out.print(")");
	}
	
	//////COMPARISON///////
	public void visit(Equal n)
	{
		System.out.print("(");
		n.lhs.accept(this);
		System.out.print(" == ");
		n.rhs.accept(this);
		System.out.print(")");
	}
	public void visit(NonEqual n)
	{
		System.out.print("(");
		n.lhs.accept(this);
		System.out.print(" != ");
		n.rhs.accept(this);
		System.out.print(")");
	}

	public void visit(Inf n)
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
	public void visit(InfEqual n)
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

	public void visit(Sup n)
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
	public void visit(SupEqual n)
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
	
	//////KEYWORDS///////
	public void visit(Print n)
	{
		System.out.print("print(");
		n.aVal.accept(this);
		System.out.print(")");
	}
	public void visit(Ins n)
	{
		System.out.print("if(");
		n.cond.accept(this);
		System.out.print(") then (");
		n.expThen.accept(this);
		System.out.print(") else (");
		n.expElse.accept(this);
		System.out.print(")");
	}
	public void visit(Variable n)
	{
		System.out.print(n.name);
	}
	public void visit(Scope s)
	{
		nbOfScope++;
		HashMap<String, Exp> data = scope.getData();
		System.out.println("let");
		for (String entry : data.keySet()) {
			indentScope(nbOfScope); System.out.print("var "+ entry + " := ");
			scope.getInScope(entry).accept(this);
			System.out.println();
		}

		ArrayList<Exp> ins = scope.getInstructions();
		indentScope(nbOfScope-1); System.out.println("in");
		for (Exp a : ins) {
			indentScope(nbOfScope); a.accept(this);
			System.out.println();
		}
		indentScope(nbOfScope-1); System.out.println("end");
		nbOfScope--;
	}
	public void visit(Assignment a)
	{
		System.out.print(a.vName);
		System.out.print(" := ");
		a.exp.accept(this);
	}

    ///////UTILITAIRES///////

    public void indentScope(int n)
    {
    	for(int i=0 ; i<n ; i++){
    		System.out.print("  ");
    	}
    }
}