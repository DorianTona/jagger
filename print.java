public class Print extends Exp
{
    public Print() { }
    
    public void eval(Exp e)
    {
    	System.out.println(e.accept(new PrettyPrinter()).toDouble());
    }
}