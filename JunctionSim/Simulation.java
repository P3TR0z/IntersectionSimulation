package JunctionSim;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import static JunctionSim.Environment.NUMBER_OF_INTERSECTIONS;
import static JunctionSim.Environment.SIMULATION_LENGTH;
import static JunctionSim.Environment.SYSTEM_LAYOUT;

public class Simulation {
    private ArrayList<JuncSystem> systemList;
    public Simulation()
    {
        systemList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_INTERSECTIONS; i++)
        {
            systemList.add(new JuncSystem());
        }
        for (int i = 0; i < SYSTEM_LAYOUT.size(); i++)
        {
            Integer[] con = SYSTEM_LAYOUT.get(i);
            switch (con[1])
            {
                case 0:
                {
                    systemList.get(con[0]).setNorthConnection(systemList.get(con[2]));
                    break;
                }
                case 1:
                {
                    systemList.get(con[0]).setSouthConnection(systemList.get(con[2]));
                    break;
                }
                case 2:
                {
                    systemList.get(con[0]).setEastConnection(systemList.get(con[2]));
                    break;
                }
                case 3:
                {
                    systemList.get(con[0]).setWestConnection(systemList.get(con[2]));
                    break;
                }
                default:
                {
                    throw new RuntimeException("Invalid SYSTEM_LAYOUT format");
                }
            }
        }
    }

    public ArrayList<ArrayList<LogEntry>> run()
    {
        ArrayList<ArrayList<LogEntry>> logs = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_INTERSECTIONS; i++)
            logs.add(new ArrayList<LogEntry>());

        systemList.get(0).getJunction().increment(systemList.get(1).getJunction().northDirection, 5);

        for (JuncSystem system : systemList)
        {
            system.startSpawner();
        }

        // per cycle
        for (int i = 0; i < SIMULATION_LENGTH; i++)
        {
            LogEntry.LogEntryBuilder entryBuilder = new LogEntry.LogEntryBuilder().cycle(i);
            ArrayList<int[]> outgoingValues = new ArrayList<>();
            for (int j = 0; j < NUMBER_OF_INTERSECTIONS; j++)
            {
                outgoingValues.add(systemList.get(j).run(entryBuilder));
                logs.get(j).add(entryBuilder.build());
            }
            for (int j = 0; j < NUMBER_OF_INTERSECTIONS; j++)
            {
                systemList.get(j).setNeighboursWeight(outgoingValues.get(j));
            }
        }

        for (JuncSystem system : systemList)
        {
            system.stopSpawner();
        }
        return logs;
    }
}
