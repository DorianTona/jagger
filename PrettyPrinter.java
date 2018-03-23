public class PrettyPrinter extends Visitor
{
    public void visitNum(Num n)
    {
        System.out.print(n.aVal);
    }

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

    public void print(Exp e)
    {
        e.accept(this);
    }
}