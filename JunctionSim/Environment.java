package JunctionSim;

// Simulation helper class
public final class Environment {
    public static final int SIMULATION_LENGTH = 10; // in cycles
    public static final int CYCLE_LENGTH = 60; // how long a cycle lasts in seconds
    public static final int CAR_SPEED = 10; // amount of time needed for one car to pass through the intersection
    public static final int CAR_DELAY = 2; // delay between each car's movement
    public static final int VERTICAL_WIDTH = 1; // number of vertical lanes;
    public static final int HORIZONTAL_WIDTH = 1; // number of horizontal lanes
    public static int VERTICAL_INITIAL_WEIGHT = 10; // initial cars on each lane
    public static int HORIZONTAL_INITIAL_WEIGHT = 10;
    public static int CAR_SPAWN_RATE = 20; // amount of time needed for new cars to appear in an intersection

    public static void SetInitialWeight(int verticalInit, int horizontalInit)
    {
        VERTICAL_INITIAL_WEIGHT = verticalInit;
        HORIZONTAL_INITIAL_WEIGHT = horizontalInit;
    }
    // todo
    public void ShowEnvironment()
    {}
}
