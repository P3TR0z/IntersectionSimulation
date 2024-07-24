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

    public void Show()
    {
        String output = "";
        output += ("Cycle " + cycle + ":\n");
        output += ("Vertical Cars: " + carsVertical + "\n");
        output += ("Horizontal Cars: " + carsHorizontal + "\n");
        output += ("Allocated Vertical Time: " + allocatedTime[0] + "\n");
        output += ("Allocated Horizontal Time: " + allocatedTime[1] + "\n");
        output += ("Passed Vertical Cars: " + passedVerticalCars + "\n");
        output += ("Spawned Horizontal Cars: " + spawnedHorizontalCars + "\n");
        output += ("Passed Horizontal Cars: " + passedHorizontalCars + "\n");
        output += ("Spawned Vertical Cars: " + spawnedVerticalCars + "\n");
        output += ("---------------------------------");
        System.out.println(output);
    }
}
