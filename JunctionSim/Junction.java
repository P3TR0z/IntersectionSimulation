package JunctionSim;

import static JunctionSim.Environment.HORIZONTAL_INITIAL_WEIGHT;
import static JunctionSim.Environment.HORIZONTAL_WIDTH;
import static JunctionSim.Environment.VERTICAL_INITIAL_WEIGHT;
import static JunctionSim.Environment.VERTICAL_WIDTH;

public class Junction
{
    // Every index in the AxisVectors represents a "lane". Every entry represents the numberOfCars waiting in that lane.
    public Axis verticalAxis;
    public Axis horizontalAxis;

    public Junction()
    {
        verticalAxis   = new Axis(VERTICAL_WIDTH, VERTICAL_INITIAL_WEIGHT);
        horizontalAxis = new Axis(HORIZONTAL_WIDTH, HORIZONTAL_INITIAL_WEIGHT);
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
