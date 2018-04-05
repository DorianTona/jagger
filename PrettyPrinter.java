public class PrettyPrinter extends Visitor
{
    public void visitNum(Num n)
    {
        System.out.print(n.aVal);
    }
    ////CALCUL//////////
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
        return;
    }
    public void visitDiv(Div n)
    {
        System.out.print("(");
        n.lhs.accept(this);
        System.out.print(" / ");
        n.rhs.accept(this);
        System.out.print(")");
        return;
    }
    //////COMPARAISON///////
    public void visitEqual(Equal n)
    {
        System.out.print("(");
        n.lhs.accept(this);
        System.out.print(" == ");
        n.rhs.accept(this);
        System.out.print(")");
        return;
    }
    public void visitNonEqual(NonEqual n)
    {
        System.out.print("(");
        n.lhs.accept(this);
        System.out.print(" != ");
        n.rhs.accept(this);
        System.out.print(")");
        return;
    }
    public void visitInf(Inf n)
    {
        System.out.print("(");
        n.lhs.accept(this);
        System.out.print(" < ");
        n.rhs.accept(this);
        System.out.print(")");
        return;
    }

    public void visitSup(Sup n)
    {
        System.out.print("(");
        n.lhs.accept(this);
        System.out.print(" > ");
        n.rhs.accept(this);
        System.out.print(")");
        return;
    }

    public void visitSupEqual(SupEqual n)
    {
        System.out.print("(");
        n.lhs.accept(this);
        System.out.print(" >= ");
        n.rhs.accept(this);
        System.out.print(")");
        return;
    }
    public void visitInfEqual(InfEqual n)
    {
        System.out.print("(");
        n.lhs.accept(this);
        System.out.print(" <= ");
        n.rhs.accept(this);
        System.out.print(")");
        return;
    }
    public void visitUnNeg(UnNeg n)
    {
        System.out.print("(-");
        n.aVal.accept(this);
        System.out.print(")");
        return; 
    }


    ////PRINT//////
    public void print(Exp e)
    {
        e.accept(this);
    }

}