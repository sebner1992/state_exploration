package test;

import assignment8_int.P1;
import assignment8_int.P2;

public class Test
{

    public static void main(String[] args)
    {
        P1 p1 = new averkova_ebner.p1.Problem1(); //note the default constructor
        int x = p1.solve(4, 9, 5, System.out);

        System.out.println("-----------------------------------------");

        P2 p2 = new averkova_ebner.p2.Problem2();
        int y = p2.solve(2, 4, 6, 1, 2, 3, 6, System.out);
    }

}
