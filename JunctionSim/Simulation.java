package JunctionSim;

import java.util.ArrayList;

import static JunctionSim.Environment.CAR_DELAY;
import static JunctionSim.Environment.SIMULATION_LENGTH;

public class Simulation {
    private TrafficController trafficCtrl;
    private LightController lightCtrl;
    private Junction junction;

    public Simulation() {
        trafficCtrl = new TrafficController();
        lightCtrl = new LightController();
        junction = trafficCtrl.getJunction();
    }

    public ArrayList<LogEntry> runOptimized() {
        ArrayList<LogEntry> ret = new ArrayList<>();
        CarSpawner spawner = new CarSpawner(junction);
        Thread spawnerThread = new Thread(spawner);
        System.out.println("Starting spawner");
        spawnerThread.start();
        System.out.println("Finished Spawner");
        // per cycle
        for (int i = 0; i < SIMULATION_LENGTH; i++) {
            LogEntry entry = new LogEntry();
            entry.cycle = i;
            entry.carsVertical = junction.getWeight(junction.verticalAxis);
            entry.carsHorizontal = junction.getWeight(junction.horizontalAxis);

            // 0 = vertical time, 1 = horizontal time; The function allocates the amount of time each axis gets for "green light"
            int time[] = lightCtrl.computeTime(junction.getWeight(junction.verticalAxis), junction.getWeight(junction.horizontalAxis));
            runEssential(entry, spawner, time);
            ret.add(i, entry);
        }
        spawnerThread.interrupt();
        return ret;
    }
    public ArrayList<LogEntry> runDefault() {
        ArrayList<LogEntry> ret = new ArrayList<>();
        CarSpawner spawner = new CarSpawner(junction);
        Thread spawnerThread = new Thread(spawner);
        System.out.println("Starting spawner");
        spawnerThread.start();
        System.out.println("Finished Spawner");
        // per cycle
        for (int i = 0; i < SIMULATION_LENGTH; i++) {
            LogEntry entry = new LogEntry();
            entry.cycle = i;
            entry.carsVertical = junction.getWeight(junction.verticalAxis);
            entry.carsHorizontal = junction.getWeight(junction.horizontalAxis);

            // 0 = vertical time, 1 = horizontal time; The function allocates the amount of time each axis gets for "green light"
            int time[] = lightCtrl.computeTime();
            runEssential(entry, spawner, time);
            ret.add(i, entry);
        }
        spawnerThread.interrupt();
        return ret;
    }
    private void runEssential(LogEntry entry, CarSpawner spawner, int[] time)
    {
        entry.allocatedTime = time;

        // Remove cars from the axis with green light for some amount of time
        if (lightCtrl.verticalPriority) // if vertical axis has green light first
        {
            entry.passedVerticalCars = trafficCtrl.removeCars(junction.verticalAxis, time[0]);
            if (time[1] != 0)
            {
                entry.passedHorizontalCars = trafficCtrl.removeCars(junction.horizontalAxis, time[1]);
            }
            else entry.passedHorizontalCars = 0;
        }
        else // if horizontal axis has priority
        {
            entry.passedHorizontalCars = trafficCtrl.removeCars(junction.horizontalAxis, time[1]);
            if (time[0] != 0)
            {
                entry.passedVerticalCars = trafficCtrl.removeCars(junction.verticalAxis, time[0]);
            }
            else  entry.passedVerticalCars = 0;
        }

        try {
            Thread.sleep((entry.passedVerticalCars + entry.passedHorizontalCars) * CAR_DELAY); // + car speed? maybe multi-branch depending on times
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // get number of cars spawned for logging purposes
        entry.spawnedVerticalCars = spawner.getCounterSpawnedVertical();
        spawner.setCounterSpawnedVertical(0);
        entry.spawnedHorizontalCars = spawner.getCounterSpawnedHorizontal();
        spawner.setCounterSpawnedHorizontal(0);
    }
}
