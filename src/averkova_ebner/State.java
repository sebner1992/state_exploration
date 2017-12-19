package averkova_ebner;

public class State
{
    private int x;
    private int y;

    public State(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public State fillX(int xCapacity)
    {
        return new State(xCapacity, y);
    }

    public State fillY(int yCapacity)
    {
        return new State(x, yCapacity);
    }

    public State emptyX()
    {
        return new State(0, y);
    }

    public State emptyY()
    {
        return new State(x, 0);
    }

    public State moveFromXtoY(int xCapacity, int yCapacity)
    {
        return ((y + x) <= yCapacity) ? new State(0, (y + x)) : new State(x - (yCapacity - y), yCapacity);
    }

    public State moveFromYtoX(int xCapacity, int yCapacity)
    {
        return (x + y <= xCapacity) ? new State(x + y, 0) : new State(xCapacity, y - (xCapacity - x));
    }
}
