package JunctionSim;

import java.util.ArrayList;

import static JunctionSim.Environment.CAR_DELAY;
import static JunctionSim.Environment.CAR_SPEED;
import static JunctionSim.Environment.CYCLE_LENGTH;
import static JunctionSim.Environment.HORIZONTAL_WIDTH;
import static JunctionSim.Environment.VERTICAL_WIDTH;

public class LightController
{
    private ArrayList<Boolean> schedule = new ArrayList<>(); // 1 - Vertical lanes get whole cycle; 0 - Horizontal lanes get cycle
    public boolean verticalPriority = true; // true = vertical passes first; false = horizontal first; There's gotta be a better way but eh

    // returns time needed for ONE LANE to fully clear the intersection
    public int getNeededTime(int weight)
    {
        return (CAR_SPEED + (weight - 1) * CAR_DELAY);
    }

    public int[] computeTimeLowDensity(int verticalWeight, int horizontalWeight)
    {
        int ret[] = new int[2];
        int nTime;
        if ((verticalWeight < horizontalWeight) && verticalWeight > 5)
        {
            verticalPriority = true;
            nTime = getNeededTime(verticalWeight / VERTICAL_WIDTH);
            if (nTime < CYCLE_LENGTH)
            {
                ret[0] = nTime;
                ret[1] = CYCLE_LENGTH - nTime;
                return ret;
            }
            ret[0] = CYCLE_LENGTH;
            ret[1] = 0;
        }
        else
        {
            verticalPriority = false;
            nTime = getNeededTime(horizontalWeight / HORIZONTAL_WIDTH);
            if (nTime < CYCLE_LENGTH)
            {
                ret[1] = nTime;
                ret[0] = CYCLE_LENGTH - nTime;
                return ret;
            }
            ret[1] = CYCLE_LENGTH;
            ret[0] = 0;
        }
        return ret;
    }
    private void generateSchedule(int numberOfTurns, boolean value)
    {
        boolean temp = true;
        for (int i = 0; i < numberOfTurns; i++)
        {
            schedule.add(value);
            if (i % 2 == 0)
            {
                if (temp)
                    schedule.add(!value);
                temp = !temp;
            }
        }
    }
    public int[] computeTimeHighDensity(int verticalWeight, int horizontalWeight)
    {
        if (schedule.size() != 0)
        {
            if (schedule.get(0) == Boolean.TRUE)
            {
                schedule.remove(0);
                verticalPriority = true;
                return (new int[] { CYCLE_LENGTH, 0 });
            }
            else
            {
                schedule.remove(0);
                verticalPriority = false;
                return (new int[] { 0, CYCLE_LENGTH });
            }
        }
        int nTime;
        if (verticalWeight >= horizontalWeight)
        {
            nTime = getNeededTime(verticalWeight / VERTICAL_WIDTH);
            generateSchedule((nTime / CYCLE_LENGTH) - 1, true);
            verticalPriority = true;
            return new int[] { CYCLE_LENGTH, 0 };
        }
        else
        {
            nTime = getNeededTime(horizontalWeight / HORIZONTAL_WIDTH);
            generateSchedule((nTime / CYCLE_LENGTH) - 1, false);
            verticalPriority = false;
            return new int[] { 0, CYCLE_LENGTH };
        }
    }
    // returns a tuple. index 0 = verticalTime; index 1 = horizontalTime;
    public int[] computeTime(int verticalWeight, int horizontalWeight)
    {
        if (verticalWeight > 100 && horizontalWeight > 100) // arbitrary value for a "high density"
        {
            return computeTimeHighDensity(verticalWeight, horizontalWeight);
        }
        if (verticalWeight + horizontalWeight < 200) // arbitrary value for low density
        {
            return computeTimeLowDensity(verticalWeight, horizontalWeight);
        }
        return computeTime();
    }

    public int[] computeTime()
    {
        int ret[] = new int[2];
        ret[0] = CYCLE_LENGTH / 2;
        ret[1] = CYCLE_LENGTH / 2;
        verticalPriority = true;
        return ret;
    }
}
