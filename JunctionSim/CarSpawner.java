package JunctionSim;

import java.util.concurrent.atomic.AtomicInteger;

import static JunctionSim.Environment.CAR_SPAWN_AMOUNT;
import static JunctionSim.Environment.CAR_SPAWN_DELAY;
import static java.lang.Thread.sleep;

public class CarSpawner implements Runnable
{
    private Junction j;
    private AtomicInteger counterSpawnedVertical = new AtomicInteger(0);
    private AtomicInteger counterSpawnedHorizontal = new AtomicInteger(0);
    public CarSpawner(Junction rhs)
    {
        j = rhs;
    }

    public int getCounterSpawnedVertical()
    {
        return counterSpawnedVertical.get();
    }
    public int getCounterSpawnedHorizontal()
    {
        return counterSpawnedHorizontal.get();
    }
    public void setCounterSpawnedVertical(int rhs)
    {
        counterSpawnedVertical.set(rhs);
    }
    public void setCounterSpawnedHorizontal(int rhs)
    {
        counterSpawnedHorizontal.set(rhs);
    }
    public void run()
    {
        while (true)
        {
            j.increment(j.verticalAxis, CAR_SPAWN_AMOUNT);
            counterSpawnedVertical.getAndAdd(CAR_SPAWN_AMOUNT * j.verticalAxis.size());
            j.increment(j.horizontalAxis, CAR_SPAWN_AMOUNT * j.horizontalAxis.size());
            counterSpawnedHorizontal.getAndAdd(CAR_SPAWN_AMOUNT);
            try
            {
                sleep(CAR_SPAWN_DELAY);
            }
            catch (InterruptedException e)
            {
                System.out.println("Thread interrupted");
                break;
            }
        }
    }
}
