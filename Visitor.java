public abstract class Visitor
{
    ///////TYPES///////
    abstract void visit(Chaine n);
    abstract void visit(Num n);

    ///////CALCULS///////
    //unary
    abstract void visit(UnNeg n);
    //binary
    abstract void visit(Add n);
    abstract void visit(Sub n);
    abstract void visit(Mul n);
    abstract void visit(Div n);

    ///////COMPARISONS///////
    abstract void visit(Equal n);
    abstract void visit(NonEqual n);
    abstract void visit(Inf n);
    abstract void visit(Sup n);
    abstract void visit(SupEqual n);
    abstract void visit(InfEqual n);

    ///////KEYWORDS///////
    abstract void visit(Print n);
    abstract void visit(Ins n);
    abstract void visit(Variable n);
    abstract void visit(Scope n);
    abstract void visit(Assignment n);
    abstract void visit(Function n);
}