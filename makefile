JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Main.java \
	TestCheck.java \
	RunCheck.java \
	Exp.java \
	Num.java \
	Add.java \
	Sub.java \
	Mul.java \
	Div.java \
	UnOp.java \
	UnNeg.java \
	Equal.java \
	NonEqual.java \
	Sup.java \
	SupEqual.java \
	Inf.java \
	InfEqual.java \
	PrettyPrinter.java \
	Visitor.java \
	Equal.java \
	Ins.java \
	Chaine.java \
	Variable.java \
	Scope.java

default: classes

classes: $(CLASSES:.java=.class)
	java -cp javacc.jar javacc Jagger.jj
	javac -g Jagger.java
	java Main
	
clean:
	$(RM) *.class
	$(RM) Jagger.java

check: $(CLASSES:.java=.class)
	java -cp javacc.jar javacc Jagger.jj
	javac -g Jagger.java
	java TestCheck
	