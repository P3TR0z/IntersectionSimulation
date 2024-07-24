package JunctionSim;

import java.util.ArrayList;

import static JunctionSim.Environment.SIMULATION_LENGTH;

public class Simulation {
    private TrafficController trafficCtrl;
    private LightController lightCtrl;
    // Every index represents a cycle, every entry represents number of cars that have passed in that cycle

    public Simulation() {
        trafficCtrl = new TrafficController();
        lightCtrl = new LightController();
    }

    public ArrayList<LogEntry> run() {
        ArrayList<LogEntry> ret = new ArrayList<>();
        CarSpawner Spawner = new CarSpawner(trafficCtrl.junction);
        Thread spawnerThread = new Thread(Spawner);
        System.out.println("Starting spawner");
        spawnerThread.start();
        System.out.println("Finished Spawner");
        // per cycle
        for (int i = 0; i < SIMULATION_LENGTH; i++) {
            LogEntry entry = new LogEntry();
            entry.cycle = i;
            entry.carsVertical = trafficCtrl.junction.getWeight(trafficCtrl.junction.verticalAxis);
            entry.carsHorizontal = trafficCtrl.junction.getWeight(trafficCtrl.junction.horizontalAxis);

            try {
                Thread.sleep(20); // figure out proper sleep amount
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 0 = vertical time, 1 = horizontal time; The function allocates the amount of time each axis gets for "green light"
            int time[] = lightCtrl.computeTime(trafficCtrl.junction.getWeight(trafficCtrl.junction.verticalAxis), trafficCtrl.junction.getWeight(trafficCtrl.junction.horizontalAxis));
            entry.allocatedTime = time;
            // Remove cars from the axis with green light for some amount of time
            // maybe should compare the two times and do the greater one first.
            entry.passedVerticalCars = trafficCtrl.removeCars(trafficCtrl.junction.verticalAxis, time[0]);
            //entry.spawnedHorizontalCars = trafficCtrl.spawnCars(trafficCtrl.junction.horizontalAxis, time[0]);
            entry.passedHorizontalCars = trafficCtrl.removeCars(trafficCtrl.junction.horizontalAxis, time[1]);
            //entry.spawnedVerticalCars = trafficCtrl.spawnCars(trafficCtrl.junction.verticalAxis, time[1]);
            entry.spawnedVerticalCars = Spawner.getCounterSpawnedVertical();
            Spawner.setCounterSpawnedVertical(0);
            entry.spawnedHorizontalCars = Spawner.getCounterSpawnedHorizontal();
            Spawner.setCounterSpawnedHorizontal(0);
            ret.add(i, entry);
        }
        spawnerThread.interrupt();
        return ret;
    }
}
