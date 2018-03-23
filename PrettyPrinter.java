public class PrettyPrinter extends Visitor
{
    public void visitAdd(Add n){
        System.out.print("(");
        n.lhs.accept(this);
        System.out.print("+");
        n.rhs.accept(this);
        System.out.println(")");
    }
    public void visitSub(Sub n){
        System.out.print("(");
        n.lhs.accept(this);
        System.out.print("-");
        n.rhs.accept(this);
        System.out.println(")");
    }

    public void visitNum(Num n)
    {
        System.out.println(n.aVal);
    }

    public void visitMul(Mul n)
    {
        System.out.print("(");
        n.lhs.accept(this);
        System.out.print("*");
        n.rhs.accept(this);
        System.out.println(")");
        return;
    }
    public void visitDiv(Div n)
    {
        System.out.print("(");
        n.lhs.accept(this);
        System.out.print("/");
        n.rhs.accept(this);
        System.out.println(")");
        return;
    }
}