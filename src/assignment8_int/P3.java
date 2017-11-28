package assignment8_int;

import java.io.PrintStream;

/** Interface for Problem 2 */
public interface P3 {

	/**
	 * Solves problem 3 (two sand timers of given capacity; initial filling level is 0)
	 * 
	 * Example: System.out.println(p3.solve(7, 4, 9, System.out)); --> 6
	 * 
	 * @param xCapacity
	 *            the capacity of sand timer X
	 * @param yCapacity
	 *            the capacity of sand timer Y
	 * @param goal
	 *            the goal we want to achieve (time to measure)
	 * @param out
	 *            a printstream to write the path to the goal (should be a single line)
	 * @return the number of actions in the shortest path to the goal
	 */
	public int solve(int xCapacity, int yCapacity, int goal, PrintStream out);
}
