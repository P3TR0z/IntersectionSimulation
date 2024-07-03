package JunctionSim;


import static JunctionSim.Environment.CAR_DELAY;
import static JunctionSim.Environment.CAR_SPAWN_RATE;
import static JunctionSim.Environment.CAR_SPEED;
import static JunctionSim.Environment.HORIZONTAL_INITIAL_WEIGHT;
import static JunctionSim.Environment.HORIZONTAL_WIDTH;
import static JunctionSim.Environment.VERTICAL_INITIAL_WEIGHT;
import static JunctionSim.Environment.VERTICAL_WIDTH;

// todo:
public class TrafficController
{
    public Junction junction;
    //private static Environment workingConditions;

    TrafficController()
    {
        junction = new Junction();
    }
    // computes how many cars pass in the allocated time, removes them from the stack and returns the sum over all lanes
    public int removeCars(Axis axis, int time)
    {
        int sum = 0;
        for (int i = 0; i < axis.getWidth(); i++)
        {
            int initialWeight = axis.getWeight(i);
            sum = sum + initialWeight - axis.setWeight(i, initialWeight - (1 + (time - CAR_SPEED) / CAR_DELAY));
        }
        return sum;
    }
    public int spawnCars(Axis axis, int time)
    {
        int sum = 0;
        for (int i = 0; i < axis.getWidth(); i++)
        {
            sum += time / CAR_SPAWN_RATE;
            axis.setWeight(i, axis.getWeight(i) + time / CAR_SPAWN_RATE);
        }
        return sum;
    }
}
