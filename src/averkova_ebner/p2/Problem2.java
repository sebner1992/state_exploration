package averkova_ebner.p2;

import java.io.PrintStream;
import java.util.Collection;

import assignment8_int.P2;
import averkova_ebner.PureWaterPouringP2;
import averkova_ebner.StateP2;

public class Problem2 implements P2
{
    private PrintStream out;

    private void setPrintStream(PrintStream out)
    {
        this.out = out;
    }

    @Override
    public int solve(int xCapacity, int yCapacity, int zCapacity, int xInit, int yInit, int zInit, int goal,
        PrintStream out)
    {
        setPrintStream(out);
        PureWaterPouringP2 pwpP2 = new PureWaterPouringP2(xCapacity, yCapacity, zCapacity, xInit, yInit, zInit);
        Collection<?> solution = pwpP2.solve(goal);
        printSteps(solution);
        return solution.size() / 2;
    }

    private void printSteps(Collection<?> solution)
    {
        if (solution.isEmpty())
        {
            out.println("No solution");
            out.println("Number of actions: 0");
            return;
        }

        for (Object obj : solution)
        {
            if (obj instanceof StateP2)
            { //if obj is of type State
                StateP2 state = (StateP2) obj;
                out.print(state.getX() + "," + state.getY() + "," + state.getZ() + " ");
            }
            else
            {
                out.print(obj.toString() + " ");
            }
        }
        out.println();
        out.println("Number of actions: " + solution.size() / 2);

    }

    public static void main(String[] args)
    {
        P2 p2 = new Problem2();
        int x = p2.solve(2, 4, 6, 1, 2, 3, 6, System.out);
    }

}
