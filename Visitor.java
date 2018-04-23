import java.util.ArrayList;
import java.util.HashMap;

public abstract class Visitor
{
    abstract void visitPrint (Print n);
    abstract void visitVariable (Variable n);
    abstract void visitScope (Scope n);

    abstract void visitIns(Ins n);

    abstract void visitEqual(Equal n);
    abstract void visitNonEqual(NonEqual n);
    abstract void visitInf(Inf n);
    abstract void visitSup(Sup n);
    abstract void visitSupEqual(SupEqual n);
    abstract void visitInfEqual(InfEqual n);

    abstract void visitAdd(Add n);
    abstract void visitSub(Sub n);

    abstract void visitMul(Mul n);
    abstract void visitDiv(Div n);
    
    abstract void visitUnNeg(UnNeg n);

    abstract void visitChaine(Chaine n);
    abstract void visitNum(Num n);
}