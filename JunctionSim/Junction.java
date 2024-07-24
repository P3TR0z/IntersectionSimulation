package JunctionSim;

import java.util.concurrent.ConcurrentHashMap;

import static JunctionSim.Environment.HORIZONTAL_INITIAL_WEIGHT;
import static JunctionSim.Environment.HORIZONTAL_WIDTH;
import static JunctionSim.Environment.VERTICAL_INITIAL_WEIGHT;
import static JunctionSim.Environment.VERTICAL_WIDTH;

public class Junction
{
    protected ConcurrentHashMap<Integer, Integer> verticalAxis;
    protected ConcurrentHashMap<Integer, Integer> horizontalAxis;
    // Every index in the AxisVectors represents a "lane". Every entry represents the numberOfCars waiting in that lane.

    public Junction()
    {
        verticalAxis = new ConcurrentHashMap<>();
        horizontalAxis = new ConcurrentHashMap<>();
        for (int i = 0; i < VERTICAL_WIDTH; i++)
            verticalAxis.put(i, VERTICAL_INITIAL_WEIGHT);
        for (int i = 0; i < HORIZONTAL_WIDTH; i++)
            horizontalAxis.put(i, HORIZONTAL_INITIAL_WEIGHT);
    }
    public int setWeight(ConcurrentHashMap<Integer, Integer> axis, int index, int value)
    {
        if (value >= 0)
        {
            axis.replace(index, value);
            return value;
        }
        axis.replace(index, 0);
        return 0;
    }
    public int getWidth(ConcurrentHashMap<Integer, Integer> axis)
    {
        return axis.size();
    }
    // increments the given lane by the given amount
    public void increment(ConcurrentHashMap<Integer, Integer> axis, int index, int amount)
    {
        axis.replace(index, axis.get(index) + amount);
    }
    // increments every lane on the given axis by the given amount
    public void increment(ConcurrentHashMap<Integer, Integer> axis, int amount)
    {
        for (int i = 0; i < axis.size(); i++)
        {
            axis.replace(i, axis.get(i) + amount);
        }
    }
    public int getWeight(ConcurrentHashMap<Integer, Integer> axis, int index)
    {
        return axis.get(index);
    }
    // returns combined weight of all lanes on given axis
    public int getWeight(ConcurrentHashMap<Integer, Integer> axis)
    {
        int sum = 0;
        for (int i = 0; i < axis.size(); i++)
            sum += axis.get(i);
        return sum;
    }
    public int getDensity()
    {
        return getWeight(verticalAxis) + getWeight(horizontalAxis);
    }
    // returns vertical - horizontal
    public int getDifference()
    {
        return getWeight(verticalAxis) - getWeight(horizontalAxis);
    }
    // returns the difference between the two lanes as a percentage of verticalWeight. Positive => vertical is greater;
    public int getDifferenceAsPercentage()
    {
        if (getWeight(verticalAxis) == 0)
            return -1000; // absurd value so it gives the opposite lane the whole cycle
        return ((getWeight(verticalAxis) - getWeight(horizontalAxis)) / getWeight(verticalAxis)) * 100;
    }
}
