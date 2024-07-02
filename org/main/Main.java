package org.main;

import JunctionSim.*;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args)
    {
        Simulation sim = new Simulation();
        ArrayList<LogEntry> logs = sim.run();
        for (LogEntry i : logs)
            i.showLog();
    }
}