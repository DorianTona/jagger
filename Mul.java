
/**
 * Write a description of class Mul here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Mul extends EXP
{
    public EXP lhs;
    public EXP rhs;
    public void accept(Visitor v)
    {
        v.visitMul(this);
    }
} // Mul
