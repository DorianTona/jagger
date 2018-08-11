import java.util.ArrayList;
import java.util.HashMap;

public class PrettyPrinter extends Visitor
{
	private ArrayList<Scope> data = new ArrayList<Scope>();
	///////TYPES///////
	public void visit(Num n)
	{
		System.out.print(n.aVal);
	}
	public void visit(UnNeg n)
	{
		System.out.print("(-");
		n.aVal.accept(this);
		System.out.print(")");
	}
	public void visit(Chaine n)
	{
		System.out.print(n.aVal);
	}

	///////CALCUL///////
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
	
	//////CONDITIONS///////
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
		getInActiveScope(n.aVal).accept(this);
	}
	public void visit(Scope n)
	{
    	data = n.activeScopes;
		System.out.println("let ");
		for(Scope data : n.activeScopes){
			for (String entry : data.data.keySet()) {
				System.out.print("  ");
				System.out.print("var "+ entry + " := ");
				getInActiveScope(entry).accept(this);
				System.out.println();
			}
		}
		System.out.println("in");

		for (Exp a : n._in) {
			System.out.print("  ");
			a.accept(this);
			System.out.println();
		}
		System.out.println("end");
	}

	public void visit(Print n)
	{
		System.out.print("print(");
		n.aVal.accept(this);
		System.out.print(")");
	}

	////PRINT//////
	public void print(Exp e)
	{
		e.accept(this);
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