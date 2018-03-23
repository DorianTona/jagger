JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Add.java \
	EXP.java \
	Mul.java \
	NUM.java \
	PrettyPrinter.java \
	Visitor.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class