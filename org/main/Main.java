package org.main;

import JunctionSim.*;

import java.util.ArrayList;


public class Main {
    public static String showDifference(ArrayList<LogEntry> v1, ArrayList<LogEntry> v2)
    {
        int total = 0;
        String ret = "";
        int len = (v1.size() >= v2.size()) ? v1.size() : v2.size(); // "replace with Math.max() call" lol
        for (int i = 0; i < len; i++)
        {
            int diff = v1.get(i).passedHorizontalCars - v2.get(i).passedHorizontalCars + v1.get(i).passedVerticalCars - v2.get(i).passedHorizontalCars;
            ret = ret + "Cycle " + i + ": " + diff + "\n";
            total += diff;
        }
        return ret + "---------------------------------\nTotal difference over " + (len+1) + " cycles: " + total + "\n=================================";

    }
    public static String showAll(ArrayList<LogEntry> logs)
    {
        int sum = 0;
        String ret = "";
        for (LogEntry i : logs)
        {
            sum = sum + i.passedHorizontalCars + i.passedVerticalCars;
            ret = ret + i.getOutput();
        }
       return ret + (sum + " cars passed in total\n=================================");
    }
    public static void main(String[] args)
    {
        Environment.SetInitialWeight(500, 428);
        Simulation sim1 = new Simulation();
        ArrayList<LogEntry> logs = sim1.runOptimized();
        System.out.println(showAll(logs));

        sim1 = new Simulation();
        ArrayList<LogEntry> logs2 = sim1.runDefault();
        System.out.println(showAll(logs2));

        System.out.println(showDifference(logs, logs2));
    }
}