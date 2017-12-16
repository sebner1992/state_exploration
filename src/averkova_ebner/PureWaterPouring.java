package averkova_ebner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PureWaterPouring {
	private final int xCapacity;
	private final int yCapacity;
	private Set<State> explored;
	LinkedList<LinkedList<?>> frontier;
	private State initState = new State(0, 0);

	public PureWaterPouring(int xCapacity, int yCapacity) {
		this.xCapacity = xCapacity;
		this.yCapacity = yCapacity;
		explored = new HashSet<>();
		frontier = new LinkedList<>();
		frontier.push(new LinkedList<>(Arrays.asList(initState)));
	}

	private boolean containsGoal(State state, int goal) {
		return state.getX() == goal || state.getY() == goal;
	}

	private boolean isAlreadyExplored(State state) {
		for (State entry : explored) {
			if (entry.getX() == state.getX() && entry.getY() == state.getY()) {
				return true;
			}
		}
		return false;
	}

	public Collection<?> solve(int goal) {
		if (containsGoal(initState, goal)) {
			return Arrays.asList(initState);
		}
		/* note: not only the states are relevant, we also want to know, how we got there -> consider Move */		
		Map<State, Action> successorsMap;
		LinkedList<?> path;
		State state;		
		while (!frontier.isEmpty()) {			
			path = frontier.pop();
			state = (State) path.getLast(); // state of last path
			successorsMap = getSuccessors(state);

			for (Entry<State, Action> e : successorsMap.entrySet()) {
				State succState = e.getKey();
				Action succAction = e.getValue();
				if (!isAlreadyExplored(succState)) {
					explored.add(succState);
					LinkedList<Object> path2 = new LinkedList<>(path); // copy path
					path2.addLast(succAction);
					path2.addLast(succState);
					if (containsGoal(succState, goal)) {
						return path2;
					} else {
						frontier.add(path2);
					}
				}
			}
		}
		return Collections.emptyList();
	}

	private Map<State, Action> getSuccessors(State state) {
		assert (state.getX() <= xCapacity && state.getY() <= yCapacity);
		Map<State, Action> successors = new HashMap<>();

		successors.put(state.moveFromXtoY(xCapacity, yCapacity), Action.from_X_to_Y);
		successors.put(state.moveFromYtoX(xCapacity, yCapacity), Action.from_Y_to_X);
		successors.put(state.fillX(xCapacity), Action.fill_X);
		successors.put(state.fillY(yCapacity), Action.fill_Y);
		successors.put(state.emptyX(), Action.empty_X);
		successors.put(state.emptyY(), Action.empty_Y);

		return successors;
	}

	private enum Action{
		from_X_to_Y, from_Y_to_X, fill_X, fill_Y, empty_X, empty_Y
	}

	public static void main(String[] args) {
		PureWaterPouring wp = new PureWaterPouring(4, 9);
		Collection<?> solution = wp.solve(5);
		if (solution.size() > 0) {
			for (Object o : solution) {
				if (o instanceof State) {
					State state = (State) o;
					System.out.print(state.getX() + "," + state.getY() + " ");
				} else {
					System.out.print(o.toString() + " ");
				}
			}
			System.out.println();
		} else {
			System.out.println("no solution");
		}
	}
}


/* original python code (p.norvig):


import doctest

def pour_problem(X, Y, goal, start = (0, 0)):
    """X and Y are the capacity of glasses; (x,y) is current fill levels and
    represent a state. The goal is a level that can be in either glass. Start at
    start state and follow successors until we reach the goal. Keep track of
    frontier and previously explored; fail when no frontier."""
    if goal in start:
        return [start]
    explored = set() # set the states we have visited
    frontier = [ [start] ] # ordered list of paths we have blazed
    while frontier:
        path = frontier.pop(0)
        (x, y) = path[-1] # Last state in the first path of the frontier
        for (state, action) in successors(x, y, X, Y).items():
            if state not in explored:
                explored.add(state)
                path2 = path + [action, state]
                if goal in state:
                    return path2
                else:
                    frontier.append(path2)
    return Fail
Fail = []

def successors(x, y, X, Y):
    """Return a dict of {state:action} pairs describing what can be reached from
    the (x, y) state and how."""
    assert x <= X and y <= Y ## (x, y) is glass levels; X and Y are glass sizes
    return {((0, y+x) if y+x <= Y else (x-(Y-y), y+(Y-y))): 'X->Y',
            ((x+y, 0) if x+y <= X else (x+(X-x), y-(X-x))): 'X<-Y',
            (X, y): 'fill X',
            (x, Y): 'fill Y',
            (0, y): 'empty X',
            (x, 0): 'empty Y'
            }

class Test:
    """
    >>> successors(0, 0, 4, 9)
    {(0, 9): 'fill Y', (0, 0): 'empty Y', (4, 0): 'fill X'}

    >>> successors(3, 5, 4, 9)
    {(4, 5): 'fill X', (4, 4): 'X<-Y', (3, 0): 'empty Y', (3, 9): 'fill Y', (0, 5): 'empty X', (0, 8): 'X->Y'}

    >>> successors(3, 7, 4, 9)
    {(4, 7): 'fill X', (4, 6): 'X<-Y', (3, 0): 'empty Y', (0, 7): 'empty X', (3, 9): 'fill Y', (1, 9): 'X->Y'}

    >>> pour_problem(4, 9, 6)
    [(0, 0), 'fill Y', (0, 9), 'X<-Y', (4, 5), 'empty X', (0, 5), 'X<-Y', (4, 1), 'empty X', (0, 1), 'X<-Y', (1, 0), 'fill Y', (1, 9), 'X<-Y', (4, 6)]

    ## What problem, with X, Y, and goal < 10 has the longest solution?
    ## Answer: pour_problem(7, 9, 8) with 14 steps.

    >>> def num_actions(triplet): X, Y, goal = triplet; return len(pour_problem(X, Y, goal)) / 2

    >>> def hardness(triplet): X, Y, goal = triplet; return num_actions((X, Y, goal)) - max(X, Y)
    >>> max([(X, Y, goal) for X in range(1, 10) for Y in range(1, 10)
    ...                   for goal in range(1, max(X, Y))], key = num_actions)
    (7, 9, 8)

    >>> max([(X, Y, goal) for X in range(1, 10) for Y in range(1, 10)
    ...                   for goal in range(1, max(X, Y))], key = hardness)
    (7, 9, 8)

    >>> pour_problem(7, 9, 8)
    [(0, 0), 'fill Y', (0, 9), 'X<-Y', (7, 2), 'empty X', (0, 2), 'X<-Y', (2, 0), 'fill Y', (2, 9), 'X<-Y', (7, 4), 'empty X', (0, 4), 'X<-Y', (4, 0), 'fill Y', (4, 9), 'X<-Y', (7, 6), 'empty X', (0, 6), 'X<-Y', (6, 0), 'fill Y', (6, 9), 'X<-Y', (7, 8)]
    """

print(doctest.testmod())
# TestResults(failed=0, attempted=9)




 */
