package assignment8_int;

import java.io.PrintStream;

/** Interface for Problem 2 */
public interface P2 {

	/**
	 * Solves problem 2 (three canisters of given capacity and initial filling level)
	 * 
	 * Example: System.out.println(p2.solve(5, 3, 9, 0, 1, 8, 4, System.out)); --> 2
	 * 
	 * @param xCapacity
	 *            the capacity of canister X
	 * @param yCapacity
	 *            the capacity of canister Y
	 * @param zCapacity
	 *            the capacity of canister Z
	 * @param xInit
	 *            the initial filling of canister X
	 * @param yInit
	 *            the initial filling of canister Y
	 * @param zInit
	 *            the initial filling of canister Z
	 * @param goal
	 *            the goal we want to achieve
	 * @param out
	 *            a printstream to write the path to the goal (should be a single line)
	 * @return the number of actions in the shortest path to the goal
	 */
	public int solve(int xCapacity, int yCapacity, int zCapacity, int xInit, int yInit, int zInit, int goal, PrintStream out);
}
