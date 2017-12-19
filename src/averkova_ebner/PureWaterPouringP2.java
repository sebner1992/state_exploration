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

public class PureWaterPouringP2
{
    private final int xCapacity;
    private final int yCapacity;
    private final int zCapacity;

    private Set<StateP2> explored;
    LinkedList<LinkedList<?>> frontier;
    private StateP2 initState;

    public PureWaterPouringP2(int xCapacity, int yCapacity, int zCapacity, int xInit, int yInit, int zInit)
    {
        this.xCapacity = xCapacity;
        this.yCapacity = yCapacity;
        this.zCapacity = zCapacity;
        explored = new HashSet<>();
        frontier = new LinkedList<>();
        initState = new StateP2(xInit, yInit, zInit);
        frontier.push(new LinkedList<>(Arrays.asList(initState)));
    }

    private boolean containsGoal(StateP2 state, int goal)
    {
        return state.getX() == goal || state.getY() == goal || state.getZ() == goal;
    }

    private boolean isAlreadyExplored(StateP2 state)
    {
        for (StateP2 entry : explored)
        {
            if (entry.getX() == state.getX() && entry.getY() == state.getY() && entry.getZ() == entry.getZ())
            {
                return true;
            }
        }
        return false;
    }

    public Collection<?> solve(int goal)
    {
        if (containsGoal(initState, goal))
        {
            return Arrays.asList(initState);
        }

        Map<StateP2, Action> successorsMap;
        LinkedList<?> path;
        StateP2 state;

        while (!frontier.isEmpty())
        {
            path = frontier.pop();
            state = (StateP2) path.getLast();
            successorsMap = getSuccessors(state);

            for (Entry<StateP2, Action> e : successorsMap.entrySet())
            {
                StateP2 succState = e.getKey();
                Action succAction = e.getValue();
                if (!isAlreadyExplored(succState))
                {
                    explored.add(succState);
                    LinkedList<Object> path2 = new LinkedList<>(path); // copy path
                    path2.addLast(succAction);
                    path2.addLast(succState);
                    if (containsGoal(succState, goal))
                    {
                        return path2;
                    }
                    else
                    {
                        frontier.add(path2);
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private Map<StateP2, Action> getSuccessors(StateP2 state)
    {
        assert (state.getX() <= xCapacity && state.getY() <= yCapacity && state.getZ() <= zCapacity);

        Map<StateP2, Action> successors = new HashMap<>();
        successors.put(state.moveFromXtoY(xCapacity, yCapacity), Action.from_X_to_Y);
        successors.put(state.moveFromYtoZ(yCapacity, zCapacity), Action.from_Y_to_Z);
        successors.put(state.moveFromZtoX(zCapacity, xCapacity), Action.from_Z_to_X);

        return successors;
    }

    private enum Action
    {
        from_X_to_Y,
        from_Y_to_Z,
        from_Z_to_X;
    }
}
