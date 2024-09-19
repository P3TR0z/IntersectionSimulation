package JunctionSim;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LogEntry
{
    public int cycle;
    public int carsNorth;
    public int carsSouth;
    public int carsEast;
    public int carsWest;
    public int[] allocatedTime;
    public int[] outgoingCars;
    public int spawnedVerticalCars = 0;
    public int spawnedHorizontalCars = 0;

}
