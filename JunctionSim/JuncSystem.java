package JunctionSim;

import static JunctionSim.Environment.CAR_DELAY;

public class JuncSystem
{
    private TrafficController trafficCtrl;
    private LightController lightCtrl;
    private Junction junction;
    private CarSpawner spawner;

    private JuncSystem northConnection = null;
    private JuncSystem southConnection = null;
    private JuncSystem eastConnection = null;
    private JuncSystem westConnection = null;

    public JuncSystem()
    {
        trafficCtrl = new TrafficController();
        lightCtrl = new LightController();
        junction = trafficCtrl.getJunction();
    }
    public Junction getJunction()
    {
        return junction;
    }
    public TrafficController getTrafficCtrl()
    {
        return trafficCtrl;
    }
    public void startSpawner()
    {
        spawner = new CarSpawner(junction);
        Thread spawnerThread = new Thread(spawner);
        System.out.println("Starting spawner");
        spawnerThread.start();
        System.out.println("Finished Spawner");
    }
    public int[] run(LogEntry.LogEntryBuilder entryBuilder)
    {
        // logging stuff
        entryBuilder.carsNorth(junction.getWeight(junction.northDirection));
        entryBuilder.carsSouth(junction.getWeight(junction.southDirection));
        entryBuilder.carsEast(junction.getWeight(junction.eastDirection));
        entryBuilder.carsWest(junction.getWeight(junction.westDirection));

        // 0 = vertical time, 1 = horizontal time; The function allocates the amount of time each axis gets for "green light"
        int time[] = lightCtrl.computeTime(junction.getVerticalWeight(), junction.getHorizontalWeight());
        entryBuilder.allocatedTime(time);
        int[] outgoing = new int[4];
        // Remove cars from the axis with green light for some amount of time
        if (lightCtrl.verticalPriority) // if vertical axis has green light first
        {
            outgoing[0] = trafficCtrl.removeCars(junction.northDirection, time[0]);
            outgoing[1] = trafficCtrl.removeCars(junction.southDirection, time[0]);
            if (time[1] != 0)
            {
                outgoing[2] = trafficCtrl.removeCars(junction.eastDirection, time[1]);
                outgoing[3] = trafficCtrl.removeCars(junction.westDirection, time[1]);
            }
        }
        else // if horizontal axis has priority
        {
            outgoing[2] = trafficCtrl.removeCars(junction.eastDirection, time[1]);
            outgoing[3] = trafficCtrl.removeCars(junction.westDirection, time[1]);
            if (time[0] != 0)
            {
                outgoing[0] = trafficCtrl.removeCars(junction.northDirection, time[0]);
                outgoing[1] = trafficCtrl.removeCars(junction.southDirection, time[0]);
            }
        }
        try {
            Thread.sleep((long)(CAR_DELAY) * (outgoing[0] + outgoing[1] + outgoing[2] + outgoing[3])); // + car speed? maybe multi-branch depending on times
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //get number of cars spawned for logging purposes
        /*entryBuilder.spawnedVerticalCars(spawner.getCounterSpawnedVertical());
        spawner.setCounterSpawnedVertical(0);
        entryBuilder.spawnedHorizontalCars(spawner.getCounterSpawnedHorizontal());
        spawner.setCounterSpawnedHorizontal(0);*/

        entryBuilder.outgoingCars(outgoing);
        return outgoing;
    }

    public JuncSystem getNorthConnection()
    {
        return northConnection;
    }
    public void setNorthConnection(JuncSystem northConnection)
    {
        this.northConnection = northConnection;
    }
    public JuncSystem getSouthConnection()
    {
        return southConnection;
    }
    public void setSouthConnection(JuncSystem southConnection)
    {
        this.southConnection = southConnection;
    }
    public JuncSystem getEastConnection()
    {
        return eastConnection;
    }
    public void setEastConnection(JuncSystem eastConnection)
    {
        this.eastConnection = eastConnection;
    }
    public JuncSystem getWestConnection()
    {
        return westConnection;
    }
    public void setWestConnection(JuncSystem westConnection)
    {
        this.westConnection = westConnection;
    }
    public void setNeighboursWeight(int[] outgoingValues)
    {
        if (northConnection != null)
        {
            northConnection.trafficCtrl.distributeCars(northConnection.junction.northDirection, outgoingValues[0]);
        }
        if (southConnection != null)
        {
            southConnection.trafficCtrl.distributeCars(southConnection.junction.southDirection, outgoingValues[1]);
        }
        if (eastConnection != null)
        {
            eastConnection.trafficCtrl.distributeCars(eastConnection.junction.eastDirection, outgoingValues[2]);
        }
        if (westConnection != null)
        {
            westConnection.trafficCtrl.distributeCars(westConnection.junction.westDirection, outgoingValues[3]);
        }
    }
}
