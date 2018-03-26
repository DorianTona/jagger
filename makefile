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
	UnNeg.java \
	Equal.java \
	NonEqual.java \
	Sup.java \
	SupEqual.java \
	Inf.java \
	InfEqual.java \
	PrettyPrinter.java \
	Visitor.java 

default: classes

classes: $(CLASSES:.java=.class)
	java -cp javacc.jar javacc Jagger.jj
	javac -g Jagger.java

	
clean:
	$(RM) *.class
	$(RM) Jagger.java