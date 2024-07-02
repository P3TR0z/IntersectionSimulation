package JunctionSim;

// Simulation helper class
public final class Environment {
    public static final int SIMULATION_LENGTH = 10; // in cycles
    public static final int CYCLE_LENGTH = 60; // how long a cycle lasts in seconds
    public static final int CAR_SPEED = 10; // amount of time needed for one car to pass through the intersection
    public static final int CAR_DELAY = 2; // delay between each car's movement
    public static final int VERTICAL_WIDTH = 5; // number of vertical lanes;
    public static final int HORIZONTAL_WIDTH = 5; // number of horizontal lanes
    public static final int INIT_WEIGHT = 100; // initial cars on each lane
    public static final int CAR_SPAWN_RATE = 20; // amount of time needed for new cars to appear in an intersection

    // todo
    public void ShowEnvironment()
    {}
}
