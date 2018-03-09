
/**
 * Write a description of class Add here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Add extends EXP{
    public EXP lhs;
    public EXP rhs;
    public void accept(Visitor v)
    {
        v.visitAdd(this);
    }

} // Add
