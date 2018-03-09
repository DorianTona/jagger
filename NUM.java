
/**
 * Write a description of class NUM here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NUM extends EXP
{
    // instance variables - replace the example below with your own
    public int aVal;

    /**
     * Default constructor for objects of class NUM
     */
    public NUM(int pVal)
    {
        aVal = pVal;
    } // NUM()
    public void accept(Visitor v)
    {
        v.visitNum(this);
    }
} // NUM
