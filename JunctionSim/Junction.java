package JunctionSim;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static JunctionSim.Environment.EAST_WIDTH;
import static JunctionSim.Environment.HORIZONTAL_INITIAL_WEIGHT;
import static JunctionSim.Environment.HORIZONTAL_WIDTH;
import static JunctionSim.Environment.NORTH_WIDTH;
import static JunctionSim.Environment.SOUTH_WIDTH;
import static JunctionSim.Environment.VERTICAL_INITIAL_WEIGHT;
import static JunctionSim.Environment.VERTICAL_WIDTH;
import static JunctionSim.Environment.WEST_WIDTH;

public class Junction
{
    // Every index in the AxisVectors represents a "lane". Every entry represents the numberOfCars waiting in that lane.
    // a cardinal direction represents lanes / cars heading IN that direction.
    // A car heading NORTH in an intersection will still be on the NORTH lanes upon arriving in its respective North Connection (NOT on southDirection lanes)
    protected ConcurrentHashMap<Integer, Integer> northDirection;
    protected ConcurrentHashMap<Integer, Integer> southDirection;
    protected ConcurrentHashMap<Integer, Integer> eastDirection;
    protected ConcurrentHashMap<Integer, Integer> westDirection;

    public Junction()
    {
        northDirection = new ConcurrentHashMap<>();
        southDirection = new ConcurrentHashMap<>();
        eastDirection = new ConcurrentHashMap<>();
        westDirection = new ConcurrentHashMap<>();

        for (int i = 0; i < NORTH_WIDTH; i++)
            northDirection.put(i, VERTICAL_INITIAL_WEIGHT);
        for (int i = 0; i < SOUTH_WIDTH; i++)
            southDirection.put(i, VERTICAL_INITIAL_WEIGHT);
        for (int i = 0; i < EAST_WIDTH; i++)
            eastDirection.put(i, HORIZONTAL_INITIAL_WEIGHT);
        for (int i = 0; i < WEST_WIDTH; i++)
            westDirection.put(i, HORIZONTAL_INITIAL_WEIGHT);
    }

    public void setWeight(ConcurrentHashMap<Integer, Integer> axis, int index, int value)
    {
        if (value > 0)
        {
            axis.replace(index, value);
            return;
        }
        axis.replace(index, 0);
    }

    public int getWidth(ConcurrentHashMap<Integer, Integer> axis)
    {
        return axis.size();
    }
    public int getVerticalWidth()
    {
        return northDirection.size() + southDirection.size();
    }
    public int getHorizontalWidth()
    {
        return eastDirection.size() + westDirection.size();
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

    public int getVerticalWeight()
    {
        return getWeight(northDirection) + getWeight(southDirection);
    }

    public int getHorizontalWeight()
    {
        return getWeight(eastDirection) + getWeight(westDirection);
    }

    public int getDensity()
    {
        return getWeight(northDirection) + getWeight(southDirection) + getWeight(eastDirection) + getWeight(southDirection);
    }
}