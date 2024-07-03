package JunctionSim;

import java.util.ArrayList;

import static JunctionSim.Environment.SIMULATION_LENGTH;

public class Simulation {
    private TrafficController trafficCtrl;
    private LightController lightCtrl;
    // Every index represents a cycle, every entry represents number of cars that have passed in that cycle

    public Simulation()
    {
        trafficCtrl = new TrafficController();
        lightCtrl = new LightController();
    }
    public ArrayList<LogEntry> run()
    {
        ArrayList<LogEntry> ret = new ArrayList<>();
        // per cycle
        for (int i = 0; i < SIMULATION_LENGTH; i++)
        {
            LogEntry entry = new LogEntry();
            entry.cycle = i;
            entry.carsVertical = trafficCtrl.junction.verticalAxis.getWeight();
            entry.carsHorizontal = trafficCtrl.junction.horizontalAxis.getWeight();

            // 0 = vertical time, 1 = horizontal time; The function allocates the amount of time each axis gets for "green light"
            int time[] = lightCtrl.computeTime(trafficCtrl.junction.getDifferenceAsPercentage());
            entry.allocatedTime = time;
            // Remove cars from the axis with green light for some amount of time. For that same amount of time,
            // pile more cars on the other axis. Do the opposite for the remaining time.

            // maybe should compare the two times and do the greater one first.
            entry.passedVerticalCars = trafficCtrl.removeCars(trafficCtrl.junction.verticalAxis, time[0]);
            entry.spawnedHorizontalCars = trafficCtrl.spawnCars(trafficCtrl.junction.horizontalAxis, time[0]);

            entry.passedHorizontalCars = trafficCtrl.removeCars(trafficCtrl.junction.horizontalAxis, time[1]);
            entry.spawnedVerticalCars = trafficCtrl.spawnCars(trafficCtrl.junction.verticalAxis, time[1]);
            ret.add(i, entry);
        }
       return ret;
    }
}
