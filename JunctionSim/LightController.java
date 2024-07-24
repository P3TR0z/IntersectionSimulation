package JunctionSim;

import static JunctionSim.Environment.CAR_DELAY;
import static JunctionSim.Environment.CAR_SPEED;
import static JunctionSim.Environment.CYCLE_LENGTH;
import static JunctionSim.Environment.HORIZONTAL_WIDTH;
import static JunctionSim.Environment.VERTICAL_WIDTH;

public class LightController
{
    // returns a tuple. index 0 = verticalTime; index 1 = horizontalTime;
    // smart algorithm will go here

    // returns time needed for ONE LANE to fully clear the intersection
    public int getNeededTime(int weight)
    {
        return (CAR_SPEED + (weight - 1) * CAR_DELAY);
    }

    public int[] computeTime(int verticalWeight, int horizontalWeight)
    {
        int ret[] = new int[2];
        if (verticalWeight < horizontalWeight)
        {
            int nTime = getNeededTime(verticalWeight/VERTICAL_WIDTH);
            if (nTime < CYCLE_LENGTH)
            {
                ret[0] = nTime;
                ret[1] = CYCLE_LENGTH - nTime;
                return ret;
            }
            ret[0] = CYCLE_LENGTH;
            ret[1] = 0;
            return ret;
        }
        else
        {
            int nTime = getNeededTime(horizontalWeight/HORIZONTAL_WIDTH);
            if (nTime < CYCLE_LENGTH)
            {
                ret[1] = nTime;
                ret[0] = CYCLE_LENGTH - nTime;
                return ret;
            }
            ret[1] = CYCLE_LENGTH;
            ret[0] = 0;
            return ret;
        }
    }
    public int[] computeTime()
    {
        int ret[] = new int[2];
        ret[0] = 30;
        ret[1] = 30;
        return ret;
    }
}
