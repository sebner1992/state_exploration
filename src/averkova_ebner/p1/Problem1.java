package averkova_ebner.p1;

import java.io.PrintStream;
import java.util.Collection;

import assignment8_int.P1;
import averkova_ebner.PureWaterPouring;
import averkova_ebner.State;

public class Problem1 implements P1
{
    private PrintStream out;

    private void setPrintStream(PrintStream out)
    {
        this.out = out;
    }

    @Override
    public int solve(int xCapacity, int yCapacity, int goal, PrintStream out)
    {
        setPrintStream(out);
        PureWaterPouring pwp = new PureWaterPouring(xCapacity, yCapacity);
        Collection<?> solution = pwp.solve(goal);
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
            if (obj instanceof State)
            { //if obj is of type State
                State state = (State) obj;
                out.print(state.getX() + "," + state.getY() + " ");
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
        P1 p1 = new Problem1();
        int x = p1.solve(4, 9, 5, System.out);
    }

}
