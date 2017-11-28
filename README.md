# A8_starter

Assignment 8: State Exploration ('Framework')
----------------------------------------------

Take the provided implementation for the pouring problem (PureWaterPouring).

Come up with a good software architecture for it (also introduce new classes/types in order to increase the readability of the code). 
It shall support different problems (but of the same kind of course) with minimal compile-time adaptation.
The idea is that the core of the software should become a minimal framework. 
A new problem should require writing new classes, but not changing existing code (Open-Closed-Principle).

Put your classes into the package ``yourteam``.

Be sure that the framework-code (i.e., "the algorithm") does not depend on any problem-specific information. In particular not on the information that the state has integers.

Also implement the following problems/variations (each in a separate java-package). Use the provided interfaces in the ``assignment8_int`` package.
Make sure that the class that implements the interface works with the default-constructor:
E.g. the following code should work:
```java
P1 p1 = new yourteam.p1.ProblemWater();  //note the default constructor
int x = p1.solve(5, 9, 8, System.out);
```

Implement at least two problems: Problem1 is mandatory. Choose between Problem2 and Problem3.

Problem1: (Package: ``yourteam.p1``, implement interface ``P1``)
---------
The original pouring problem.

Problem2: (Package: ``yourteam.p2``, implement interface ``P2``)
---------
The pouring problem, but with three canisters, each with a configurable initial filling level. 
Only the following actions between the canisters should be allowed:
``X->Y, Y->Z, Z->X;``
Also, there is no faucet (no water source).
So, only the water of the initial states is available.

Problem3 [german]: (Package: ``yourteam.p3``, implement interface ``P3``)
------------------
Sie haben zwei Sanduhren (‚left‘ und ‚right‘), 
von denen eine in X Minuten und die andere in Y Minuten durchläuft. 
Wie lassen sich hiermit genau Z Minuten messen?

Eine Aktion kann nur in dem Moment durchgeführt werden, 
wenn bei einer der Uhren der gesamte Sand durchgeronnen ist. 
Es ist durchaus möglich, nur die linke Uhr umzudrehen 
(obwohl sie noch nicht ganz durchgelaufen ist), 
sobald die rechte Uhr komplett durchgelaufen ist.

Hinweis: Zu Beginn ist in beiden Sanduhren der ganze Sand in der unteren Hälfte.
```
Beispiel: X=7, Y=4, Z=9

Output: //Aktuelle Zeit, Aktion, Füllstand links, Füllstand rechts (nach der Aktion)
0, turn left, 7, 0
0, turn right, 7, 4   //zum Zeitpunkt 4 wird der ganze Sand der rechten Uhr durchgelaufen sein -> Neue Aktion 
4, turn right, 3, 4
7, turn left, 7, 1
8, turn left, 1, 0
8, turn right, 1, 4     //diese Aktion wäre nicht notwendig
9 minutes elapsed!
``` 


Notes
-----

Note that the current code uses a ``HashSet`` and calls its ``contains()``-method. The javadoc of ``HashSet.contains()`` states: "Returns true if this collection contains the specified element. More formally, returns true if and only if this collection contains at least one element e such that ``(o==null ? e==null : o.equals(e))``."
So if you replace the ``int[]`` that is put into the hashset with your own class (e.g. ``MyState``), make sure that your ``equals()``-method works as intended. If not overriden, the ``equals()``-method is inherited from the ``Object`` class (which checks whether the two objects are the same, i.e., share the same memory location). Intuitively, two state objects for Problem1, e.g., are equal, if their x- and y- integer values are the same.

Further note, that the javadoc of the ``Object.equals()`` method says:
"Note that it is generally necessary to override the ``hashCode`` method whenever this method is overridden, so as to maintain the general contract for the ``hashCode`` method, which states that equal objects must have equal hash codes."

Think about other problems that can be solved with the framework.
