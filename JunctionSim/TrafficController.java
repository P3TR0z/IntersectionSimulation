package JunctionSim;


import java.util.concurrent.ConcurrentHashMap;

import static JunctionSim.Environment.CAR_DELAY;
import static JunctionSim.Environment.CAR_SPAWN_DELAY;
import static JunctionSim.Environment.CAR_SPEED;


public class TrafficController
{
    public Junction junction;
    private static ConcurrentHashMap<Integer, Integer> previous = null;
    TrafficController()
    {
        junction = new Junction();
    }
    // computes how many cars pass in the allocated time, removes them from the stack and returns the sum over all lanes
    public int removeCarsWithDelay(ConcurrentHashMap<Integer, Integer> axis, int time)
    {
        int sum = 0;
        for (int i = 0; i < axis.size(); i++)
        {
            int initialWeight = junction.getWeight(axis, i);
            sum = sum + initialWeight - junction.setWeight(axis, i, initialWeight - (1 + (time - CAR_SPEED) / CAR_DELAY));
        }
        return sum;
    }
    public int removeCarsNoDelay(ConcurrentHashMap<Integer, Integer> axis, int time)
    {
        int sum = 0;
        for (int i = 0; i < axis.size(); i++)
        {
            int initialWeight = junction.getWeight(axis, i);
            sum = sum + initialWeight - junction.setWeight(axis, i, initialWeight - (time / CAR_DELAY));
        }
        return sum;
    }
    public int removeCars(ConcurrentHashMap<Integer, Integer> axis, int time)
    {
        if (previous == axis)
        {
            System.out.println("here");
            return removeCarsNoDelay(axis, time);
        }
        previous = axis;
        return removeCarsWithDelay(axis, time);
    }
    public int spawnCars(ConcurrentHashMap<Integer, Integer> axis, int time)
    {
        int sum = 0;
        for (int i = 0; i < axis.size(); i++)
        {
            sum += time / CAR_SPAWN_DELAY;
            junction.setWeight(axis, i, junction.getWeight(axis, i) + time / CAR_SPAWN_DELAY);
        }
        return sum;
    }
}
