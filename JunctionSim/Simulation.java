package JunctionSim;

import java.util.ArrayList;

import static JunctionSim.Environment.NUMBER_OF_INTERSECTIONS;
import static JunctionSim.Environment.SIMULATION_LENGTH;

public class Simulation {
    private ArrayList<JuncSystem> systemList;

    public Simulation()
    {
        systemList = new ArrayList<>();
        systemList.add(new JuncSystem());
        for (int i = 1; i < NUMBER_OF_INTERSECTIONS; i++)
        {
            systemList.add(new JuncSystem());
            systemList.get(i).setSouthConnection(systemList.get(i - 1));
            systemList.get(i - 1).setNorthConnection(systemList.get(i));
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
            //system.startSpawner(); // goes crazy and crashes the ComputeTimeHighDensity, dunno why
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
        return logs;
    }
}
