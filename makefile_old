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
	Equal.java \
	Ins.java \
	Chaine.java \
	Variable.java \
	Scope.java

default: classes

classes: $(CLASSES:.java=.class) javacc
	java -cp javacc.jar javacc Jagger.jj
	javac -g Jagger.java
	javac Main.java
	java Main

javacc: Jagger.jj
	java -cp javacc.jar javacc Jagger.jj
	javac -g Jagger.java
	
clean:
	$(RM) *.class
	$(RM) Jagger.java

check: $(CLASSES:.java=.class) javacc
	javac -g TestCheck.java
	javac -g RunCheck.java
	java -cp javacc.jar javacc Jagger.jj
	javac -g Jagger.java
	java TestCheck
	