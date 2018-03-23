public abstract class Visitor
{
    abstract void visitAdd(Add n);
    abstract void visitSub(Sub n);
    abstract void visitNum(Num n);
    abstract void visitMul(Mul n);
    abstract void visitDiv(Div n);
}