public abstract class Visitor
{
    abstract void visitNum(Num n);
    abstract void visitAdd(Add n);
    abstract void visitSub(Sub n);
    abstract void visitMul(Mul n);
    abstract void visitDiv(Div n);

    abstract void visitEqual(Equal n);
    abstract void visitNonEqual(NonEqual n);
    abstract void visitInf(Inf n);
    abstract void visitSup(Sup n);
    abstract void visitSupEqual(SupEqual n);
    abstract void visitInfEqual(InfEqual n);
    
    abstract void visitUnNeg(UnNeg n);
}