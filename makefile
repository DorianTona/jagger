JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
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
	Equal.java

default: classes

classes: $(CLASSES:.java=.class)
	java -cp javacc.jar javacc Jagger.jj
	javac -g Jagger.java

	
clean:
	$(RM) *.class
	$(RM) Jagger.java

test:
	java Jagger
	1
	-(1)
	1+1
	1-1
	