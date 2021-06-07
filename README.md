Easy As ABC’ Hint System

The aim was to design, implement and document a hint system for the puzzle game 'Easy as ABC' using procedural and declarative AI logic algorithms as well as encodings of logic problems into satisfiability.

Starter code written by [Ian Gent](https://ipg.host.cs.st-andrews.ac.uk/).

### Compiling and Running Instructions

Navigate into the src directory:

```shell script
cd src
```

Set the CLASSPATH for LogicNG and ANTLR:

```shell script
export CLASSPATH=.:antlr-runtime-4.8.jar:logicng-2.0.2.jar:$CLASSPATH
```

Compile all `.java` files:

```shell script
javac Main.java */*.java
```

To run the application, your command should take the following form:

```shell script
java Main <TEST|SOLVE|HINT> <test-set|problem-to-solve|problem-get-hints-for>
```

For example, pass:

```shell script
java Main SOLVE VeryEasy
```

The output should be:

```

   ab  
   --
a| ab |b
b| ba |a
   --  
   ba  
```

### Dependencies
- [LogicNG](https://github.com/logic-ng/LogicNG): A Java Library for creating, manipulating and solving Boolean and Pseudo-Boolean formulas.
- [ANTLR](https://www.antlr.org/): (ANother Tool for Language Recognition) is a powerful parser generator for reading, processing, executing, or translating structured text or binary files.
- [JUnit 5](https://junit.org/junit5/): A unit testing framework for Java.