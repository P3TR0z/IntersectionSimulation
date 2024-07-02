package JunctionSim;

import java.util.ArrayList;

public class Axis
{
    // Every index represents one lane moving along the Axis. Every entry represents the Weight (number of cars) on lane
    private ArrayList<Integer> lanes;
    public Axis(int width, int init)
    {
        lanes = new ArrayList<>(width);
        for (int i = 0; i < width; i++)
            lanes.add(init);
    }
    // returns number of lanes
    public int getWidth()
    {
        return lanes.size();
    }
    // sets weight on one lane. sets weight 0 if amount < 0; else sets amount; returns whatever it ended up setting
    public int setWeight(int index, int amount)
    {
        if (amount >= 0)
        {
            lanes.set(index, amount);
            return amount;
        }
        lanes.set(index, 0);
        return 0;
    }
    public int setWeight(int amount)
    {
        if (amount >= 0)
        {
            lanes.set(0, amount);
            return amount;
        }
        lanes.set(0, 0);
        return 0;
    }
    // returns weight on one lane
    public int getWeight(int index)
    {
        return lanes.get(index);
    }
    // returns weight across all lanes
    public int getWeight()
    {
        int ret = 0;
        for (int i : lanes)
            ret += i;
        return ret;
    }
}
