package org.main;

import JunctionSim.*;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args)
    {
        Environment.SetInitialWeight(32, 128);
        Simulation sim1 = new Simulation();
        ArrayList<LogEntry> logs = sim1.run();
        int sum = 0;
        for (LogEntry i : logs)
        {
            sum = sum + i.passedHorizontalCars + i.passedVerticalCars;
            i.Show();
        }
        System.out.println(sum + " cars passed in total");
    }
}