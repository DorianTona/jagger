JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Exp.java \
	Add.java \
	Sub.java \
	Mul.java \
	Div.java\
	Num.java \
	PrettyPrinter.java \
	Visitor.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class