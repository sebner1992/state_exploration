package assignment8_int;

import java.io.PrintStream;

/** Interface for Problem 1 */
public interface P1 {

	/**
	 * Solves problem 1 (two empty canisters of given capacity)
	 * 
	 * Example: System.out.println(p1.solve(5, 9, 8, System.out)); --> 6
	 * 
	 * @param xCapacity
	 *            the capacity of canister X
	 * @param yCapacity
	 *            the capacity of canister Y
	 * @param goal
	 *            the goal we want to achieve
	 * @param out
	 *            a printstream to write the path to the goal (should be a single line)
	 * @return the number of actions in the shortest path to the goal
	 */
	public int solve(int xCapacity, int yCapacity, int goal, PrintStream out);
}
