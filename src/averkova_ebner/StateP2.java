package averkova_ebner;

public class StateP2
{
    private int x;
    private int y;
    private int z;

    public StateP2(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getZ()
    {
        return z;
    }

    public StateP2 moveFromXtoY(int xCapacity, int yCapacity)
    {
        return ((y + x) <= yCapacity) ? new StateP2(0, (y + x), z) : new StateP2(x - (yCapacity - y), yCapacity, z);
    }

    public StateP2 moveFromYtoZ(int yCapacity, int zCapacity)
    {
        return ((z + y) <= zCapacity) ? new StateP2(x, 0, (z + y)) : new StateP2(x, y - (zCapacity - z), zCapacity);
    }

    public StateP2 moveFromZtoX(int zCapacity, int xCapacity)
    {
        return ((x + z) <= xCapacity) ? new StateP2(0, y, (x + z)) : new StateP2(z - (xCapacity - x), y, xCapacity);
    }

}
