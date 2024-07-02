package JunctionSim;

// todo:
public class TrafficController
{
    public Junction junction;
    //private static Environment workingConditions;

    TrafficController()
    {
        junction = new Junction(Environment.VERTICAL_WIDTH, Environment.HORIZONTAL_WIDTH, Environment.INIT_WEIGHT);
    }
    TrafficController(int initialWeight)
    {
        junction = new Junction(Environment.VERTICAL_WIDTH, Environment.HORIZONTAL_WIDTH, initialWeight);
    }
    // computes how many cars pass in the allocated time, removes them from the stack and returns the sum over all lanes
    public int removeCars(Axis axis, int time)
    {
        int sum = 0;
        for (int i = 0; i < axis.getWidth(); i++)
        {
            int initialWeight = axis.getWeight(i);
            sum = sum + initialWeight - axis.setWeight(i, initialWeight - (1 + (time - Environment.CAR_SPEED) / Environment.CAR_DELAY));
        }
        return sum;
    }
    public int spawnCars(Axis axis, int time)
    {
        int sum = 0;
        for (int i = 0; i < axis.getWidth(); i++)
        {
            sum += time / Environment.CAR_SPAWN_RATE;
            axis.setWeight(i, axis.getWeight(i) + time / Environment.CAR_SPAWN_RATE);
        }
        return sum;
    }
}
