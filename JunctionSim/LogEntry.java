package JunctionSim;

public class LogEntry
{
    public int cycle;
    public int carsHorizontal;
    public int carsVertical;
    public int[] allocatedTime;
    public int passedVerticalCars;
    public int spawnedVerticalCars;
    public int passedHorizontalCars;
    public int spawnedHorizontalCars;


    public void showLog()
    {   // writing of the logs to console can be greatly optimized, I think
        System.out.println("Cycle " + cycle + ":");
        System.out.println("Horizontal Cars: " + carsHorizontal);
        System.out.println("Vertical Cars: " + carsVertical);
        System.out.println("Allocated Vertical Time: " + allocatedTime[0]);
        System.out.println("Allocated Horizontal Time: " + allocatedTime[1]);
        System.out.println("Passed Vertical Cars: " + passedVerticalCars);
        System.out.println("Spawned Horizontal Cars: " + spawnedHorizontalCars);
        System.out.println("Passed Horizontal Cars: " + passedHorizontalCars);
        System.out.println("Spawned Vertical Cars: " + spawnedVerticalCars);
        System.out.println("---------------------------------");
    }
}
