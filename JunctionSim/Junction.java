package JunctionSim;

public class Junction
{
    // Every index in the AxisVectors represents a "lane". Every entry represents the numberOfCars waiting in that lane.
    public Axis verticalAxis;
    public Axis horizontalAxis;

    public Junction(int verticalWidth, int horizontalWidth)
    {
        verticalAxis   = new Axis(verticalWidth, 0);
        horizontalAxis = new Axis(horizontalWidth, 0);
    }
    public Junction(int verticalWidth, int horizontalWidth, int init)
    {
        verticalAxis   = new Axis(verticalWidth, init);
        horizontalAxis = new Axis(horizontalWidth, init);
    }
    public int getDensity()
    {
        return verticalAxis.getWeight() + horizontalAxis.getWeight();
    }
    // returns vertical - horizontal
    public int getDifference()
    {
        return verticalAxis.getWeight() - horizontalAxis.getWeight();
    }
    // returns the difference between the two lanes as a percentage of verticalWeight. Positive => vertical is greater;
    public int getDifferenceAsPercentage()
    {
        if (verticalAxis.getWeight() == 0)
            return -1000; // absurd value so it gives the opposite lane the whole cycle
        return ((verticalAxis.getWeight() - horizontalAxis.getWeight()) / verticalAxis.getWeight()) * 100;
    }
}
