all: javacc classes
default: all

javacc:
	java -cp javacc.jar javacc Jagger.jj
	javac -g Jagger.java

classes:
	javac *.java

clean:
	$(RM) *.class
	$(RM) ParseException.java
	$(RM) SimpleCharStream.java
	$(RM) TokenMgrError.java
	$(RM) Token.java
	$(RM) JaggerConstants.java
	$(RM) JaggerTokenManager.java
	$(RM) Jagger.java

cl: all	#command-line
	java Main

check: all
	java TestCheck
	