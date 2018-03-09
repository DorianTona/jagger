
/**
 * Write a description of class PrettyPrinter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PrettyPrinter extends Visitor
{
    // instance variables - replace the example below with your own
    public void visitAdd(Add n){
        System.out.print("(");
        n.lhs.accept(this);
        System.out.print("+");
        n.rhs.accept(this);
        System.out.println(")");
    }

    public void visitNum(NUM n)
    {
        System.out.println(n.aVal);
    }
    public void visitMul(Mul n)
    {
        return;
    }
} // PrettyPrinter
